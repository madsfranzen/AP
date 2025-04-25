package examplewords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Words {
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            long count = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .count();
            System.out.println("Number of words with all five vowels: " + count);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            List<String> examples = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .limit(20)
                    .collect(Collectors.toList());
            System.out.println("20 words with all five vowels: " + examples);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            String shortest = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .min((w1, w2) -> w1.length() - w2.length()).get();
            System.out.println("Shortest: " + shortest);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            List<String> allShortest = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .filter(w -> w.length() == 7)
                    .collect(Collectors.toList());
            System.out.println("All of that length: " + allShortest);
        }
    }

    public static boolean hasFiveVowels(String word) {
        return word.toLowerCase().codePoints()
                .filter(c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                .distinct()
                .count() == 5;
    }
}

