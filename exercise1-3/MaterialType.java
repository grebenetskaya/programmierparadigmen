// everything per Unit (100 kg) of material
// CO2 in tones
// Costs in Euros
// reusability from 0 to 1
/**
 * The MaterialType class defines the essential properties of building materials, including construction cost,
 * disposal cost, CO₂ emissions during construction and disposal, and reusability. Each material subclass (e.g.,
 * Concrete, Wood, Metal) represents a specific material with preset values. This structure supports consistent
 * handling of material properties in building lifecycle simulations, facilitating cost, emissions, and waste
 * management.
 */
public abstract class MaterialType {
    public double constructionCost;
    public double disposalCost;
    public double co2Build;
    public double co2Destroy;
    public double reusability;
    /**
     * Constructs a MaterialType with specified values for construction and disposal costs, CO2 emissions, and reusability.
     * @param constructionCost, the cost of constructing with this material per unit.
     * @param disposalCost, the cost of disposing of this material per unit.
     * @param co2Build, CO2 emissions generated during construction per unit.
     * @param co2Destroy, CO2 emissions generated during disposal per unit.
     * @param reusability, the reusability factor of the material (0 to 1).
     */
    public MaterialType (double constructionCost, double disposalCost,
                         double co2Build, double co2Destroy, double reusability) {
        this.constructionCost = constructionCost;
        this.disposalCost = disposalCost;
        this.co2Build = co2Build;
        this.co2Destroy = co2Destroy;
        this.reusability = reusability;
    }
}
/**
 * Represents the material type Concrete with specific costs, CO2 emissions, and reusability.
 */
class Concrete extends MaterialType {
    public Concrete() {
        super(100, 15, 0.15, 0.12, 0.2);
    }
}
/**
 * Represents the material type Wood, characterized by lower CO2 emissions and high reusability.
 */
class Wood extends MaterialType {
    public Wood() {
        super(120, 10, 0.05, 0.02, 0.8);
    }
}
/**
 * Represents the material type Glass with moderate costs, CO2 emissions, and reusability.
 */
class Glass extends MaterialType {
    public Glass() {
        super(200, 25, 0.1, 0.08, 0.3);
    }
}
/**
 * Represents the material type Brick, with balanced costs and reusability.
 */
class Brick extends MaterialType {
    public Brick() {
        super(80, 12, 0.1, 0.07, 0.5);
    }
}
/**
 * Represents the material type NaturalStone, which has moderate costs and high reusability.
 */
class NaturalStone extends MaterialType {
    public NaturalStone() {
        super(150, 20, 0.05, 0.04, 0.7);
    }
}
/**
 * Represents the material type Plasterboard with low costs and low CO2 emissions.
 */
class Plasterboard extends MaterialType {
    public Plasterboard() {
        super(60, 5, 0.03, 0.02, 0.3);
    }
}
/**
 * Represents the material type Metal, which has high costs but is highly reusable.
 */
class Metal extends MaterialType {
    public Metal() {
        super(300, 30, 0.2, 0.15, 0.9);
    }
}
/**
 * Represents the material type Asphalt, with moderate costs and reusability.
 */
class Asphalt extends MaterialType {
    public Asphalt() {
        super(70, 8, 0.08, 0.05, 0.6);
    }
}
