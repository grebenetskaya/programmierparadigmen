import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
/*
      NOT RELEVANT
 */
/**
 * The Test class is responsible for running simulations on different building types
 * and calculating various sustainability metrics for each scenario.
 * It simulates multiple building types and computes metrics such as financial expenditure,
 * CO2 emissions, waste generation, and resident satisfaction as well as the target function,
 * which shows the level of sustainability for each scenario.
 * For the output of the key figures, a simulation runs with the most average result
 * (median) of the target function for each scenario.
 */
public class TestForTest {
    public static void main(String[] args) {
        System.out.println("New start: ");

        // Create an ArrayList with different building types to simulate.
        ArrayList<BuildingSustainability> buildingSustainabilityTypes = new ArrayList<>(Arrays.asList(
                new MinimalBuildingSustainability(),
                new EcoBuildingSustainability(),
                new HighQualityBuildingSustainability()
        ));
        Random random = new Random();


        double livingArea;
        double probabilityArea = random.nextDouble();  // Генеруємо випадкове число від 0 до 1
        if (probabilityArea < 0.51)
            livingArea = Constants.CITY;
        else if (probabilityArea < 0.51 + 0.13)
            livingArea = Constants.SUBURBAN_AREA;
        else
            livingArea = Constants.VILLAGE;



        // Iterate over each building type to simulate their lifecycle and output results.
        for (BuildingSustainability b : buildingSustainabilityTypes) {
            String buildingType = "";
            if (b instanceof MinimalBuildingSustainability)
                buildingType = "Minimal building";
            else if (b instanceof EcoBuildingSustainability)
                buildingType = "Ecological building";
            else if (b instanceof HighQualityBuildingSustainability)
                buildingType = "High-quality building";
            System.out.println(buildingType + ": ");

            // Initialize lists to store target values and the results (tupels) for each simulation.
            ArrayList<Double> targetValues = new ArrayList<>();
            ArrayList<ValuesTupel> tupelsOfAnswers = new ArrayList<>();

            int numOfResidentsGl = 0;
            for (int i = 1; i <= Constants.NUMBER_OF_RUNS_OF_SIMULATION; i++) {
                Random randomB = new Random();
                BuildingType buildingT;
                double probabilityType = randomB.nextDouble();  // Генеруємо випадкове число від 0 до 1
                if (probabilityType < 0.48)
                    buildingT = new PrivatHouse(1); // подивитись, може справити
                else if (probabilityType < 0.48 + 0.27)
                    buildingT = new ApartmentHouse(50, 1 ); // подивитись, може справити
                else if (probabilityType < 0.48 + 0.27 + 0.1)
                    buildingT = new PublicInstitution(1);
                else
                    buildingT = new IndustrialBuilding(1);
                // Reinitialize Year and Building instances for each simulation run to maintain independence.
                Year currentYear = new Year();
                Building construction;
                if (b instanceof MinimalBuildingSustainability)
                    construction = new Building(new MinimalBuildingSustainability(), buildingT, livingArea);
                else if (b instanceof EcoBuildingSustainability)
                    construction = new Building(new EcoBuildingSustainability(), buildingT, livingArea);
                else if (b instanceof HighQualityBuildingSustainability)
                    construction = new Building(new HighQualityBuildingSustainability(), buildingT, livingArea);
                else
                    continue;


                // Initialize lists to store expenditures and satisfaction over each decade.
                ArrayList<Double> decadeFinancialExpenditures = new ArrayList<>();
                ArrayList<Double> decadeSatisfactionIndexes = new ArrayList<>();
                double costAtStartOfDecade = construction.getGeneralCost();
                int yearsInDecade = 0;
                double sumOfSatisfactionsThroughoutTheDecade = 0;

                // Run the simulation year by year until the building reaches its expected lifetime or is destroyed.
                while (construction.getExistence()) {
                    yearsInDecade += 1;

                    currentYear.oneYearPasses();
                    construction.oneYearPasses();
                    sumOfSatisfactionsThroughoutTheDecade += construction.computeSatisfaction();

                    // At the end of each decade or if the building is destroyed, record financial and satisfaction data.
                    if (currentYear.getNumberOfYearsPassed() % 10 == 0 || !construction.getExistence()) {
                        double currentDecadeCost = construction.getGeneralCost();

                        if (decadeFinancialExpenditures.size() > 0) {
                            currentDecadeCost = construction.getGeneralCost() - decadeFinancialExpenditures.get(decadeFinancialExpenditures.size() - 1);
                            //currentDecadeSatisfaction = construction.computeSatisfaction() - decadeSatisfactionIndexes.get(decadeSatisfactionIndexes.size() - 1);
                        }
                        if (yearsInDecade > 0) {
                            double averageDecadeCost = currentDecadeCost / yearsInDecade;
                            double averageDecadeSatisfaction = sumOfSatisfactionsThroughoutTheDecade/yearsInDecade;
                            decadeFinancialExpenditures.add(averageDecadeCost);
                            decadeSatisfactionIndexes.add(averageDecadeSatisfaction);
                        }
                        yearsInDecade = 0;
                        sumOfSatisfactionsThroughoutTheDecade = 0;
                    }
                }
                // Determine the number of residents in the building after the simulation.
                int numberOfResidents = construction.type.getNumberOfResidents();
                numOfResidentsGl = numberOfResidents;

                // Calculate the average financial expenditure, CO2 emissions, and waste generation per resident per year.
                double avFinancialExpenditureGeneral = construction.getGeneralCost() / (numberOfResidents * currentYear.getNumberOfYearsPassed());
                double avCO2Emission = construction.getCo2().getAmount() / (numberOfResidents * currentYear.getNumberOfYearsPassed());
                double avWasteAmount = construction.getWasteMaterial().getQuantity() / (numberOfResidents * currentYear.getNumberOfYearsPassed());

                // Calculate the target value for the current simulation run.
                double targetValue = targetFunction(avFinancialExpenditureGeneral, avCO2Emission, avWasteAmount);
                targetValues.add(targetValue);

                // Store the calculated metrics in a ValuesTupel instance.
                tupelsOfAnswers.add(new ValuesTupel(avFinancialExpenditureGeneral, decadeFinancialExpenditures, avCO2Emission,
                        avWasteAmount, decadeSatisfactionIndexes, targetValue, construction.type.toString()));
                 System.out.println("Age: " + currentYear.getNumberOfYearsPassed());
                System.out.println("Expected lifetime: " + construction.sustainability.lifetimeOfConstructionExpected);
                System.out.println("Type: " + construction.type);
                System.out.println("Architectural importance: " + construction.isArchitecturalImportant());

                StringBuilder sb = new StringBuilder();
                sb.append("Type of building: " + buildingType + "\n");
                sb.append("i. The average financial expenditure per resident per year: ").append(avFinancialExpenditureGeneral).append("\n");
                sb.append("ii. Distribution of the average financial expenditure per resident per year over decades: ").append("\n");
                for (int j = 0; j < decadeFinancialExpenditures.size(); j++) {
                    sb.append((j + 1)).append("-th decade: ").append(decadeFinancialExpenditures.get(j) / numberOfResidents).append("\n");
                }
                // sb.append("\n");
                sb.append("iii. Average CO2 emissions per resident per year caused by the building measured in tons of CO2: ").append(avCO2Emission).append("\n");
                sb.append("iv. Average amount of non-recycled waste generated by the building per resident per year in tons: ").append(avWasteAmount).append("\n");
                sb.append("v. An index for the average satisfaction with housing quality per decade: ").append("\n");
                for (int j = 0; j < decadeSatisfactionIndexes.size(); j++) {
                    sb.append((j + 1) + "-th decade: " + (decadeSatisfactionIndexes.get(j))).append("\n");
                }
                sb.append("\n");
                System.out.println(sb.toString());

            }


            for (ValuesTupel vt : tupelsOfAnswers) {
                System.out.println(vt.toString(numOfResidentsGl));

            }
        }
    }

    /**
     * Calculates the target value using given coefficients for financial expenditure, CO2 emissions, and waste amount.
     * Pre: avFinancialExpenditureGeneral, avCO2Emission, and avWasteAmount must be >= 0.
     * Post: Returns a target value that represents the overall sustainability metric for the building scenario.
     * @param avFinancialExpenditureGeneral Average financial expenditure per resident per year.
     * @param avCO2Emission Average CO2 emissions per resident per year.
     * @param avWasteAmount Average waste generation per resident per year.
     * @return Calculated target value for the building scenario.
     */
    public static double targetFunction(double avFinancialExpenditureGeneral, double avCO2Emission, double avWasteAmount) {
        // if architecturalImportant then multiply with some coefficient !!!
        return 15.2 + Constants.COEFF_avFinancialExpenditureGeneral * avFinancialExpenditureGeneral + Constants.COEFF_avCO2Emission * avCO2Emission +
                Constants.COEFF_avWasteAmount * avWasteAmount;
    }
}
