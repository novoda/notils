package com.novoda.notils;

import java.util.*;

public final class Collections {

    private Collections() {
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    public static <E> ArrayList<E> newArrayList(int startingSize) {
        return new ArrayList<E>(startingSize);
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<E>();
    }

    public static <K, V> Hashtable<K, V> newHashTable() {
        return new Hashtable<K, V>();
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> keyType) {
        return new EnumMap<K, V>(keyType);
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Class<E> keyType) {
        return EnumSet.noneOf(keyType);
    }
}
