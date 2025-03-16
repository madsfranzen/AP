package afleveringstuderende;

public class DictionaryDoubleHashing <K, V> implements Dictionary<K, V> {
    private Entry<K,V>[] table;
    private int size;

   private final Entry DELETED = new Entry(null,null);

    public DictionaryDoubleHashing(int length) {
        table =  new Entry[length];
        size = 0;
    }


    @Override
    public V get(K key) {
        //TODO
        return null;
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return false;
    }

    @Override
    public V put(K key, V value) {
       // TODO
        return null;
    }

    @Override
    public V remove(K key) {
       //TODO
        return null;
    }

    @Override
    public int size() {
        //TODO
        return 0;
    }


    // method only for test purpose
    public void writeOut() {
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "\t" + table[i]);
        }
    }

    public static class Entry<K,V>{
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        public String toString(){
            return "(" +key + " , " + value + ")";
        }
    }
}
