public class TEST {
    public static void main(String[] args) {
        DictionaryDoubleHashing<Integer, String> dic = new
        DictionaryDoubleHashing<>(10);
        dic.put(4371,"A");
        dic.writeOut();
        System.out.println("------------------------------");
        dic.put(1323,"B");
        dic.writeOut();
        System.out.println("------------------------------");
        dic.put(6173,"C");
        dic.writeOut();
        System.out.println("------------------------------");
        dic.put(4199,"D");
        dic.writeOut();
        System.out.println("------------------------------");
        dic.put(4344,"E");
        dic.writeOut();
        System.out.println("------------------------------");
        dic.put(9679,"F");
        dic.writeOut();
        System.out.println("------------------------------");
        dic.put(1989,"G");
        dic.writeOut();
        System.out.println("------------------------------");
        System.out.println("Forventet 7 " +dic.size());
        System.out.println("Remove 4199");
        dic.remove(4199);
        System.out.println("Forventet: null "+ dic.get(4199));
        System.out.println("Forventet: G " +dic.get(1989));
        System.out.println("Forventet 6 " +dic.size());
        System.out.println("Forventet G "+ dic.put(1989,"MAD"));
        System.out.println("Forventet 6 " + dic.size());
        System.out.println("Forventet MAD "+dic.get(1989));
        System.out.println("Forventet B " + dic.put(1323,"MAD2"));
        System.out.println("Forventet 6 " + dic.size());
        System.out.println("Forventet MAD2 "+dic.get(1323));
        dic.writeOut();
        System.out.println("------------------------------");
        dic.put(1,"MAD3");
        } 
}
