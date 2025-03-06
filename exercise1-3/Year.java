/**
 * The Year class represents a time span in years used in the simulation.
 * It stores the number of past years and provides methods to retrieve and update this time step by step.
 */
public class Year {
    private int numberOfYearsPassed; // Invariant: numberOfYearsPassed ≥ 0

    /**
     * Default constructor for the Year class.
     * Initializes the number of years passed to 0.
     * Pre: -
     * Post: A Year object is created with 0 years passed.
     * Invariant: numberOfYearsPassed ≥ 0.
     */
    public Year () {
        this.numberOfYearsPassed = 0;
    }

    /**
     * Parameterized constructor for the Year class.
     * Initializes the Year object with the specified number of years passed.
     * @param numberOfYearsPassed > 0, the number of years that have passed (must be > 0).
     * Pre: numberOfYearsPassed ≥ 0.
     * Post: A Year object is created with the specified number of years passed.
     * Invariant: numberOfYearsPassed ≥ 0.
     */
    public Year (int numberOfYearsPassed) {
        // ERROR: The constructor does not enforce the precondition numberOfYearsPassed ≥ 0.
        // This can lead to numberOfYearsPassed being negative, violating the class invariant.
        this.numberOfYearsPassed = numberOfYearsPassed;
    }

    /**
     * Increases the number of years passed by one.
     * Pre: -
     * Post: Increments the number of years passed by one.
     * Invariant: numberOfYearsPassed ≥ 0.
     */
    public void oneYearPasses() {
        // GOOD: The method ensures that numberOfYearsPassed is always incremented positively, maintaining the invariant.
        numberOfYearsPassed += 1;
    }
    /**
     * Returns the current number of years that have passed.
     * @return the current number of years passed.
     * Pre:-
     * Post: The number of years passed is returned unchanged.
     * Invariant: numberOfYearsPassed ≥ 0.
     */
    public int getNumberOfYearsPassed() {
        return numberOfYearsPassed;
    }

    // GOOD: High class cohesion as the Year class is solely responsible for managing the passage of years.
    // It encapsulates all related data and behavior.

    // GOOD: Encapsulation is properly used; the numberOfYearsPassed field is private and can only be accessed or modified through public methods.

    // BAD: The class does not provide methods to reset or decrease the number of years, which might be needed in some simulation scenarios.
    // Adding such methods could enhance the class's utility.



}

