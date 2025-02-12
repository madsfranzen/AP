
/*Nedbørsberegning
På Canvas finder du filen nedboer.zip
I filen er der en klasse Nedboer, der indeholder et array over nedbørsmængden i
ml per uge for år 2022.

Programmer de tre metoder der er specificeret i klassen Nedboer.

1. Find ugenummeret på den uge man skal starte ferie, hvis man ønsker
    mindst nedbør i de tre uger man holder ferie.
2. Find ugenummeret på den uge man skal starte ferie, hvis man ønsker
    mindst nedbør og man med en parameter kan angive, hvor mange på
    hinanden følgende uger, man vil holde ferie.
3. Find ugenummeret på den første uge i den periode hvor nedbøren har
    været præcis den samme flest uger i træk

Hvad er størrelsesordenen af tidskompleksiteten for metoderne?*/

public class opgave5 {

	public static void main(String[] args) {

		int[] nedboerPrUge = { 20, 10, 12, 12, 13, 14, 15, 10, 8, 7, 13,
				15, 10, 9, 6, 8, 12, 22, 14, 16, 16, 18, 23, 12, 0, 2, 0, 0, 78, 0,
				0, 0, 34, 12, 34, 23, 23, 12, 44, 23, 12, 34, 22, 22, 22, 22, 18,
				19, 21, 32, 24, 13 };

		System.out.println("Opgave 5:");
		System.out.println(bedsteTreFerieUger(nedboerPrUge));
		System.out.println(bedsteFerieUgerStart(nedboerPrUge, 3));
		System.out.println(ensNedboer(nedboerPrUge));
	}

	/**
	 * Returnerer ugenummeret for den uge i året, hvor man skal starte ferien,
	 * hvis man ønsker den minimale nedbørsmængde i de tre uger
	 *
	 * @return
	 */

	public static int bedsteTreFerieUger(int[] weeks) {
		int weekToStart = 0;
		int totalRain = weeks[0] + weeks[1] + weeks[2];
		int bestWeeksTotalRain = totalRain;

		for (int i = 1; i < weeks.length - 2; i++) {
			totalRain = weeks[i] + weeks[i + 1] + weeks[i + 2];
			if (totalRain < bestWeeksTotalRain) {
				bestWeeksTotalRain = totalRain;
				weekToStart = i + 1;
			}
		}
		return weekToStart;
	}

	/**
	 * Returnerer ugenummeret for den uge i året, hvor man skal starte ferien,
	 * hvis man ønsker den minimale nedbørsmængde i det "antal" uger, der er
	 * angivet i paramtereren
	 *
	 * @return
	 */

	public static int bedsteFerieUgerStart(int[] weeks, int antal) {
		int weekToStart = 0;
		int totalRain = 0;

		for (int i = 0; i < antal; i++) {
			totalRain += weeks[i];
		}

		int bestWeeksTotalRain = totalRain;

		for (int i = 1; i <= weeks.length - antal; i++) {
			totalRain = totalRain - weeks[i - 1] + weeks[i + antal - 1];

			if (totalRain < bestWeeksTotalRain) {
				bestWeeksTotalRain = totalRain;
				weekToStart = i;
			}
		}

		return weekToStart + 1;
	}

	/**
	 * Returnerer ugenummeret på den første uge hvor nedbøren har været præcis
	 * den samme flest uger i træk
	 *
	 * @return
	 */
	public static int ensNedboer(int[] weeks) {
		int consecutiveWeeks = 0;
		int maxNumConsecutiveWeeks = 0;
		int bestWeek = -1;

		for (int i = 1; i < weeks.length; i++) {
			if (weeks[i] == weeks[i - 1]) {
				consecutiveWeeks++;

				if (consecutiveWeeks > maxNumConsecutiveWeeks) {
					maxNumConsecutiveWeeks = consecutiveWeeks;
					bestWeek = (i + 1) - consecutiveWeeks + 1;
				}

			} else
				consecutiveWeeks = 1;
		}
		System.out.println(maxNumConsecutiveWeeks);
		return bestWeek;
	}
}
