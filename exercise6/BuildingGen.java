package Aufgabe6;
/**
 * Class to represents a building with offices inside.
 *
 * - name not null and final.
 * - offices is not null.
 */
public class BuildingGen {
    private final String name;
    private ListGen<OfficeGen> offices; // eine oder mehrere: exception when empty

    public BuildingGen(String name) {
        this.name = name;
        this.offices= new ListGen<>();
    }

    /**
     * Return building name.
     *
     * @return name != null.
     */
    public String getName(){
        return name;
    }

    /**
     * Adds an office to this building.
     * Increments offices list size by 1.
     *
     * @param officeUnit != null.
     */
    public void addOffice(OfficeGen officeUnit){
        offices.add(officeUnit);
    }

    public void displayInfo(){
        System.out.println("Building:" + name);
        for (OfficeGen o : offices){
            System.out.println(o);
        }
    }

    /**
     * Returns number of offices.
     *
     * @return the number of offices, size >= 0
     */
    public int getOfficesNumber(){
        return offices.getSize();
    }

    /**
     * Removes an office from this building.
     * Decrements offices list size by 1.
     *
     * @param value != null, contained in office list.
     * @throws UnsupportedOperationException if officeUnit not found.
     */
    public void removeOffice(OfficeGen value){
        offices.remove(value);
    }

    public void printOffices() {
        System.out.println("offices in building " + name + ":");
        IteratorOverListGen<OfficeGen> iterator = offices.iterator();
        while (iterator.hasNext()) {
            OfficeGen office = iterator.next();
            System.out.println("- " + office.getNumberID());
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("\t\tbuilding name: ").append(name).append("\n \t\toffices:");
        for (OfficeGen office : offices){
            result.append("\t\t" + office.toString());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BuildingGen building = (BuildingGen) obj;
        return name.equals(building.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
