package kr.co.dysnt.framework.common.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;

import kr.co.dysnt.framework.filter.JwtRequestFilter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class RefreshableSqlSessionFactoryBean extends SqlSessionFactoryBean implements DisposableBean {

    private SqlSessionFactory proxy;
    private int interval = 1000;

    private Timer timer;
    private TimerTask task;

    private Resource[] mapperLocations;

    private boolean running = false;

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();
//    private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class); // ✅ Logger 추가

    public void setMapperLocations(Resource[] mapperLocations) {
        try {
            super.setMapperLocations(mapperLocations);
            this.mapperLocations = mapperLocations;
        } catch (Exception e) {
            log.error("setMapperLocations caught exception", e.getMessage());
        }
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void refresh() throws Exception {
        w.lock();
        try {
            super.afterPropertiesSet();
        } finally {
            w.unlock();
        }

        log.info("sqlMapClient refreshed.");
    }

    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }

    private void setRefreshable() {
        proxy = (SqlSessionFactory) Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(),
                new Class[] { SqlSessionFactory.class }, new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(getParentObject(), args);
                    }
                });

        task = new TimerTask() {
            private Map<Resource, Long> map = new HashMap<Resource, Long>();

            public void run() {
                if (isModified()) {
                    try {
                        refresh();
                    } catch (Exception e) {
                        log.error("caught exception", e.getMessage());
                    }
                }
            }

            private boolean isModified() {
                boolean retVal = false;

                try {
                    if (mapperLocations != null) {
                        for (int i = 0; i < mapperLocations.length; i++) {
                            Resource mappingLocation = mapperLocations[i];
                            retVal |= findModifiedResource(mappingLocation);
                        }
                    }
                } catch (Exception e) {
                    log.error("isModified caught exception", e.getMessage());
                }

                return retVal;
            }

            private boolean findModifiedResource(Resource resource) {
                boolean retVal = false;
                List<String> modifiedResources = new ArrayList<String>();

                try {
                    long modified = resource.lastModified();

                    if (map.containsKey(resource)) {
                        long lastModified = ((Long) map.get(resource)).longValue();

                        if (lastModified != modified) {
                            map.put(resource, new Long(modified));
                            modifiedResources.add(resource.getDescription());
                            retVal = true;
                        }
                    } else {
                        map.put(resource, new Long(modified));
                    }
                } catch (IOException e) {
                    log.error("findModifiedResource caught exception", e.getMessage());
                }
                if (retVal) {
                    log.info("modified files : " + modifiedResources);
                }
                return retVal;
            }
        };

        timer = new Timer(true);
        resetInterval();

    }

    private Object getParentObject() throws Exception {
        r.lock();
        try {
            return super.getObject();
        } finally {
            r.unlock();
        }
    }

    public SqlSessionFactory getObject() throws Exception {
        try {
            if (this.proxy == null) {
                setRefreshable();
            }
        } catch (Exception e) {
            log.error("getObject caught exception", e.getMessage());
        }
        return this.proxy;
    }

    public Class<? extends SqlSessionFactory> getObjectType() {
        return (this.proxy != null ? this.proxy.getClass() : SqlSessionFactory.class);
    }

    public boolean isSingleton() {
        return true;
    }

    public void setCheckInterval(int ms) {
        interval = ms;

        try {
            if (timer != null) {
                resetInterval();
            }
        } catch (Exception e) {
            log.error("setCheckInterval caught exception", e.getMessage());
        }
    }

    private void resetInterval() {
        try {
            if (running) {
                timer.cancel();
                running = false;
            }
        } catch (Exception e) {
            log.error("resetInterval caught exception", e.getMessage());
        }

        try {
            if (interval > 0) {
                timer.schedule(task, 0, interval);
                running = true;
            }
        } catch (Exception e) {
            log.error("resetInterval caught exception", e.getMessage());
        }
    }

    public void destroy() throws Exception {
        timer.cancel();
    }
}
