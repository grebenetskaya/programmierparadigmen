/**
 * The CO2 class represents the amount of CO2 emissions associated with a building.
 * It keeps track of the total CO2 emissions and provides methods to retrieve and increase this amount.
 * The increase in CO2 emissions depends on the type of building, reflecting different environmental impacts.
 **/
public class CO2 {
    private double amount;  // Invariant: amount ≥ 0

    /**
     * Default constructor initializing CO2 amount to zero.
     * Precondition: None.
     * Postcondition: amount == 0.
     * Invariant: amount ≥ 0.
     */
    public CO2(){
        this.amount = 0;
    }
    /**
     * Constructor initializing CO2 amount to a specified value.
     * @param amount The initial amount of CO2 emissions.
     * Precondition: amount ≥ 0.
     * Postcondition: amount == specified amount.
     * Invariant: amount ≥ 0.
     */
    public CO2(double amount){
        // ERROR: The constructor does not enforce the precondition amount ≥ 0, should be handled with Exception.
        // This can lead to an invalid state where amount < 0, violating the invariant.
        this.amount =  amount;
    }
    /**
     * Returns the current amount of CO2 emissions.
     * @return the current amount of CO2 emissions.
     * Precondition: None.
     * Postcondition: amount remains unchanged.
     * Invariant: amount ≥ 0.
     */
    public double getAmount(){
        return this.amount;
    }
    /**
     * Increases the CO2 amount. The increase depends on the type of building
     * @param  toAdd >= 0, building is a valid instance.
     * Precondition: toAdd ≥ 0.
     * Postcondition: amount is increased by toAdd.
     * Invariant: amount ≥ 0.
     */
    public void increaseAmount(double toAdd){          // modifier?
        // ERROR: The method does not enforce the precondition toAdd ≥ 0.
        // This can result in amount becoming negative, violating the invariant.
        this.amount += toAdd;
    }
}

// GOOD: The class encapsulates CO2 emission data effectively,
// maintaining high cohesion by handling related operations within a single class.
// GOOD: The amount variable is private, ensuring encapsulation and
// preventing unauthorized external modifications.

// BAD: Lack of input validation in constructors and methods can lead to invariant violations
// and inconsistent object state.
