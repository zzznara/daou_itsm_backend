package kr.co.dysnt.framework.config;

import com.zaxxer.hikari.HikariDataSource;

import kr.co.dysnt.framework.common.bean.RefreshableSqlSessionFactoryBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@Configuration
@Lazy
@EnableTransactionManagement
@RequiredArgsConstructor
public class DatabaseConfig {

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "txManager")
    public PlatformTransactionManager txManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
        dataSourceTransactionManager.setNestedTransactionAllowed(true);
        return dataSourceTransactionManager;
    }

    @Bean(name = "sqlSession")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new RefreshableSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.co.dysnt.framework.**.vo" +
                ", kr.co.dysnt.framework.**.dto" +
                ", kr.co.dysnt.framework.**.dao");
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/config/mybatis-config.xml"));

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resourcePatternResolver.getResources("classpath*:mybatis/mapper/**/*.xml");

        sqlSessionFactoryBean.setMapperLocations(resource);

        ((RefreshableSqlSessionFactoryBean) sqlSessionFactoryBean).setInterval(1000);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

}
