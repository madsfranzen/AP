package Opgave2;

import java.util.HashMap;
import java.util.Map;

public class DictionaryHashMap<K, V> implements Dictionary<K, V> {

    private Map<K, V>[] tabel;
    private static int N = 13;
    private int size = 0;

    /**
     * HashingMap constructor comment.
     */

    @SuppressWarnings("unchecked")
    public DictionaryHashMap() {
        tabel = new HashMap[N];
        for (int i = 0; i < N; i++) {
            tabel[i] = new HashMap<K, V>();
        }
    }

    @Override
    public V get(K key) {
        return tabel[key.hashCode() % N].get(key);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        if (get(key) == null) {
            size++;
        }
        return tabel[key.hashCode() % N].put(key, value);
    }

    @Override
    public V remove(K key) {
        if (get(key) != null) {
            size--;
        }
        return tabel[key.hashCode() % N].remove(key);
    }

    @Override
    public int size() {
        return size;
    }

}
