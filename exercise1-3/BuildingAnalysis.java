import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


// STYLE: functional

public class BuildingAnalysis {

    /**
     * Evaluates the architectural proportions of the given buildings using the provided filter.
     *
     * @param buildings        List of buildings to evaluate.
     * @param proportionFilter Predicate to filter buildings based on proportion.
     * Precondition: buildings and proportionFilter must not be null.
     * Postcondition: Prints the evaluation of architectural proportions to the console.
     */
    public static void evaluateProportions(List<Building> buildings,
                                           Predicate<Building> proportionFilter) {
        // GOOD: Using Predicate<Building> allows flexible filtering, reducing coupling and increasing reusability.
        System.out.println("Evaluation of architectural proportions (Proportion > 0.8):");

        // BAD: Potential NullPointerException if buildings contain null elements or building.getProportionScore() returns null.
        List<String> proportionResults = buildings.stream()
                .filter(proportionFilter)
                .map(building -> String.format("Building: %s | Proportion: %.2f",
                        building.getClass().getSimpleName(),
                        building.getProportionScore()))
                .collect(Collectors.toList());

        proportionResults.forEach(System.out::println);
    }

    /**
     * Evaluates the dominance of the given buildings using the provided filter.
     *
     * @param buildings       List of buildings to evaluate.
     * @param dominanceFilter Predicate to filter buildings based on dominance.
     * Precondition: buildings and dominanceFilter must not be null.
     * Postcondition: Prints the evaluation of dominance to the console.
     */
    public static void evaluateDominance(List<Building> buildings,
                                         Predicate<Building> dominanceFilter) {
        // GOOD: The method uses streams for concise and readable code, enhancing maintainability.
        System.out.println("\nEvaluation of dominance (Dominance > 0.7):");
        // BAD: No exception handling if building.getDominanceScore() throws an exception.
        List<String> dominanceResults = buildings.stream()
                .filter(dominanceFilter)
                .map(building -> String.format("Building: %s | Dominance: %.2f",
                        building.getClass().getSimpleName(),
                        building.getDominanceScore()))
                .collect(Collectors.toList());

        dominanceResults.forEach(System.out::println);
    }

    /**
     * Calculates the total environmental impact of the given buildings using the provided impact extractor function.
     *
     * @param buildings       List of buildings to calculate impact for.
     * @param impactExtractor Function to extract the environmental impact from a building.
     * Precondition: buildings and impactExtractor must not be null.
     * Postcondition: Prints the total environmental impact to the console.
     */
    public static void calculateEnvironmentalImpact(List<Building> buildings,
                                                    Function<Building, Double> impactExtractor) {
        // GOOD: The use of Function<Building, Double> promotes weak coupling and high cohesion.

        System.out.println("\nEnvironmental impact:");

        double totalEnvironmentalImpact = buildings.stream()
                .map(impactExtractor)
                .mapToDouble(Double::doubleValue)
                .sum();

        System.out.printf("Total environmental impact of buildings: %.2f%n", totalEnvironmentalImpact);
    }
}
