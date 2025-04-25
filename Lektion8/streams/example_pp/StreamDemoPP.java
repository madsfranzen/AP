package example_pp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StreamDemoPP {
    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("countries.txt"));

            while (scan.hasNextLine()) {
                wordList.add(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long count = 0;
        for (String w : wordList) {
            if (w.length() > 10) {
                count++;
            }
        }
        System.out.println("Lange ord: " + count);

        count = wordList.stream().filter(w -> w.length() > 10).count();
        System.out.println("Lange ord: " + count);

        int max = wordList.stream()
                .mapToInt(e -> e.length()).max()
                .getAsInt();
        System.out.println("Langste ord: " + max);

//          max = wordList.stream()
//                .mapToInt(e -> e.length())
//                .max().getAsInt();

//        String[] shop = {"Lidl", "Aldi", "Netto", "Føtex", "Lidl", "Netto", "365", "Kvickley", "Netto", "Super Brugsen"};

//        Stream<String> stream = Stream.of(shop);
//        System.out.println(stream.count());
//        System.out.println(stream.distinct().count());
//        System.out.println(Stream.of(shop).distinct().count());

        //System.out.println(Stream.of(shop).limit(5).distinct().count());

//        Object[] result = Stream.of(shop)
//                .limit(5)
//                .sorted().toArray();
//        System.out.println(Arrays.toString(result));

//        Object[] result = Stream.of(shop)
//                .limit(5)
//                .sorted((e1, e2) -> e1.length() - e2.length()).toArray();
//        System.out.println(Arrays.toString(result));

//        Object[] result = Stream.of(shop).map(e -> e.charAt(0)).toArray();
//        System.out.println(Arrays.toString(result));

//        int[] antal = {1, 2, 3, 4, 5, 6};
//        int sum = IntStream.of(antal).reduce(0, (e1, e2) -> e1 + e2);
//        System.out.println("Sum er: " + sum);
//        int mul = IntStream.of(antal).reduce(1, (e1, e2) -> e1 + e2);
//        System.out.println("Mul er: " + mul);
//        System.out.println("Værdierne er: ");
//        System.out.println(IntStream.of(antal).mapToObj(e -> "" + e).reduce((e1, e2) -> e1 + " " + e2).get());


//        List<String> list = wordList.stream()
//                .filter(e -> e.charAt(0) == 'H')
//                .collect(Collectors.toList());
//        System.out.println(list);

//        Map<Character, Long> map =
//                wordList.stream()
//                        .collect(Collectors.groupingBy(e -> e.charAt(0), Collectors.counting()));
//        System.out.println(map);


    }
}
