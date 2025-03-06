package Aufgabe4;
/**
 * Lift: An elevator, a small Interior space exclusively serving mechanical vertical circulation.
 *
 *
 * - Preconditions: Objects of this type must be Lifts.
 * - Postconditions: Lifts are not used in escape paths when lifts are not allowed.
 */

public abstract class Lift implements Interior, PureCirculation {
    private Entity associatedEntity;

    /**
     * Returns the associated Entity.
     *
     * @return The Entity this Lift belongs to.
     */
    @Override
    public Entity entity() {
        return associatedEntity;
    }

    /**
     * Sets the associated Entity.
     *
     * @param entity The Entity to set.
     * @throws IllegalArgumentException if entity is null.
     */
    @Override
    public void setEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null.");
        }
        this.associatedEntity = entity;
    }

    @Override
    public Escape escape() {
        // Lifts are not used in escape paths when lifts are not allowed.
        return null;
    }

}
