import java.util.*;

/**
 * The Building class models a building's lifecycle within a sustainability simulation, managing its construction,
 * maintenance, environmental impact, and demolition. Key attributes include CO₂ emissions, material usage,
 * cost, and damage level. The class supports lifecycle events such as renovations and catastrophic damage,
 * with resilience based on sustainability level. It calculates CO₂ and cost implications based on material
 * properties, reusability, and sustainability requirements. Architectural importance can also influence
 * whether a building is revitalized or demolished.
 */

public class Building {

    public int years = 0;
    protected BuildingSustainability sustainability; // Represents the sustainability level of the building
    protected BuildingType type;// Specifies the type of building
    protected boolean exists; //Invariant: true or false
    protected CO2 generalCO2;  //Invariant: >= 0
    protected double damage = 0; //Invariant: between 0 and 100
    protected Cost generalCost; //Invariant: >= 0
    protected boolean architecturalImportance = false;
    protected Material buildingMaterial; //Invariant: >= 0

    protected Material wasteMaterial;//Invariant: >= 0

    protected Material recycleMaterial;//Invariant: >= 0

    protected Material initialBuildingMaterial;//Invariant: >= 0

    protected double livingArea; //Invariant: >= 0


   private final double proportionScore;
   private final double dominanceScore;
   private final double environmentalImpactScore;


    //protected Material additionalMetal;
    // protected Material adjacentTerritory;

    /**
     * Constructs a new Building with the given parameters.
     *
     * @param sustainability The sustainability characteristics of the building.
     * @param type The type of the building (e.g., private house, factory).
     * @param livingArea The living area of the building.
     * Precondition: sustainability, type, and livingArea must not be null, and livingArea >= 0.
     * Postcondition: A new Building instance is created with the specified attributes.
     * Invariants: All class invariants are established.
     */
    public Building(BuildingSustainability sustainability, BuildingType type, double livingArea){      // define whether it is a city or a village using constants
        this.sustainability = sustainability;
        this.type = type;
        this.exists = true;
        this.generalCO2 = new CO2(0);
        this.generalCost = new Cost(0);
        this.architecturalImportance = false;
        buildingMaterial = new Material(type.quantityOfMaterialPerUnit * type.numberOfUnits,sustainability.materialComposition);
        wasteMaterial = new Material(0, sustainability.materialComposition);
        recycleMaterial = new Material(0, sustainability.materialComposition);
        initialBuildingMaterial = new Material(buildingMaterial.getQuantity(), sustainability.materialComposition);
        this.livingArea = livingArea;
        this.catastropheByBuildingOrDemolishing();

        Random random = new Random();
        this.proportionScore = 0.5 + (random.nextDouble() * 0.5); // Пропорція від 0.5 до 1.0
        this.dominanceScore = 0.5 + (random.nextDouble() * 0.5);  // Домінантність від 0.5 до 1.0
        this.environmentalImpactScore = 0.1 + (random.nextDouble() * 0.9);

    }

    /**
     * Checks if the building still exists.
     *
     * @return true if the building exists, false otherwise.
     * Pre: -
     * Post: Returns the existence status of the building.
     */
    public boolean getExistence() {
        return this.exists;
    }

