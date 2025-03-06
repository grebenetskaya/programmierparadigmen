package Aufgabe8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CubeArrangementSearcher {

    public static List<CubeArrangement> searchArrangements (int n, int m, int k, RatingFunction ratingFunction) {
        if (n < 1) {
            throw new IllegalArgumentException("Number of cubes n has to be at least 1");
        } else if (n == 1) {
            return List.of(CubeArrangement.singleCube());
        } else {
            // gehe rekursiv weiter vor

            List<CubeArrangement> smallerSolutions = searchArrangements(n-1, m, k, ratingFunction);

            List<CubeArrangement> allNewSolutions = smallerSolutions.stream()
                            .flatMap(arr -> arr.addOneCubeAllPossible(m).stream())
                            .toList();



            return selectTopK(allNewSolutions, ratingFunction, k);

        }

        //return null;
    }

    public static List<CubeArrangement> selectTopK(
            List<CubeArrangement> arrangements,
            RatingFunction ratingFunction,
            int k
    ) {
        return arrangements.stream()
                .sorted(Comparator.comparing(ratingFunction::rate).reversed())
                .limit(k)
                .toList();
    }
}
