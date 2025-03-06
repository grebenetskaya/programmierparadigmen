/**
 * The abstract Cost class represents a generic cost related to a building's lifecycle.
 * It contains an `amount` attribute representing the financial value of the cost.
 * This class provides the basic functionality to get and modify the cost amount, while
 * its subclasses implement specific behaviors for increasing the different type of cost based on the building type.
 */
public class Cost {
    private double amount;

    /**
     * Constructor: initializes the cost with the provided amount.
     * @param amount The initial amount of the cost.
     */
    public Cost(double amount){
        this.amount = amount;
    }

    /**
     * Returns the current amount of the cost.
     * @return the current amount of the cost.
     */
    public double getAmount(){
        return this.amount;
    }

    /**
     * Increases the cost by a specified amount.
     * @param toAdd The amount to add to the current cost.
     * Precondition: toAdd ≥ 0.
     * Postcondition: The cost amount is increased by toAdd.
     * Invariant: amount ≥ 0.
     */
    public void increaseAmount(double toAdd){
        // ERROR: The method does not enforce the precondition toAdd ≥ 0.
        // This can lead to amount becoming negative, violating the invariant.
        this.amount += toAdd;
    }
}

// GOOD: The class encapsulates cost data effectively, maintaining high
// cohesion by handling related operations within a single class.

