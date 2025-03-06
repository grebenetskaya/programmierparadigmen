package Aufgabe4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Ensemble implements Entity {
    /**
     * Ensemble: A loose combination of multiple Buildings or Complexes perceived as belonging together.
     *
     * - Preconditions: Objects of this type must represent a building ensemble.
     * - Postconditions:
     *   - The method entities() returns the set of Buildings and Complexes in the ensemble.
     *   - The method space() returns the area enclosed by the ensemble, or null if non-existent.
     */
    private Set<Entity> buildingsAndComplexes = new HashSet<>();
    private PublicRoad publicRoad;
    /**
     * Returns the set of Buildings and Complexes in the ensemble.
     *
     * @return a set of Entities
     */
    private Set<Entity> entities(){
        return this.buildingsAndComplexes;
    };

    /**
     * Returns the space enclosed by the ensemble, or null if non-existent.
     *
     * @return the enclosed Space or null
     */
    private Space space(){
        return this.publicRoad;
    };

    @Override
    public void addEntity(Entity toAdd){
        if(!(toAdd instanceof Building || toAdd instanceof Complex)) {
            throw new IllegalArgumentException("we are adding only buildings and complexes to ensemble!");
        } else  if (buildingsAndComplexes.contains(toAdd)) {
            throw new IllegalArgumentException("cannot add the building(complex) to the complex! this building(complex) already exists!!");
        } else {
            buildingsAndComplexes.add(toAdd);
        }
    }

    @Override
    public void removeEntity(Entity toRemove) {
        buildingsAndComplexes.remove(toRemove);
    }

    @Override
    public void addSpace(Space toAdd, Space... connectTo) {
        if(this.publicRoad != null) {
            throw new UnsupportedOperationException("we cannot add another public road to ensemble!");
        }
        if(!(toAdd instanceof PublicRoad)){
            throw new UnsupportedOperationException("as a space we can use only public road in ensemble!");
        }
        this.publicRoad = (PublicRoad) toAdd;
    }

    @Override
    public void removeSpace(Space toRemove) {
        if (this.publicRoad == null) {
            throw new UnsupportedOperationException("we cannot remove road if it does not exist!");
        }
        if(!(toRemove instanceof PublicRoad)){
            throw new UnsupportedOperationException("as a space we can use only public road in ensemble!");
        }
        this.publicRoad = null;
    }
}
