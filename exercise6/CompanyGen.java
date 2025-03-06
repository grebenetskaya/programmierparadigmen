package Aufgabe6;
/**
 * Represents a company that holds multiple buildings.
 *
 * - name is final and not null.
 * - buildings is never null.
 */
public class CompanyGen {
    private final String name;
    private ListGen<BuildingGen> buildings; // mehrere


    public CompanyGen(String name) {
        this.name = name;
        this.buildings=new ListGen<>();
    }
    /**
     * @return company name, never null.
     */
    public String getName(){
        return name;
    }
    /**
     * Adds a building to the company.
     *
     * @param buildingUnit not null
     *
     * - buildingUnit != null
     *
     * - buildings size increases by 1 if new.
     */
    public void addBuilding( BuildingGen buildingUnit){
        buildings.add(buildingUnit);
    }
    /**
     * Removes a building from the company.
     *
     * @param buildingUnit not null, must exist.
     * @throws UnsupportedOperationException if buildingUnit does not exist.
     *
     * - buildingUnit != null and present in buildings.
     *
     * - buildings size decreases by 1.
     */
    public void deleteBuilding(BuildingGen buildingUnit){
        buildings.remove(buildingUnit);
    }

    public void displayInfo(){
         System.out.println("Company" + name);
         for (BuildingGen b : buildings){
             b.displayInfo();
         }
    }
    /**
     * @return number of buildings, size >= 0
     */
    public int getBuildingsNumber(){
        return buildings.getSize();
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("company name: ").append(name).append("\n \tbuildings: \n");
        for(BuildingGen building : buildings){
            result.append(building.toString());
        }
        return result.toString() + "\n";
    }
}
