# Opgaver onsdag den 29. januar

## Opgave 1

Hvad er størrelsesordenen af tidskompleksiteten (Store O) af nedenstående
metode?

O(n^2)

```java
public void algorithm (int[] array) {
    for (int pass = 1; pass <= array.length; pass++){ 
        for (int index = 0; index < array.length; index++){ 
            for (int count = 0; count < 10; count++){
            // ... Koden her er uafhængig af arryets størrelse
            }
        }
    }
}
```

Hvad er størrelsesordenen af ovenstående metode, hvis tallet 10 erstattes af
array.length i den inderste løkke?

O(n^3)

## Opgave 2

Hvad er størrelsesordenen af tidskompleksiteten (Store O) af nedenstående
metoder?

```java
public boolean isStringHello(String string) {
    if (string.equals("Hello")){
        return true;
    }
    return false;
}

// O(1)

public boolean checkString(String[] strings, String text){
    for (int i = 0; i < strings.length; i++){
        if (strings[i] == text) {
            return true;
        }
    }
    return false;
}

// O(n)

public void methodA (int[] array, int n) {
    for (int index = 0; index < n - 1; index++) {
        int mark = privatemethodA (array, index, n-1); 
        int temp = array[index];
        array[index] = array[mark];
        array[mark] = temp;
    }
}

// O(n2)

private int privatemethodA (int[] array, int first, int last){ 
    int min = array[first];
    int indexOfMin = first;
    for (int index = first + 1; index <= last; index++){
        if (array[index] < min) {
            min = array[index];
            indexOfMin = index;
        }
    }
    return indexOfMin;
}

// O(n)

public int methodB (int n) {
    int result = 0;
    for (int i = 0; i < n; i++){
        for (int j = 1; j < n; j = j * 2){
            result++;
        }
    }
    return result;
}

// O(nlogn)
 ``` 


## Opgave 3

Algoritme prefixAverage.

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

Programmer metoden så den får den mindst mulige tidskompleksitet.

## Opgave 4

Lav en metode der kan beregne det n’te tal i fibnoacci talfølgen. Metoden skal
have lineær størrelsesorden af tidskompleksitet.

```java
public static long fib(long n) {
    long f0 = 0;
    long f1 = 1;
    long f2 = 1;
    if (n == 0) return f0;
    else if (n == 1) return f1;
    else if (n == 2) return f2;
    for (int i = 3; i <= n; i++) { 
        f0 = f1;
        f1 = f2;
        f2 = f0 + f1;
    }
    return f2;
}
```

## Opgave 5

Nedbørsberegning
På Canvas finder du filen nedboer.zip
I filen er der en klasse Nedboer, der indeholder et array over nedbørsmængden i
ml per uge for år 20 22.

Programmer de tre metoder der er specificeret i klassen Nedboer.

1. Find ugenummeret på den uge man skal starte ferie, hvis man ønsker
    mindst nedbør i de tre uger man holder ferie.
2. Find ugenummeret på den uge man skal starte ferie, hvis man ønsker
    mindst nedbør og man med en parameter kan angive, hvor mange på
    hinanden følgende uger, man vil holde ferie.
3. Find ugenummeret på den første uge i den periode hvor nedbøren har
    været præcis den samme flest uger i træk

Hvad er størrelsesordenen af tidskompleksiteten for metoderne?

## Opgave 6

Algoritme belgiens flag:

Du skal lave en metode der givet et array af tegn (de tre tegn ’S’, ’G’ og ’R’) bytter
rundt på tegnene så først kommer alle de sorte ’S’, så de gule ’G’ og til sidst de
røde ’R’, svarende til det belgiske fag.

Såfremt arrayet har følgende udseende ved start
{ G, R, S, G, G, R, S, R, G, G, R, S, G }
Vil elementerne være byttet rundt så arrayet får følgende udseende
{ S, S, S, G, G, G, G, G, G, R, R, R, R}

Du kan ikke vide hvor mange der er af hver farve.

Metoden har følgende signatur:

public static void belgisk_flag(char[] belgisk_flag)

Programmer metoden så den får den mindst mulige tidskompleksitet
Dvs. arrayet må kun gennemløbes én gang og der må ikke bruges et ekstra
hjælpe array.



