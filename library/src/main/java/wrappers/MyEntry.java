package wrappers;

import java.util.Map;

public class MyEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    public MyEntry() {
    }

    ;

    public MyEntry(K key, V value) {
        this.value = value;
        this.key = key;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
}
