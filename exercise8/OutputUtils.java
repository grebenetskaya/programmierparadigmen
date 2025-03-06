package Aufgabe8;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Contains auxiliary methods for outputting the results.
 *  * We separate the "side effect" (console) from the logic.
 */
public class OutputUtils {
    /**
     * Returns the parameters (n, m, k) and the best & worst solution
     * with ratings.
     */
    public static void printResults(
            int n,
            int m,
            int k,
            List<CubeArrangement> solutions,
            RatingFunction ratingFunction
    ) {
        System.out.println("Parameter: n=" + n + ", m=" + m + ", k=" + k);

        if (solutions.isEmpty()) {
            System.out.println("Keine Lösungen gefunden!");
            return;
        }


        List<ScoredArrangement> scored = solutions.stream()
                .map(a -> new ScoredArrangement(a, ratingFunction.rate(a)))
                .collect(Collectors.toList());

        // Finde beste und schlechteste
        scored.sort((o1, o2) -> Double.compare(o2.score, o1.score));

        ScoredArrangement best = scored.get(0);
        ScoredArrangement worst = scored.get(scored.size()-1);

        System.out.println("Number of solutions found: " + solutions.size());
        System.out.println();

        System.out.println("--- Best solution ---");
        System.out.println("Score: " + best.score);
        System.out.println(best.arr.toAsciiArt());

        System.out.println("--- Worst solution ---");
        System.out.println("Score: " + worst.score);
        System.out.println(worst.arr.toAsciiArt());

        System.out.println("=====================================");
        System.out.println();
    }

    /**
     * Small auxiliary class: Arrangement + Score.
     */
    static class ScoredArrangement {
        CubeArrangement arr;
        double score;
        public ScoredArrangement(CubeArrangement a, double s) {
            this.arr = a;
            this.score = s;
        }
    }
}