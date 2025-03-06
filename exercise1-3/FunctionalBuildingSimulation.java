import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// STYLE: functional

public class FunctionalBuildingSimulation {

    public static void main(String[] args) {
        List<FunctionalBuilding> buildings = List.of(
                new FunctionalBuilding("EcoHouse", 100, 0.85, 12000, 1.5),
                new FunctionalBuilding("ModernApartment", 150, 0.65, 15000, 2.0),
                new FunctionalBuilding("HistoricLibrary", 90, 0.45, 9000, 1.8)
        );

        // призначаємо статускультурної спадщини рандомно
        buildings.forEach(FunctionalBuilding::assignRandomHeritageStatus);

        // симуляція на 10 років,генеруємо звіт по кожному року
        List<String> report = simulateOverYears(buildings, 10,
                FunctionalBuilding::calculateAnnualMaintenanceCost,
                FunctionalBuilding::ageBuilding,
                FunctionalBuilding::calculateCO2Impact,
                FunctionalBuildingSimulation::simulateDisaster);
        report.forEach(System.out::println);

        // генерація підсумкового звіту про вартість та CO2-вплив
        List<String> scenarioReport = generateReport(buildings,
                FunctionalBuilding::calculateAnnualMaintenanceCost,
                FunctionalBuilding::calculateCO2Impact);
        scenarioReport.forEach(System.out::println);
    }

    // генерація звіту на основі функціональних інтерфейсів
    /**
     *Generates a report based on functional interfaces.
     * @param buildings, list of buildings to generate the report for.
     * @param costFunction, function to calculate the cost.
     * @param impactFunction, function to calculate the CO2 impact.
     * Precondition: buildings, costFunction, and impactFunction are not null.
     * Postcondition: returns a list of formatted report strings.
     */
    public static List<String> generateReport(List<FunctionalBuilding> buildings,
                                              Function<FunctionalBuilding, Double> costFunction,
                                              Function<FunctionalBuilding, Double> impactFunction) {
        // GOOD: Using functional interfaces promotes reusability and decouples the report generation from specific implementations.
        return buildings.stream()
                .map(building -> String.format("Building: %s, Total Cost: %.2f, CO2 Impact: %.2f, Heritage: %b",
                        building.getName(),
                        costFunction.apply(building),
                        impactFunction.apply(building),
                        building.isHeritage()))
                .collect(Collectors.toList());
    }

    // функція для симуляції життєвого циклу, юзаємо паралельні потоки
    /**
     * Simulates the lifecycle over years using parallel streams.
     * @param buildings, list of buildings to simulate.
     * @param years, Number of years to simulate.
     * @param maintenanceCalculator, function to calculate maintenance cost.
     * @param ageCalculator, function to calculate building age.
     * @param co2Calculator, function to calculate CO2 impact.
     * @param disasterSimulator, function to simulate disasters.
     * Precondition: buildings and all functions are not null, years > 0.
     * Postcondition: returns a list of simulation results.
     */
    public static List<String> simulateOverYears(List<FunctionalBuilding> buildings, int years,
                                                 Function<FunctionalBuilding, Double> maintenanceCalculator,
                                                 Function<FunctionalBuilding, Integer> ageCalculator,
                                                 Function<FunctionalBuilding, Double> co2Calculator,
                                                 Function<FunctionalBuilding, String> disasterSimulator) {
        // GOOD: Using CompletableFuture for asynchronous tasks improves performance and keeps the code easy to follow.
        return buildings.stream()
                .flatMap(building -> IntStream.rangeClosed(1, years)
                        .mapToObj(year -> CompletableFuture.supplyAsync(() -> simulateYear(building, year, maintenanceCalculator, ageCalculator, co2Calculator, disasterSimulator))))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    //  симуляція одного року + катастрофи
    /**
     * Simulates one year considering possible disasters.
     * @param building, the building to simulate.
     * @param year, the current year of simulation.
     * @param maintenanceCalculator, function to calculate maintenance cost.
     * @param ageCalculator, function to calculate building age.
     * @param co2Calculator, function to calculate CO2 impact.
     * @param disasterSimulator, function to simulate disasters.
     * Precondition: building and all functions are not null, year > 0.
     * Postcondition: returns a string with simulation results for the year.
     */
    private static String simulateYear(FunctionalBuilding building, int year,
                                       Function<FunctionalBuilding, Double> maintenanceCalculator,
                                       Function<FunctionalBuilding, Integer> ageCalculator,
                                       Function<FunctionalBuilding, Double> co2Calculator,
                                       Function<FunctionalBuilding, String> disasterSimulator) {
        String disasterResult = disasterSimulator.apply(building);
        double maintenanceCost = maintenanceCalculator.apply(building);
        int age = ageCalculator.apply(building);
        double co2Impact = co2Calculator.apply(building);

        return String.format("Year %d | Building: %s | Maintenance Cost: %.2f | Age: %d | CO2 Impact: %.2f | Heritage: %b | %s",
                year, building.getName(), maintenanceCost, age, co2Impact, building.isHeritage(), disasterResult);
    }

    // симуляція катастрофи для будівлі
    /**
     * Simulates a disaster for a building.
     * @param building, the building to simulate the disaster for.
     * Precondition: building != null.
     */
    public static String simulateDisaster(FunctionalBuilding building) {
        int disasterDamage = new Random().nextInt(50); // Випадкове пошкодження від 0 до 49
        String repairSource = (disasterDamage > 40 || building.isHeritage()) ? "government" : "owner";
        building.repair(repairSource);

        return String.format("Disaster Damage: %d | Repair funded by: %s", disasterDamage, repairSource);
    }
}


/**
 * The FunctionalBuilding class represents a building with properties and methods to simulate its lifecycle.
 * It includes attributes like name, maximum residents, condition, maintenance cost, CO2 impact factor, and heritage status.
 */
class FunctionalBuilding {
    private final String name;
    private final int maxResidents;
    private double condition; // Стан будівлі від 0 до 1
    private final double maintenanceCostPerResident; // Invariant: maintenanceCostPerResident ≥ 0
    private int currentResidents;
    private final double co2Factor; // Фактор екологічного впливу
    private boolean heritage;

