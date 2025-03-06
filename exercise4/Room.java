package Aufgabe4;

import java.util.*;
/**
 * Room: A Space that is enclosed except for openings, which may be closable.
 *
 *
 * - Preconditions: Objects of this type must represent rooms with recognizable boundaries.
 * - Postconditions: The enclosing shell is not part of the Room.
 */

public abstract class Room implements Interior{
    private List<Space> adjazentSpaces = new ArrayList<Space>();
    private Entity associatedEntity;    // the smallest one
    /**
     * Constructs a Room with the given associated Entity and adjacent Spaces.
     *
     * @param associatedEntity The Entity this Room belongs to.
     * @param adjazentSpaces Adjacent Spaces to this Room.
     * @throws IllegalArgumentException if associatedEntity is null.
     */

    // зробити створення об'єктів через освітленність та площу вікон
    public Room(Entity associatedEntity, Space ... adjazentSpaces){
        this.adjazentSpaces.addAll(Arrays.asList(adjazentSpaces));
        this.associatedEntity = associatedEntity;
    }
    /**
     * @return
     */
    @Override
    public Entity entity() {
        return this.associatedEntity;
    }

    /**
     * @return
     */
    @Override
    public Escape escape() {
        if(adjazentSpaces.isEmpty())
            return null;
        return new Escape(this);
    }

    /**
     * @return
     */
    @Override
    public Set<Space> remove() {        // треба дописати!!!!!!!!!!!!!!
        for(Space space : this.adjazentSpaces){
            space.deleteAdjazentSpace(this);
        }
        this.adjazentSpaces.clear();
        Set<Space> result = new HashSet<Space>();
        result.add(this);
        return result;
    }

    /**
     * @param space
     */
    @Override
    public void deleteAdjazentSpace(Space space) {
        this.adjazentSpaces.remove(space);
    }
    @Override
    public void addAdjazentSpace(Space space){
        // зробити прев'язку
    }
}
