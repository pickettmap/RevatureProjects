package com.intellijeep.util;

public class ReverseEnumMap <V extends Enum<V> & EnumConverter<?>> {

    private IntelliJeepHashMap<Integer, V> map = new IntelliJeepHashMap<>();

    public ReverseEnumMap(Class <V> valueType) {
        for(V v : valueType.getEnumConstants()) {
            map.put(v.convert(), v);
        }
    }

    public V get(int num) {
        return map.get(num);
    }
}
