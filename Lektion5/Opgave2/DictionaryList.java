package Opgave2;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class DictionaryList<K, V> implements Dictionary<K, V> {

    private List<KeyValuePair>[] tabel;
    private static int N = 13;
    private int size = 0;

    public DictionaryList() {
        tabel = new List[N];
        for (int i = 0; i < N; i++) {
            tabel[i] = new ArrayList<>();
        }
    }

    public class KeyValuePair {
        private K key;
        private V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        List<KeyValuePair> array = tabel[key.hashCode() % N];
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getKey().equals(key)) {
                value = array.get(i).getValue();
            }
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {

        List<KeyValuePair> array = tabel[key.hashCode() % N];

        // IF ALREADY EXISTS
        if (get(key) != null) {
            V oldValue = get(key);

            remove(key);
            array.add(new KeyValuePair(key, value));

            return oldValue;

            // IF NEW ENTRY
        } else {
            array.add(new KeyValuePair(key, value));
            size++;
            return null;
        }

    }

    @Override
    public V remove(K key) {
        List<KeyValuePair> array = tabel[key.hashCode() % N];
        V removedValue = null;
        int i = 0;
        boolean found = false;
        while (i < array.size() && !found) {
            KeyValuePair kvpair = array.get(i);
            if (kvpair.getKey().equals(key)) {
                removedValue = kvpair.getValue();
                array.remove(i);
                found = true;
                size--;
            } else {
                i++;
            }
        }
        return removedValue;
    }

    @Override
    public int size() {
        return size;
    }

}
