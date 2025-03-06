package Aufgabe4;

import java.util.*;
/**
 * Exterior: A Space outside a Building (exterior space).
 *
 *
 * - Preconditions: Objects of this type must represent spaces outside of buildings.
 * - Postconditions: Can be connected to Interior spaces.
 */
public abstract class Exterior implements Space{
    private Entity complexOrBuilding;   // до чого належить цей екстеріор
    private List<Space> adjazentSpaces = new ArrayList<Space>();
    private boolean lift;
    private boolean enter;

    public Exterior(Entity complexOrBuilding, boolean lift, boolean enter, Space ... adjazentSpaces){
        this.complexOrBuilding = complexOrBuilding;
        this.adjazentSpaces.addAll(Arrays.asList(adjazentSpaces));
        this.lift = lift;
        this.enter = enter;
    }

    /**
     * @return
     */
    @Override
    public Entity entity() {
        return this.complexOrBuilding;
    }

    /**
     * @return
     */
    @Override
    public Escape escape() {
        if (adjazentSpaces.isEmpty()){
            return null;
        }
        return new Escape(this);
    }

    /**
     * @return
     */
    @Override
    public Set<Space> remove() {    //////// треба дописати!!!!!!!!!!!!!!
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
    public void addAdjazentSpace(Space space) {
        // зробити прев'язку
    }
    @Override
    public void setEntity(Entity entity){
        this.complexOrBuilding = entity;
    }
}
