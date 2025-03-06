package Aufgabe4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * Complex: A combination of multiple buildings that are structurally connected and share common access.
 *
 *
 * - Preconditions: Objects of this type must represent a building complex.
 * - Postconditions:
 *   - The method buildings() returns the set of Buildings in the complex.
 *   - The method spaces() returns the set of Exterior Spaces embedded in the complex but not in any Building.
 */
public abstract class Complex implements Entity {
    private Set<Building> buildings = new HashSet<>();
    private Set<Exterior> exteriorSpaces = new HashSet<>();     // additional constraint
    /**
     * Returns the set of Buildings in the complex.
     *
     * @return a set of Buildings
     */
    protected Set<Building> buildings(){
        return this.buildings;
    }
    /**
     * Returns the set of Exterior Spaces embedded in the complex but not in any Building.
     *
     * @return a set of Exterior Spaces
     */
    protected Set<Exterior> spaces(){
        return this.exteriorSpaces;
    }

    public void addEntity (Entity toAdd){
        if(!(toAdd instanceof Building)) {
            throw new IllegalArgumentException("we are adding only buildings to complex!");
        } else  if (buildings.contains(toAdd)) {
            throw new IllegalArgumentException("cannot add the building to the complex! this building already exists!!");
        } else {
            buildings.add((Building) toAdd);
        }
    }
    public void removeEntity(Entity toRemove){
        buildings.remove(toRemove);
        /*if(buildings.size() == 1){
             Building b = new Building();
             buildings.clear();
        }*/
        if(buildings.size() <= 2){
            throw new UnsupportedOperationException("it is impossible to delete building from complex, when we ave only two buildings in complex!");
        }
    }

    public void addSpace(Space newSpace, Space ... connectTo){
        if(!(newSpace instanceof Exterior)) {
            throw new IllegalArgumentException("we are adding only outer space!");
        }
        if(connectTo.length != 1){
            throw new IllegalArgumentException("we can only connect exterior to one enter");
        }
        if(exteriorSpaces.contains(newSpace)){
            throw new IllegalArgumentException("cannot add the outer space to the complex! this outer space already exists!");
        }
        if (!exteriorSpaces.contains(Arrays.stream(connectTo).toArray()[0])) {
            throw new IllegalArgumentException("space is not contained in exteriorSpaceArray!");
        }

        exteriorSpaces.add((Exterior) newSpace);
    }

    public void removeSpace(Space deletedSpace){
        if (!exteriorSpaces.contains(deletedSpace)) {
            throw new IllegalArgumentException("cannot delete the outer space from the complex! outer space does not exists!");
        }
        for(Exterior space : exteriorSpaces) {
            space.deleteAdjazentSpace(deletedSpace);
        }
        exteriorSpaces.remove(deletedSpace);
    }
}
