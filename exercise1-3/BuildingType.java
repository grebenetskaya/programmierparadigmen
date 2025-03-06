import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The BuildingType class is an abstract base class that defines properties and methods common to various types
 * of buildings in the simulation. It holds general attributes like area, number of units, material quantity, and
 * a list of units that make up the building. Derived classes can represent specific types of buildings
 * (e.g., private houses, apartment buildings, industrial buildings).
 */
public abstract class BuildingType{

    protected double area;// Invariant:  >= 0
    protected int numberOfUnits = 1; // Invariant:  >= 1

    protected double quantityOfMaterialPerUnit;  // Invariant:  >= 0   // unit = 100kg, 1 quintal
    protected List<Unit> listOfUnits = new ArrayList<>();

    protected double adjazentTerritory;// Invariant:  >= 0

    protected double additionalMetal = 0;// Invariant:  >= 0
    /**
     * Constructs a BuildingType with specified area, number of units, material quantity per unit, and adjacent territory.
     *
     * @param area Area of the building
     * @param numberOfUnits Number of units in the building
     * @param quantityOfMaterialPerUnit Material quantity needed per unit
     * @param adjazentTerritory Area of the adjacent territory
     *  Precondition: area >= 0, numberOfUnits >= 1, quantityOfMaterialPerUnit >= 0, adjazentTerritory >= 0
     * Postcondition: BuildingType object is initialized with specified attributes.
     * Invariants: All class invariants are established.
     */

    public BuildingType(double area, int numberOfUnits, double quantityOfMaterialPerUnit, double adjazentTerritory){
        this.area = area;
        this.numberOfUnits = numberOfUnits;
        this.quantityOfMaterialPerUnit = quantityOfMaterialPerUnit;
        this.adjazentTerritory = adjazentTerritory;
    }
    // getOccupancyRate() для визначення наповнюваності дому

    /**
     * Generates a random value with a Gaussian distribution centered around the mean.
     *
     * @param mean The mean value for the random number
     * @return A random number based on Gaussian distribution with mean and standard deviation of mean * 0.1
     * Precondition: mean > 0
     * Postcondition: Returns a random value based on Gaussian distribution.
     */
    public double getRandom(double mean) {
        //STYLE:PROCEDURAL
        Random random = new Random();
        double stdDev = mean * 0.1;
        return mean + stdDev * random.nextGaussian();
    }

    /**
     * Calculates and returns the total number of residents across all residential units in the building.
     *
     * @return Total number of residents in all residential units
     * Precondition: listOfUnits is not null and contains valid Unit objects.
     * Post: Returns the total count of residents based on unit occupancies.
     */
    public int getNumberOfResidents() {
        int numberOfResidents = 0;
        for (Unit unit : this.listOfUnits) {
            numberOfResidents += (unit).getQuantityOfPeople();
        }
        return numberOfResidents;
    }

    @Override
    public abstract String toString();
}
/**
 * Represents a private house building type with specific area and satisfaction levels for residents.
 */
class PrivatHouse extends BuildingType {
    /**
     * Constructs a PrivatHouse with specified unit satisfaction.
     *
     * @param unitSatisfaction Satisfaction level of the unit
     * Precondition: unitSatisfaction >= 0
     * Postcondition: PrivatHouse object is initialized with a random area and one residential unit.
     * Invariants: All class invariants are maintained.
     */
    public PrivatHouse(double unitSatisfaction) {
        super(0, 1, 1400, 2500);
        this.area = getRandom(100);
        listOfUnits.add(new ResidentalUnit(unitSatisfaction));
    }
    @Override
    public String toString() {
        return "Private house";
    }
}
/**
 * Represents an apartment house with multiple residential units, each with a specified satisfaction level.
 */
class ApartmentHouse extends BuildingType {
    /**
     * Constructs an ApartmentHouse with specified number of units and unit satisfaction.
     *
     * @param numberOfUnits     Number of units in the apartment house
     * @param unitSatisfaction Satisfaction level of the units
     * Precondition: numberOfUnits >= 1, unitSatisfaction >= 0
     * Postcondition: ApartmentHouse object is initialized with random number of units and residential units added to the list.
     * Invariants: All class invariants are maintained.
     */
    public ApartmentHouse(int numberOfUnits, double unitSatisfaction) {
        super(0, 0, 0, 12500);
        this.numberOfUnits = (int)getRandom(50);
        this.quantityOfMaterialPerUnit = 850;       // от балди
        for (int i = 0; i < numberOfUnits; i++) {
            listOfUnits.add(new ResidentalUnit(unitSatisfaction));
        }
    }
    @Override
    public String toString() {
        return "Apartment house";
    }
}

/**
 * Represents an industrial building with a large area, additional metal requirements, and a single non-residential unit.
 */
class IndustrialBuilding extends BuildingType {
    protected NonResidentialUnit unit;
    /**
     * Constructs an IndustrialBuilding with specified unit satisfaction.
     *
     * @param unitSatisfaction Satisfaction level of the unit
     * Precondition: unitSatisfaction >= 0
     * Postcondition: IndustrialBuilding object is initialized with random area, additional metal, and one non-residential unit.
     * Invariants: All class invariants are maintained.
     */

    public  IndustrialBuilding(double unitSatisfaction){
        super(0, 1 , 0, 50000);
        this.area = getRandom(5000);
        this.additionalMetal = 1000;
        this.quantityOfMaterialPerUnit = area * 17; // 17  quintal per 1 square meter to built a idustrial
        listOfUnits.add(new NonResidentialUnit(unitSatisfaction));
    }

    @Override
    public String toString() {
        return "Industrial building ";
    }
}

/**
 * Represents a public institution building (e.g., schools) with a large area, material requirements, and a non-residential unit.
 */
class PublicInstitution extends BuildingType {
    protected NonResidentialUnit unit;
    /**
     * Constructs a PublicInstitution with specified unit satisfaction.
     *
     * @param unitSatisfaction Satisfaction level of the unit
     * Precondition: unitSatisfaction >= 0
     * Postcondition: PublicInstitution object is initialized with random area and one non-residential unit.
     * Invariants: All class invariants are maintained.
     */
    public PublicInstitution(double unitSatisfaction){
        super(0, 1, 0, 125000);
        this.area = getRandom(3000);
        this.quantityOfMaterialPerUnit = area * 10; // 10 quintal per 1 square meter to built a school for example
        listOfUnits.add(new NonResidentialUnit(unitSatisfaction));
    }

    @Override
    public String toString() {
        return "Public institution";
    }
}
// GOOD: The use of inheritance in the BuildingType hierarchy promotes code reuse and logical organization.
// Each subclass represents a specific type of building with its own attributes, maintaining high cohesion.
// GOOD: The getNumberOfResidents() method in BuildingType uses polymorphism, allowing it to work with any Unit subclass.
