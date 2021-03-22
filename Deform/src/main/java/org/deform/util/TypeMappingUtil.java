package org.deform.util;

import java.util.HashMap;
import java.util.Map;

public class TypeMappingUtil {
    private static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;
    static {
        WRAPPER_TYPE_MAP = new HashMap<Class<?>, Class<?>>(17);
        WRAPPER_TYPE_MAP.put(Integer.class, int.class);
        WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
        WRAPPER_TYPE_MAP.put(Character.class, char.class);
        WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
        WRAPPER_TYPE_MAP.put(Double.class, double.class);
        WRAPPER_TYPE_MAP.put(Float.class, float.class);
        WRAPPER_TYPE_MAP.put(Long.class, long.class);
        WRAPPER_TYPE_MAP.put(Short.class, short.class);
        WRAPPER_TYPE_MAP.put(Void.class, void.class);
        WRAPPER_TYPE_MAP.put(String.class, String.class); //I am treating String like a primitive, sue me
}

    public static boolean isPrimitiveType(Class c) {
        if(WRAPPER_TYPE_MAP.containsKey(c) || WRAPPER_TYPE_MAP.containsValue(c)) {
            return true;
        }
        return false;
    }

    public static boolean isPrimitiveType(Object o) {
        if(WRAPPER_TYPE_MAP.containsKey(o.getClass()) || WRAPPER_TYPE_MAP.containsValue(o.getClass())) {
            return true;
        }
        return false;
    }
}
