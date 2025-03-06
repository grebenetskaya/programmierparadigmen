import java.util.Map;
import java.util.TreeMap;

/**
 * The abstract Material class represents a general material used in the construction, waste, or recycling process of a building.
 * Each material has a quantity, a CO2 emission factor, and a reusability coefficient, which defines how much of it can be reused.
 * It also provides methods for getting the material's quantity and CO2 value, increasing the quantity, and resetting it to zero.
 * Pre: The reusability parameter must be between 0 and 1, throws IllegalArgumentException otherwise.
 * Post: Material object is initialized with given attributes.
 */
public class Material {

    // minimal: 0.4 concrete, 0.15 plasterboard, 0.45 brick
    // eco: 0.3 concrete, 0.2 natural stone, 0.5 wood
    // high quality 0.3 concrete, 0.4 natural stone, 0.3 glass
    private double quantity = 0;  //Invariant: >=0
    protected Map<MaterialType, Double> materialComposition; // in percent

    /**
     * Constructs a Material object with a specified quantity and material composition.
     * @param quantity, initial quantity of material.
     * @param materialComposition, composition of material types and their respective percentages.
     * Pre: each material type percentage is between 0 and 1, and their sum should equal 1.
     * Post: initializes the material object with the specified attributes.
     */
    public Material(double quantity, Map<MaterialType, Double> materialComposition){
        // STYLE: OO - This constructor is object-oriented as it sets the initial state of the Material
        // object, including quantity and materialComposition, as attributes specific to each instance.
        this.quantity = quantity;
        this.materialComposition = materialComposition;
    }
    /**
     * Returns the current quantity of the material.
     * @return Current quantity of the material.
     */
    public double getQuantity(){
        return this.quantity;
    }

    /**
     * Increases the quantity of the material by a specified amount.
     * @param toAdd, the amount to add to the current quantity (must be >= 0).
     * Pre: toAdd must be non-negative.
     * Post: the quantity is increased by the specified amount.
     * Invariant: quantity ≥ 0.
     */
    public void increaseQuantity(double toAdd){
        this.quantity += toAdd;
    }


    /**
     * Resets the material's quantity to zero.
     * Precondition: none.
     * Postcondition: quantity == 0.
     * Invariant: quantity ≥ 0.
     */
    public void setQuantityToZero() {
        this.quantity = 0;
    }
    /**
     * Calculates the total monetary value of the current material based on its composition and construction costs.
     * @return total cost of the material in Euros.
     * Pre: material composition and quantity must be initialized, quantity ≥ 0.
     * Post: returns the calculated monetary value based on construction costs.
     */
    public double getCurrentMaterialMoney() {
        // STYLE: Procedural - This method follows a procedural style by performing a calculation
        // independently of the object's state. It aggregates values from the materialComposition map
        // in a series of steps and returns a final result.
        double sumOfMoney = 0;
        for (Map.Entry<MaterialType, Double> entry : this.materialComposition.entrySet()){
            // BAD: No null check for entry.getKey(), which could cause NullPointerException.
            sumOfMoney += this.quantity * entry.getKey().constructionCost * entry.getValue();
        }
        // GOOD: Method is side-effect free, enhancing reliability and testability.
        return sumOfMoney;
    }
}
// BAD: The materialComposition is protected, allowing subclasses or same-package classes to modify
// it directly, potentially violating encapsulation. A better approach would be to make it private.

// BAD: The class does not override equals() and hashCode(),
// which may lead to unexpected behavior when Material objects are used in collections.
