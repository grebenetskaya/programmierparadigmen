package Aufgabe8;
/**
 * Ein funktionales Interface, das eine Würfelanordnung bewertet
 * und einen double-Wert als Score zurückgibt.
 */
@FunctionalInterface
public interface RatingFunction {
    /**
     * Berechnet den "Wert" (Score) eines CubeArrangement.
     * Höher ist besser.
     */
    double rate(CubeArrangement arrangement);
}