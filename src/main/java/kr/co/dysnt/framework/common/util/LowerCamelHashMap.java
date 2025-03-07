package kr.co.dysnt.framework.common.util;

import org.springframework.jdbc.support.JdbcUtils;

import java.util.HashMap;

public class LowerCamelHashMap extends HashMap {

    private static final long serialVersionUID = -2910195798340305871L;

    @SuppressWarnings("unchecked")
    @Override
    public Object put(Object key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String) key), value);
    }
}