    /**
     * Destroys the building, updating its material quantities, CO2 emissions, and waste generation.
     *
     * Pre: Building must still exist.
     * Post: The building is marked as destroyed and its materials are updated.
     * Invariants: The building's materials reflect the state after destruction.
     */
    protected void destroy(){
        exists = false;
        this.catastropheByBuildingOrDemolishing();
        for (Map.Entry<MaterialType, Double> entry : sustainability.materialComposition.entrySet()){
            double value = entry.getValue();
            MaterialType key = entry.getKey();
            double materialAmount = this.buildingMaterial.getQuantity() * value;
            double reuseCoefficient = key.reusability;
            generalCO2.increaseAmount(materialAmount*key.co2Destroy*(1-reuseCoefficient));
            generalCost.increaseAmount(materialAmount*key.disposalCost);
            generalCost.increaseAmount(materialAmount*key.constructionCost);
            this.recycleMaterial.increaseQuantity(reuseCoefficient * materialAmount);
            if(key instanceof Asphalt) {
                generalCost.increaseAmount(this.type.adjazentTerritory * key.constructionCost);
                generalCost.increaseAmount(this.type.adjazentTerritory * key.disposalCost);
                generalCO2.increaseAmount(this.type.adjazentTerritory * key.co2Build);
                generalCO2.increaseAmount(this.type.adjazentTerritory * key.co2Destroy);
            }
            if(key instanceof Metal){
                generalCost.increaseAmount(this.type.additionalMetal * key.constructionCost);
                generalCost.increaseAmount(this.type.additionalMetal * key.disposalCost);
                generalCO2.increaseAmount(this.type.additionalMetal * key.co2Build);
                generalCO2.increaseAmount(this.type.additionalMetal * key.co2Destroy);
            }
        }
        this.wasteMaterial.increaseQuantity(this.buildingMaterial.getQuantity() - this.recycleMaterial.getQuantity());
    }

    /**
     * Computes the overall satisfaction of the residents based on the satisfaction levels of all units.
     *
     * @return The average satisfaction score for residents.
     * Pre: type.listOfUnits is not empty.
     * Post: Returns the computed average satisfaction score.
     */
    protected double computeSatisfaction() {
        double sumPfSatisfactions = 0;
        for (Unit unit : type.listOfUnits) {
            sumPfSatisfactions += unit.getSatisfaction();
        }
        return sumPfSatisfactions / type.listOfUnits.size();
    }

    /**
    * Increases the building's damage level based on its sustainability type.
    * If the damage exceeds the critical limit, the building may be destroyed or revitalized.
    *
    * Precondition: The building must exist (exists == true).
    * Postcondition: The building's damage level is increased appropriately.
    * If damage >= CRITICAL_DAMAGE, then either the building is revitalized or destroyed.
    */
    private void damage(){
        // BAD: Using a switch-case on the type of 'sustainability' increases coupling and reduces maintainability.
        // This code could be improved by using polymorphism, where each BuildingSustainability subclass implements
        // its own method to adjust damage, reducing the need for type checking.
        switch (this.sustainability) {
            case HighQualityBuildingSustainability hb:
                this.damage += 1;
                break;
            case MinimalBuildingSustainability mb:
                this.damage += 2;
                break;
            case EcoBuildingSustainability eb:
                this.damage += 2;
                break;
            default:
                throw new IllegalStateException("Unexpected building type: " + this);
        }
        if (damage >= Constants.CRITICAL_DAMAGE) {
            if(architecturalImportance) {
                revitaliseBuilding();
            } else {
                destroy();
            }
        }

    }

    /**
     * Renovates the building by updating the condition of a specified number of units.
     *
     * @param numberOfUnits The number of units to renovate.
     * Pre: numberOfUnits must be > 0.
     * Post: Selected units are renovated, and building material quantities are adjusted.
     */
    public void renovateBuilding(int numberOfUnits){
        this.damage = Math.max(damage - Constants.INCREASE_DAMAGE_PER_RENOVATION, damage/4.0);
        List<Unit> temporary = new ArrayList<Unit>(type.listOfUnits);
        Collections.shuffle(temporary);
        for(int i = 0; i < numberOfUnits && i < temporary.toArray().length; i++){
            Unit apartment = temporary.get(i);
            apartment.renovate();
        }
        this.buildingMaterial.increaseQuantity(initialBuildingMaterial.getQuantity() * 0.2);
    }
    /**
     * Revitalizes the building by resetting damage and refreshing units.
     *
     * * Pre: The building must exist
     * Post:Building's damage is reset and units are refreshed.
     * Building material quantities are increased.
     */
    public void revitaliseBuilding() {
        this.damage = 0;
        for(Unit unit : type.listOfUnits){
            unit.revitalise();
        }
        this.buildingMaterial.increaseQuantity(initialBuildingMaterial.getQuantity() * 1.2);
    }

