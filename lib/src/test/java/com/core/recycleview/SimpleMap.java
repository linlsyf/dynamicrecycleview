package com.core.recycleview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxw
 */
public class SimpleMap extends LinkedHashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = -1923285173865070782L;

    public SimpleMap() {
    }

    public SimpleMap(int capacity) {
        super(capacity);
    }

    public SimpleMap(int capacity, float loadFactor) {
        super(capacity, loadFactor);
    }

    public SimpleMap(Map<? extends String, ?> map) {
        super(map);
    }


    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        String value = this.getString(key);
        return null == value ? defaultValue : Boolean.valueOf(value);
    }

    public double getDouble(String key) {
        return this.getDouble(key, 0.0D);
    }

    public Double getDouble(String key, Double defaultValue) {
        String value = this.getString(key);
        return null == value ? defaultValue : Double.valueOf(value);
    }

    public float getFloat(String key) {
        return this.getFloat(key, 0.0F);
    }

    public Float getFloat(String key, Float defaultValue) {
        String value = this.getString(key);
        return null == value ? defaultValue : Float.valueOf(value);
    }

    public int getInt(String key) {
        return this.getInteger(key, 0);
    }

    public Integer getInteger(String key, Integer defaultValue) {
        String value = this.getString(key);
        return null == value ? defaultValue : Integer.valueOf(value);
    }

    public long getLong(String key) {
        return this.getLong(key, 0L);
    }

    public Long getLong(String key, Long defaultValue) {
        String value = this.getString(key);
        return null == value ? defaultValue : Long.valueOf(value);
    }

    public String getString(String key) {
        Object value = this.get(key);
        return null == value ? null : String.valueOf(value);
    }

    public String getString(String key, String defaultValue) {
        String value = this.getString(key);
        return null == value ? defaultValue : value;
    }

    public String[] getStringArray(String key) {
        String value = this.getString(key);
        return null == value ? new String[0] : value.split(",");
    }

    public <T> T getAsClz(String key, Class<T> clz) {
        return getAsClz(key, clz, null);
    }

    public <T> T getAsClz(String key, Class<T> clz, T defaultVal) {
        Object value = this.get(key);
        if (value == null) {
            return defaultVal;
        }
        if (clz.isAssignableFrom(value.getClass())) {
            return (T) value;
        }
        return null;
    }

    public SimpleMap getAsMap(String key) {
        Object value = this.get(key);
        return value instanceof Map ? new SimpleMap((Map) value) : null;
    }

    public List<Object> getAsList(String key) {
        Object value = this.get(key);
        return value instanceof List ? (List) value : null;
    }

    public List<SimpleMap> getAsMapList(String key) {
        List values = this.getAsList(key);
        if (null != values) {
            ArrayList mapObjects = new ArrayList();
            Iterator i$ = values.iterator();

            while (i$.hasNext()) {
                Object o = i$.next();
                if (o instanceof Map) {
                    mapObjects.add(new SimpleMap((Map) o));
                }
            }
            return mapObjects;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T remove(String key, Class<T> type, T defaultValue) {
        Object obj = super.remove(key);
        if (obj != null && type.isInstance(obj)) {
            return (T) obj;
        }
        return defaultValue;
    }

}
