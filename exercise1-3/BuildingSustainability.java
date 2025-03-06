import java.util.*;

/**
 * This abstract class represents a generic building structure with attributes related to renovation periods,
 * expected lifetime, and material composition. It provides a base for managing and simulating the lifecycle of
 * buildings with different sustainability characteristics, like minimal, eco-friendly, and high-quality buildings.
 * Subclasses specify unique configurations of materials and sustainability for different building types.
 */

public abstract class BuildingSustainability {

    protected double renovationPeriodAverage; //Invariant: > 0
    protected double lifetimeOfConstructionExpected; //Invariant: > 0
    protected Map<MaterialType, Double> materialComposition = new TreeMap<>(new MaterialTypeComparator());// Material composition percentages for the building





    /**
     * Constructs a new BuildingSustainability with the given parameters.
     * @param renovationPeriodAverage Average period for renovations
     * @param lifetimeOfConstructionExpected Expected building lifespan in years
     * @param concretePercentage Percentage of concrete used
     * @param woodPercentage Percentage of wood used
     * @param glassPercentage Percentage of glass used
     * @param brickPercentage Percentage of brick used
     * @param naturalStonePercentage Percentage of natural stone used
     * @param plasterboardPercentage Percentage of plasterboard used
     * @param metalPercentage Percentage of metal used
     * @param asphaltPercentage Percentage of asphalt used
     * Precondition: Sum of all material percentages must equal 1; renovationPeriodAverage > 0; lifetimeOfConstructionExpected > 0.
     * Postcondition: Initializes the building object with specified attributes.
     * Invariants: All class invariants are established.
     * */
    public BuildingSustainability(double renovationPeriodAverage, double lifetimeOfConstructionExpected,
                                  double concretePercentage, double woodPercentage, double glassPercentage,
                                  double brickPercentage, double naturalStonePercentage, double plasterboardPercentage,
                                  double metalPercentage, double asphaltPercentage){ // , double asphaltPercentage
        if (Math.abs(concretePercentage + woodPercentage + glassPercentage + brickPercentage + naturalStonePercentage +
                plasterboardPercentage + metalPercentage - 1) > 0.0001) {
            throw  new IllegalArgumentException("Sum of percentages should be 1.");
        }
        // STYLE: OO - This constructor sets the initial state for each instance of BuildingSustainability,
        // establishing the material composition and expected lifespan as encapsulated properties of the object.
        // GOOD: High cohesion - Constructor initializes all necessary attributes, ensuring object consistency.

        this.renovationPeriodAverage = getRandomRenovationInterval(renovationPeriodAverage, new Random());
        this.lifetimeOfConstructionExpected = lifetimeOfConstructionExpected;
        materialComposition.put(new Concrete(), concretePercentage);
        materialComposition.put(new Wood(), woodPercentage);
        materialComposition.put(new Glass(), glassPercentage);
        materialComposition.put(new Brick(), brickPercentage);
        materialComposition.put(new NaturalStone(), naturalStonePercentage);
        materialComposition.put(new Plasterboard(), plasterboardPercentage);
        materialComposition.put(new Metal(), metalPercentage);
        materialComposition.put(new Asphalt(), asphaltPercentage);

    }

    /**
     * Generates a random renovation interval based on the average renovation period and a normal distribution.
     *
     * @param averagePeriod The average time between renovations.
     * @param random A random number generator.
     * @return A renovation interval greater than or equal to 1.
     * Pre: averagePeriod must be greater than 0.
     * Post: Returns a valid renovation interval in years.
     */
    public int getRandomRenovationInterval(double averagePeriod, Random random) {
        // STYLE: Procedural - This method is procedural because it generates a random value
        // without interacting with other objects or relying on instance-specific attributes.
        // BAD: This method uses a Random object passed as a parameter, which can lead to inconsistent behavior if different Random instances are used.
        // It would be better to have a single Random instance for the class or use a thread-safe random number generator.
        double interval = averagePeriod + random.nextGaussian() * Constants.STANDARD_DEVIATION;
        return Math.max((int) Math.round(interval), 1);
    }


}

/**
 * Represents a minimal sustainability building with standard material composition and an average renovation
 * period and expected lifetime. This class defines a simpler, low-cost building model with average durability.
 *  Pre: -
 * Post: A MinimalBuildingSustainability object is created with predefined material attributes and lifespan.
 */
class MinimalBuildingSustainability extends BuildingSustainability {
    public MinimalBuildingSustainability () {
        super(20, Constants.LIFETIME_EXP_M, 0.40,0,0,
                0.45,0,0.15,0, 0);
        // GOOD: Weak coupling - The subclass relies on the superclass constructor, reducing duplication and dependencies.

    }
}
/**
 * Represents an eco-friendly building with sustainability features that lower environmental impact.
 * This includes higher proportions of recyclable materials, reduced CO2 emissions, and a moderate lifespan.
 * Pre: -
 * Post: An EcoBuildingSustainability object is created with eco-friendly materials and a sustainable lifespan.
 */
class EcoBuildingSustainability extends BuildingSustainability {
    public EcoBuildingSustainability() {
        super(20, Constants.LIFETIME_EXP_E, 0.30,0.50,0,
                0,0.20,0,0, 0);
        // GOOD: Use of inheritance - Enhances code reuse and logical organization.

    }
}
/**
 * Represents a high-quality building with durable materials, extended lifespan, and high user satisfaction.
 * This model uses a more expensive material composition, supporting longer building life and reduced renovation needs.
 * Pre: -
 * Post: A HighQualityBuildingSustainability object is created, providing a durable, high-quality building model.
 */
class HighQualityBuildingSustainability extends BuildingSustainability {

    public HighQualityBuildingSustainability() {
        super(25, Constants.LIFETIME_EXP_HQ, 0.30,0,0.30,
                0,0.40,0,0, 0);
        // GOOD: Use of inheritance - Enhances code reuse and logical organization.

    }
}
// GOOD: The subclasses do not strengthen preconditions or weaken postconditions, satisfying the Liskov Substitution Principle.
// GOOD: The design uses polymorphism by allowing different types of BuildingSustainability to be used interchangeably, enhancing flexibility.
