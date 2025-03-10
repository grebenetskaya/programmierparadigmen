import java.util.ArrayList;

/**
 * Class to represent the tuple of results, that are asked in the exercise.
 * Additionally, this class helps to represent values for target function.
 * (Helper class)
 */
public class ValuesTupel {
    //average financial expenditure in the whole building.
    private double avFinancialExpenditureGeneral; // Invariant: avFinancialExpenditureGeneral ≥ 0
    //array to save financial expenditures for decades.
    private ArrayList<Double> decadeFinancialExpenditures;  // Invariant: decadeFinancialExpenditures != null
    //average CO2 emission.
    private double avCO2Emission;  // Invariant: avCO2Emission ≥ 0
    //average waste amount
    private double avWasteAmount; // Invariant: avWasteAmount ≥ 0
    //array to store satisfaction for decades.
    private ArrayList<Double> decadeSatisfactionIndexes; // Invariant: decadeSatisfactionIndexes != null
    private double targetValue; // Invariant: targetValue ≥ 0
    private String buildingType; // Invariant: buildingType != null

    /**
     * Constructor for helper class.
     * @param avFinancialExpenditureGeneral the average financial expenditure in general.
     * @param decadeFinancialExpenditures   the financial expenditures per decade.
     * @param avCO2Emission                 the average CO2 emission.
     * @param avWasteAmount                 the average waste amount.
     * @param decadeSatisfactionIndexes     the satisfaction indexes per decade.
     * @param targetValue                   the target value.
     * @param buildingType                  the type of the building.
     * Pre:
     *   avFinancialExpenditureGeneral ≥ 0
     *   decadeFinancialExpenditures ≠ null
     *   avCO2Emission ≥ 0
     *   avWasteAmount ≥ 0
     *   decadeSatisfactionIndexes ≠ null
     *   targetValue ≥ 0
     *   buildingType ≠ null
     * Post: A ValuesTupel object is created with the specified attributes.
     * Invariants: All instance variables are set to the provided values.
     */
    public ValuesTupel(double avFinancialExpenditureGeneral,
                       ArrayList<Double> decadeFinancialExpenditures,
                       double avCO2Emission, double avWasteAmount,
                       ArrayList<Double> decadeSatisfactionIndexes,
                       double targetValue, String buildingType) {
        // GOOD: High class cohesion - The constructor initializes all necessary fields, ensuring the object is in a valid state.
        // BAD: The constructor does not enforce the preconditions, which could lead to invalid object states.
        this.avFinancialExpenditureGeneral = avFinancialExpenditureGeneral;
        this.decadeFinancialExpenditures = decadeFinancialExpenditures;
        this.avCO2Emission = avCO2Emission;
        this.avWasteAmount = avWasteAmount;
        this.decadeSatisfactionIndexes = decadeSatisfactionIndexes;
        this.targetValue = targetValue;
        this.buildingType = buildingType;
    }


    /**
     * Returns target value.
     * @return target value.
     * Pre: -
     * Post: Returns the target value without modification.
     */
    public double getTargetValue() {
        return targetValue;
    }

    /**
     * String representation of the results.
     * @param numberOfResidents != 0
     * @return string representation of the values.
     * Pre: numberOfResidents > 0
     * Post: Returns a formatted string with calculated values.

     */
    public String toString(int numberOfResidents) {
        // BAD: The method does not check if numberOfResidents > 0, which could lead to division by zero.
        StringBuilder sb = new StringBuilder();
        sb.append("Type of building: " + buildingType + "\n");
        sb.append("i. The average financial expenditure per resident per year: ").append(avFinancialExpenditureGeneral).append("\n");
        sb.append("ii. Distribution of the average financial expenditure per resident per year over decades: ").append("\n");
        for (int j = 0; j < decadeFinancialExpenditures.size(); j++) {
            // GOOD: Using indices in the loop makes it clear which decade is being processed.
            sb.append((j + 1)).append("-th decade: ").append(decadeFinancialExpenditures.get(j) / numberOfResidents).append("\n");
        }
        // sb.append("\n");
        sb.append("iii. Average CO2 emissions per resident per year caused by the building measured in tons of CO2: ").append(avCO2Emission).append("\n");
        sb.append("iv. Average amount of non-recycled waste generated by the building per resident per year in tons: ").append(avWasteAmount).append("\n");
        sb.append("v. An index for the average satisfaction with housing quality per decade: ").append("\n");
        for (int j = 0; j < decadeSatisfactionIndexes.size(); j++) {
            // BAD: Duplicated logic for iterating over decades; could be refactored to a separate method to improve maintainability.
            sb.append((j + 1) + "-th decade: " + (decadeSatisfactionIndexes.get(j))).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }


}