    /**
     *Simulates a catastrophic event, where the intensity of the event determines the
     * extent of damage inflicted on the building. Depending on the building type and
     * specific resilience thresholds, this method may apply varying levels of damage
     * to the structure.
     *
     * Pre:  The building must exist
     * Post: Updates the building's damage and initiates renovations if needed.
     */
    public void catastrophe() {
        // BAD: Using instanceof checks for 'sustainability' types leads to tight coupling and makes the code less flexible.
        // A better solution would be to use polymorphism, where each 'sustainability' type defines its own catastrophe response.
        Random random = new Random();
        double indexOfDamage = random.nextInt(4, 100);  //maybe gaussian
        if ((this.sustainability instanceof EcoBuildingSustainability && indexOfDamage >= 20) ||
                (this.sustainability instanceof MinimalBuildingSustainability && indexOfDamage >= 10) ||
                (this.sustainability instanceof HighQualityBuildingSustainability && indexOfDamage >= 30)) {
            this.damage += indexOfDamage;
            if(this.type instanceof ApartmentHouse)
                renovateBuilding((int) (this.type.numberOfUnits * indexOfDamage) / 100);
            else
                renovateBuilding(1);
        }
    }
    /**
     * Simulates potential damage due to construction or demolition.
     *
     * Pre: -
     * Post: Updates damage level and initiates partial renovations if needed.
     */
    public void catastropheByBuildingOrDemolishing() {
        Random random1 = new Random();
        //STYLE: Procedural code
        int x = random1.nextInt(1, 100);
        if(x > 50) {
            Random random2 = new Random();
            double indexOfDamage = random2.nextInt(4, 40);
            this.damage += indexOfDamage;
            if(this.type instanceof ApartmentHouse)
                renovateBuilding((int) (this.type.numberOfUnits * indexOfDamage) / 100);
            else
                renovateBuilding(1);
            //System.out.println("catastrophe by building or demolishing happened with index " + indexOfDamage);
        }


    }

    /**
     * Simulates the passage of one year, updating the building's state based on age, sustainability, and other factors.
     * This method increments the building's age, increases utility costs based on architectural importance,
     * applies potential damage, and performs random checks for catastrophes or renovation needs.
     *
     * Pre: The building must exist
     * Post: Updates building age, adjusts utility costs, increases damage, and may initiate renovations or catastrophes.
     */
    public void oneYearPasses() {
        //STYLE: Procedural code
        if(years > this.sustainability.lifetimeOfConstructionExpected) {
            destroy();
            return;
        }
        years += 1;
        Random random_i = new Random();
        if (this.architecturalImportance) {
            /*
            int x = random_i.nextInt(100);
                if (x < 1) {
                this.architecturalImportance = false;
                System.out.println("im here making false " + years);
            }
             */
        } else {
            int y = random_i.nextInt(100);
            if (y < 2) {
                this.sustainability.lifetimeOfConstructionExpected += (this.sustainability.lifetimeOfConstructionExpected - years/4);
                //this.sustainability.lifetimeOfConstructionExpected += 10;
                //System.out.println("im here making true " + years);
                this.architecturalImportance = true;
            }
        }
        if (this.architecturalImportance) {
            this.generalCost.increaseAmount(1000*this.type.numberOfUnits); // utility cost
        } else {
            this.generalCost.increaseAmount(500*this.type.numberOfUnits); // utility cost
        }

        this.damage();

        Random random = new Random();
        int randomInt = random.nextInt(100);
        boolean catastropheOccurs = randomInt < 100 * Constants.PROBABILITY_OF_CATASTROPHE;
        if (catastropheOccurs) {
            catastrophe();
        }
        for(Unit unit : type.listOfUnits){
            unit.resetSatisfaction(-0.1);                 // do not know if -5
        }
        int numberOfUnitsToRenovate = 0;
        for (int i = 0; i < this.type.numberOfUnits; i++) {
            Random random1 = new Random();
            int renovationPeriodForUnit = switch (this.sustainability) {
                // BAD: Using switch-case on 'sustainability' types increases coupling and reduces extensibility.
                // Implementing a method in 'BuildingSustainability' classes to provide the renovation period would make the code cleaner.
                case HighQualityBuildingSustainability hb -> Constants.RENOVATION_PERIOD_HQ;
                case MinimalBuildingSustainability mb -> Constants.RENOVATION_PERIOD_M;
                case EcoBuildingSustainability eb -> Constants.RENOVATION_PERIOD_E;
                default -> throw new IllegalStateException("Unexpected building type: " + this);
            };
            boolean unitToBeRenovated = random1.nextInt(100) < 100.0/renovationPeriodForUnit;
            if (unitToBeRenovated) {
                numberOfUnitsToRenovate++;
            }
        }
        if(this.type instanceof ApartmentHouse)
            renovateBuilding(numberOfUnitsToRenovate);
        else
            renovateBuilding(1);
        // BAD: The 'oneYearPasses()' method is lengthy and handles multiple operations, making the control flow complex.
        // Breaking it into smaller methods with single responsibilities would improve maintainability.


    }

