import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Exercises1 {

    public static void main(String[] args) {
        // Liste med tal mellem 1 - 50
        List<Integer> list = List.of(1,2,3,2,1,6,3,4,5,2,3,8,8,9,34,32);
        list.stream().forEach(e-> System.out.println(e+1));

        //  TODO
        //	Udskriver det største element i listen
		Integer max = list.stream().mapToInt(v -> v).max().orElseThrow();
		System.out.println("MAX ELEMENT: " + max);

        //  TODO
        //	Afgør og udskriver om alle tallene i listen er mindre end 50

        //  TODO
        // 	Udskriver antallet af lige tal i listen

        //  TODO
        //	Udskriver antallet af ulige tal i listen

        //  TODO
        //  Udskriver
        //      Gennemsnittet af tallene i listen
        //      Antallet af tallene i listen
        //      Antallet af tallene i listen der er større end gennemsnittet
        //      Antallet af tallene i listen der er mindre end gennemsnittet

        //  TODO
        //	Udskriver antallet af gange de forskellige tal forekommer i listen

        //  TODO
        //	Udskriver antallet af gange de forskellige tal forekommer i listen i sorteret orden
    }
}
