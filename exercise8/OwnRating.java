package Aufgabe8;

import java.util.Random;
import java.util.Set;

/**
 * Example of a custom rating function ("OwnRating").
 * The final score is:
 *   - sumOfHeights * 2
 *   - plus a random bonus between 0 and 5 (for variability).
 */
public class OwnRating implements RatingFunction {

    private static final Random RAND = new Random();

    @Override
    public double rate(CubeArrangement arrangement) {
        // Summation of all cube heights * 2
        double sumHeights = sumOfHeights(arrangement) * 2.0;

        // Add a small random part (0..5)
        double randomPart = RAND.nextDouble() * 5.0;

        return sumHeights + randomPart;
    }

    /**
     * @param arrangement the cube arrangement whose heights we want to sum
     * @return the sum of all heights in the arrangement
     */
    private double sumOfHeights(CubeArrangement arrangement) {
        // Retrieve all occupied coordinates
        Set<Coord> occupied = arrangement.getAllOccupiedCoords();

        // Sum up the heights at those coordinates
        return occupied.stream()
                .mapToDouble(c -> arrangement.getHeightAt(c))
                .sum();
    }
}