    /**
     * Retrieves the CO2 emissions associated with the building.
     *
     * @return CO2 emissions of the building
     * Pre: -
     * Post: The CO2 emissions of the building are returned.
     */
    public CO2 getCo2() {
        // GOOD: High class cohesion as 'getCo2()' method uses the building's materials and sustainability attributes cohesively to calculate CO2 emissions.
        double sumOfCO2 = 0;
        for (Map.Entry<MaterialType, Double> entry : this.sustainability.materialComposition.entrySet()){
            double value = entry.getValue();
            MaterialType key = entry.getKey();
            double materialAmount = this.buildingMaterial.getQuantity()*value;
            double reuseCoefficient = key.reusability;
            generalCO2.increaseAmount(materialAmount*key.co2Destroy*(1-reuseCoefficient));
        }
        return this.generalCO2;
    }


    /**
     * Retrieves the amount of waste material produced by the building.
     *
     * @return the amount of waste of the building     *
     * Pre: -
     * Post: The total quantity of waste material for the building is returned.
     */
    public Material getWasteMaterial() {
        for (Map.Entry<MaterialType, Double> entry : sustainability.materialComposition.entrySet()){
            double value = entry.getValue();
            MaterialType key = entry.getKey();
            double materialAmount = this.buildingMaterial.getQuantity()*value;
            double reuseCoefficient = key.reusability;
            generalCO2.increaseAmount(materialAmount*key.co2Destroy*(1-reuseCoefficient));
            this.recycleMaterial.increaseQuantity(reuseCoefficient * materialAmount);
        }
        this.wasteMaterial.increaseQuantity(this.buildingMaterial.getQuantity() - this.recycleMaterial.getQuantity());
        return this.wasteMaterial;
    }
    /**
     * Retrieves the total cost associated with the building.
     *
     * @return The total cost if the building is demolished, or total ongoing cost if it exists.
     * Pre: -
     * Post: Returns general demolition cost or ongoing material costs if building still exists.
     */
    public double getGeneralCost(){
        if (!exists) {
            return this.generalCost.getAmount();
        } else {
            //throw new IllegalArgumentException("You can only access general cost after the building is demolished.");
            return this.buildingMaterial.getCurrentMaterialMoney() + this.generalCost.getAmount();
        }
    }
    /**
     * Checks if the building is architecturally important.
     *
     * @return true if the building has architectural importance, false otherwise.
     * Pre: -
     * Post: Returns the architectural importance status.
     */
    public boolean isArchitecturalImportant(){
        return this.architecturalImportance;
    }



           public double getProportionScore() {
               return proportionScore;
           }

           public double getDominanceScore() {
               return dominanceScore;
           }

           public double getEnvironmentalImpactScore() {
               return environmentalImpactScore;
           }
}
