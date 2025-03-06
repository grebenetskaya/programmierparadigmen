package Aufgabe4;

import java.util.HashSet;
import java.util.Set;
/**
 * Building: A structure intended to enclose accessible spaces, allowing human occupancy.
 *
 *
 * - Preconditions: Objects of this type or its subtypes must be buildings connected to the ground.
 * - Postconditions: The method spaces() returns the set of all Spaces embedded in the building.
 */
public abstract class Building implements Entity{
    private Set<Space> spaces = new HashSet<Space>();

    /**
     * Returns the set of all Spaces embedded in the building.
     *
     * @return a set of Spaces
     */
    public Set<Space> spaces() {
        return spaces;
    }

    @Override
    public void addEntity(Entity toAdd) throws IlligalEntityOperationException {
        throw new IlligalEntityOperationException("cannot add entity to building!");
    }

    /**
     * @param toRemove
     */
    @Override
    public void removeEntity(Entity toRemove) throws IlligalEntityOperationException{
        throw new IlligalEntityOperationException("cannot remove entity from building!");
    }


    @Override
    public void addSpace(Space newSpace, Space ... connectTo) {     /// should we do something with escape
        if(newSpace instanceof PublicRoad)
            throw new IllegalArgumentException("it is impossible to add public road to the building!");
        if(spaces.contains(newSpace)){
            throw new IllegalArgumentException("cannot add the room to the building! room already exists!");
        }
        spaces.add(newSpace);
        for(Space space : connectTo) {
            if (!spaces.contains(space)) {
                throw new IllegalArgumentException("space is not contained in spaceArray!");
            }
            space.addAdjazentSpace(newSpace);
        }

    }

    @Override
    public void removeSpace(Space deletedSpace) {               // same question
        if (!spaces.contains(deletedSpace)) {
            throw new IllegalArgumentException("cannot delete the room from the building! room does not exists!");
        }
        for(Space space : spaces) {
            space.deleteAdjazentSpace(deletedSpace);
        }
        spaces.remove(deletedSpace);
    }
}
