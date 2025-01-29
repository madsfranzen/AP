/*Algoritme prefixAverage.

Du skal lave en metode der givet et array af heltal beregner prefix gennemsnittet
af tallene i arrayet.

Prefix gennemsnittet af et array er et nyt array hvor indeks _i_ indeholder
gennemsnittet af tallene på indeksplads _0_ til _i_ , i det oprindelige array. Dette kan
illustreres med nedenstående eksempel:

Givet arrayet

{5, 10, 5, 6, 4, 9, 8}

Er prefixarrayet

{5.0, 7.5, 6.667, 6.5, 6.0, 6.5, 6.714}

Metoden har følgende signatur:

```java
public static double[] prefixAverage(int[] inputTal)
```

Programmer metoden så den får den mindst mulige tidskompleksitet.*/

import java.util.Arrays;

public class opgave3 {
    public static void main(String[] args) {

        System.out.println("Opgave 3:");

        int[] originalArray = { 5, 10, 5, 6, 4, 9, 8 };

        System.out.println(Arrays.toString(originalArray));
        System.out.println("Generated prefixarray:");
        System.out.println(Arrays.toString(prefixAverage(originalArray)));
    }

    public static double[] prefixAverage(int[] inputTal) {
        double[] prefixArray = new double[inputTal.length];
        int sum = 0;
        for (int i = 0; i < inputTal.length; i++) {
            sum += inputTal[i];
            prefixArray[i] = sum / (i + 1.0);
        }
        return prefixArray;
    }
}
