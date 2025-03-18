public class Test2 {

    public static void main(String[] args) {
            DictionaryDoubleHashing<Integer, String> dictionary = new DictionaryDoubleHashing<Integer, String>(11);
    
            System.out.println(dictionary.isEmpty());
            System.out.println(dictionary.size());
    
            dictionary.put(8, "hans");
            dictionary.put(3, "viggo");
            dictionary.put(1, "bente");
    
            System.out.println(dictionary.remove(1));
    
            System.out.println();
    
            dictionary.writeOut();
    
            System.out.println();
    
            dictionary.put(1, "søren");
            dictionary.put(18, "jørgen");
    
            dictionary.writeOut();
    
            System.out.println();
    
            System.out.println(dictionary.isEmpty());
            System.out.println(dictionary.size());
            System.out.println(dictionary.get(8));
            System.out.println(dictionary.remove(3));
    
            dictionary.put(7, "bent");
            dictionary.put(2, "lene");
            dictionary.put(10, "sanne");
    
            System.out.println(dictionary.isEmpty());
            System.out.println(dictionary.size());
            System.out.println(dictionary.put(8, "Ida"));
            System.out.println(dictionary.get(8));
            System.out.println(dictionary.size());
    
            System.out.println();
    
            dictionary.writeOut();
        }
}
