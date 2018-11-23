package com.github.soilex.xshop.utils;

import com.github.soilex.xshop.annotations.EnumField;

import java.util.HashMap;
import java.util.Hashtable;

public final class Enums {

    private Enums() {
    }

    private static Hashtable<Class<?>, EnumInfo<?>> enumInfoMap = new Hashtable<>();

    private static EnumInfo retrieveEnumFields(Class<?> enumType) {
        return enumInfoMap.computeIfAbsent(enumType, c -> new EnumInfo(c));
    }

    public static int getEnumValue(Enum<?> e) {
        return retrieveEnumFields(e.getClass()).getEnumValue(e);
    }

    public static String getEnumText(Enum<?> e) {
        return retrieveEnumFields(e.getClass()).getEnumText(e);
    }

    public static <T extends Enum<?>> T valueOf(Class<T> enumType, int value) throws NoSuchFieldException {
        return (T) retrieveEnumFields(enumType).valueOf(value);
    }

    private static class EnumInfo<T extends Enum<?>> {

        private HashMap<T, EnumField> fields;

        public EnumInfo(Class<T> enumType) {
            T[] constants = enumType.getEnumConstants();
            fields = new HashMap<>(constants.length);
            for (T e : constants) {
                try {
                    EnumField field = (EnumField) enumType.getField(e.name()).getAnnotation(EnumField.class);
                    fields.put(e, field);
                } catch (NoSuchFieldException ignore) {
                    // 这种情况是永远都不会发生的
                }
            }
        }

        public String getEnumText(Enum<?> e) {
            return fields.get(e).text();
        }

        public int getEnumValue(Enum<?> e) {
            return fields.get(e).value();
        }

        public T valueOf(int value) throws NoSuchFieldException {
            return fields.entrySet().stream()
                    .filter(c -> c.getValue().value() == value)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchFieldException(String.valueOf(value)))
                    .getKey();
        }
    }
}


