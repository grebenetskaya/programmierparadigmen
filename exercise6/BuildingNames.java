package Aufgabe6;
/**
 * Enum of predefined building names.
 *
 */
public enum BuildingNames {
    B1("building1"),
    B2("building2"),
    B3("building3");


    private String name;
    /**
     * @param name must not be null
     */
    BuildingNames(String name){
        this.name = name;
    }
    /**
     * @return the building name, not null.
     */
    public String getName(){
        return name;
    }

}
