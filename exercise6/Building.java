package Aufgabe6;

/**
 * Class to represents a building with offices inside.
 *
 * - name not null and final.
 * - offices is not null.
 */
public class Building {
    private final String name;
    private List offices;

    public Building(String name) {
        this.name = name;
        this.offices= new List();
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
    public void addOffice(Office officeUnit){
        offices.add(officeUnit);
    }

    public void displayInfo(){
        System.out.println("Building:" + name);
        IteratorOverList it = offices.iterator();
        while(it.hasNext()){
            Office o = (Office) it.next();
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
    public void removeOffice(Office value){
        offices.remove(value);
    }

    public void printOffices() {
        System.out.println("offices in building " + name + ":");
        IteratorOverList it = offices.iterator();
        while (it.hasNext()) {
            Office office = (Office)it.next();
            System.out.println("- " + office.getNumberID());
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("\t\tbuilding name: ").append(name).append("\n \t\toffices:");
        IteratorOverList it = offices.iterator();
        while(it.hasNext()){
            Office office = (Office)it.next();
            result.append("\t\t" + office.toString());
        }
        return result.toString() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Building building = (Building) obj;
        return name.equals(building.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
