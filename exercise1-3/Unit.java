import java.util.Random;
/**
 * The Unit class represents a generic unit within a building, providing properties for tracking
 * resident satisfaction, renovation history, and quantity of residents. It includes methods to
 * adjust satisfaction, perform renovations, and reset conditions. Specific unit types
 * (e.g., residential, non-residential) extend this class to define particular behaviors.
 */
public abstract class Unit {
    private double satisfaction;  // Invariant: satisfaction ≥ 0
    private double initialSatisfaction;  // Invariant: initialSatisfaction ≥ 0
    private int renovationTimes;  // Invariant: renovationTimes ≥ 0
    protected int quantityOfPeople;  // Invariant: quantityOfPeople ≥ 0
    long seed = 15L;


    /**
     * Constructor: Initializes a residential unit with a given satisfaction level and randomizes the number of residents.
     * @param satisfaction >= 0, the initial satisfaction level of the unit.
     * Pre: satisfaction ≥ 0.
     * Post: Creates a ResidentalUnit object with randomized number of residents and the provided satisfaction value.
     * Invariants: satisfaction ≥ 0, initialSatisfaction ≥ 0, renovationTimes = 0.
     */
    public Unit(double satisfaction){
        //Random randomWithSeed = new Random(seed);
        // STYLE: OO - This constructor follows object-oriented principles by initializing
        // the object's state (satisfaction, initialSatisfaction) as attributes of the Unit instance.
        this.satisfaction = satisfaction;
        this.initialSatisfaction = satisfaction;
        this.renovationTimes = 0;
    }

    /**
     * Simulates the renovation of the residential unit. Resets the time since the last renovation
     * and adjusts the satisfaction level based on the number of renovations performed.
     * Pre: -
     * Post: Resets the timeFromRenovation to 0 and adjusts the satisfaction value to ensure it does not fall below 0.
     */
    public void renovate(){
        renovationTimes += 1;
        // STYLE: Procedural - Here we see a procedural style, where the method executes a series
        // of steps sequentially, adjusting satisfaction based on a formula, rather than depending
        // on other object interactions.
        // BAD: The method uses a constant from the Constants class without checking if it's properly initialized, leading to tight coupling.
        // A better solution would be to pass the coefficient as a parameter or encapsulate it within the Unit class.
        this.satisfaction = Math.max(0, initialSatisfaction - Constants.COEFF_FOR_SATISFACTION_FUNCTION_BY_RENOVATION * renovationTimes);
    }
    /**
     * Revitalizes the unit by resetting the renovation count and restoring initial satisfaction level.
     * Pre: -
     * Post: Satisfaction is restored to its initial level, renovationTimes is set to 0.
     * Invariants: satisfaction ≥ 0, renovationTimes = 0.
     */
    public void revitalise(){
        renovationTimes = 0;
        this.satisfaction = initialSatisfaction;
    }

    /**
     * Adjusts the satisfaction level of the residents by a specified amount.
     * The satisfaction cannot drop below 0.
     * @param change, the amount to adjust the satisfaction by (can be positive or negative).
     * Pre: -
     * Post: Updates the satisfaction level, ensuring it remains non-negative.
     * Invariants: satisfaction ≥ 0.
     */
    public void resetSatisfaction(double change) {     // зміна насолодження протягом часу
        // STYLE: Procedural - This method uses a sequential, step-by-step approach to adjust satisfaction,
        // focusing solely on modifying the value without interacting with other objects.
        this.satisfaction = Math.max(0, this.satisfaction + change);

    }
    /**
     * Returns the current satisfaction level of the residents in the unit.
     * @return the current satisfaction level.
     * Pre: -
     * Post: Returns satisfaction; satisfaction remains unchanged.
     */
    public double getSatisfaction() {
        return this.satisfaction;
    }


    /**
     * Returns the number of people in the unit.
     *
     * @return The quantity of people.
     * Pre: None.
     * Post: Returns quantityOfPeople; quantityOfPeople remains unchanged.
     * Invariants: quantityOfPeople ≥ 0.
     */
    public abstract int getQuantityOfPeople();
}

/**
 * The ResidentalUnit class represents an individual housing unit in a building.
 * It tracks the number of residents, their satisfaction level, and the time since the last renovation.
 * The satisfaction of residents can change over time or due to renovations.
 */
class ResidentalUnit extends Unit{
    private int quantityOfPeople;

    /**
     * Constructor: Initializes a residential unit with a given satisfaction level and randomizes the number of residents.
     * @param satisfaction >= 0, the initial satisfaction level of the unit.
     * Pre: satisfaction >=0.
     * Post: Creates a ResidentalUnit object with randomized number of residents and the provided satisfaction value.
     * Invariants: satisfaction ≥ 0, quantityOfPeople ≥ 1.
     */
    public ResidentalUnit(double satisfaction){
        super(satisfaction);
        Random random = new Random();
        quantityOfPeople = Math.max((int)(3 + 2 * random.nextGaussian()), 1);
    }

    /**
     * Returns the current number of residents living in the unit.
     * @return the number of residents in the unit.
     * Pre: -
     * Post: Returns quantityOfPeople; quantityOfPeople remains unchanged.
     * Invariants: quantityOfPeople ≥ 1.
     */
    public int getQuantityOfPeople() {
        return this.quantityOfPeople;
    }
}

/**
 * The NonResidentialUnit class represents a unit in a building not used for residential purposes.
 * It has a default quantity of people set to 100 and allows tracking of satisfaction levels.
 */
class NonResidentialUnit extends Unit{

    private int quantityOfPeople = 100;
    /**
     * Constructor: Initializes a non-residential unit with a specified satisfaction level.
     *
     * @param satisfaction Initial satisfaction level for the unit (must be >= 0).
     * Pre: satisfaction >= 0;
     * Post: Creates a NonResidentialUnit with a default quantity of people and specified satisfaction.
     * Invariants: satisfaction ≥ 0, quantityOfPeople = 100.
     */
    public NonResidentialUnit(double satisfaction){
        super(satisfaction);
    }
    /**
     * Returns the number of people using the non-residential unit.
     *
     * @return Number of people in the unit.
     * Pre: None.
     * Post: Returns quantityOfPeople; quantityOfPeople remains unchanged.
     * Invariants: quantityOfPeople = 100.
     */
    public int getQuantityOfPeople() {
        return this.quantityOfPeople;
    }


}