    /**
     * Constructs a new FunctionalBuilding.
     * @param name, the name of the building.
     * @param maxResidents, maximum number of residents.
     * @param condition, the initial condition of the building (between 0 and 1).
     * @param maintenanceCostPerResident, the maintenance cost per resident.
     * @param co2Factor, the CO2 impact factor.
     * Precondition: name is not null; maxResidents > 0; 0 ≤ condition ≤ 1; maintenanceCostPerResident ≥ 0; co2Factor ≥ 0.
     * Postcondition: a new FunctionalBuilding object is created.
     * Invariants: all class invariants are established.
     */
    public FunctionalBuilding(String name, int maxResidents, double condition, double maintenanceCostPerResident, double co2Factor) {
        // ERROR: The constructor does not enforce the preconditions, potentially allowing invalid values that violate class invariants.
        // Should be handled with exception.
        this.name = name;
        this.maxResidents = maxResidents;
        this.condition = condition;
        this.maintenanceCostPerResident = maintenanceCostPerResident;
        this.co2Factor = co2Factor;
        this.currentResidents = (int) (maxResidents * condition);
    }

    /**
     * Returns the name of the building.
     * @return the name of the building.
     * Precondition: none.
     * Postcondition: the name is returned.
     */
    public String getName() { return name; }

    /**
     * Checks if the building has heritage status.
     * @return true if the building is heritage, false otherwise.
     * Precondition: none.
     * Postcondition: the heritage status is returned.
     */
    public boolean isHeritage() { return heritage; }

    /**
     * Assigns heritage status randomly.
     * Precondition: none.
     * Postcondition: the heritage status is randomly assigned.
     */
    public void assignRandomHeritageStatus() {
        heritage = new Random().nextBoolean(); // 50% ймовірність
        System.out.printf("Building %s heritage status: %b\n", name, heritage);
    }

    /**
     * Ages the building by decreasing its condition.
     * @return the age of the building in percentage 0-100.
     * Precondition: condition ≥ 0.
     * Postcondition: condition is decreased by 0.02, but not smaller than 0.
     * Invariant: 0 ≤ condition ≤ 1.
     */
    public int ageBuilding() {
        condition -= 0.02;
        condition = Math.max(0, condition);
        return (int) (100 * (1 - condition));
    }

    /**
     * Repairs the building.
     * @param financier, who funds the repair (government or owner).
     * Precondition: financier is not null and is either "government" or "owner".
     * Postcondition: condition is increased by 0.3, not exceeding 1.
     * Invariant: 0 ≤ condition ≤ 1.
     */
    public void repair(String financier) {
        // ERROR: The method does not check if financier is valid, potentially leading to incorrect behavior.
        if ("government".equals(financier) || heritage) {
            System.out.printf("Government repairs %s. Condition restored.\n", name);
        } else {
            System.out.printf("Owner repairs %s. Condition partially restored.\n", name);
        }
        condition = Math.min(1.0, condition + 0.3);
    }

    /**
     * Calculates the annual maintenance cost.
     * @return the annual maintenance cost.
     * Precondition: currentResidents ≥ 0, maintenanceCostPerResident ≥ 0.
     * Postcondition: returns the product of currentResidents and maintenanceCostPerResident.
     */
    public double calculateAnnualMaintenanceCost() {
        return currentResidents * maintenanceCostPerResident;
    }

    /**
     * Calculates the CO2 impact of the building.
     * @return the CO2 impact.
     * Precondition: co2Factor ≥ 0, 0 ≤ condition ≤ 1.
     * Postcondition: returns the product of co2Factor and (1 - condition).
     */
    public double calculateCO2Impact() {
        return co2Factor * (1 - condition);
    }
}

// GOOD: Weak coupling since the class does not depend on other classes, enhancing reusability and maintainability.
