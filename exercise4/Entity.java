package Aufgabe4;

import java.util.HashSet;
import java.util.Set;
/**
 * Entity: A built unit, such as a Building, Complex, Ensemble, or the entire built city.
 *
 *
 * - Preconditions: Objects of this type or its subtypes must represent a built entity.
 * - Entities cannot be of type Space.
 * - Methods are needed to add and remove built entities and spaces.
 */
public interface Entity {
    /**
     * Adds a built entity to this Entity.
     *
     * @param toAdd the Entity to add
     * @throws IllegalArgumentException if toAdd is null or cannot be added
     */
    void addEntity (Entity toAdd) throws IlligalEntityOperationException;
    /**
     * Removes a built entity from this Entity.
     *
     * @param toRemove the Entity to remove
     * @throws IllegalArgumentException if toRemove is null or cannot be removed
     */
    void removeEntity(Entity toRemove) throws IlligalEntityOperationException;
    /**
     * Adds a Space to this Entity, possibly connecting it to existing spaces.
     *
     * @param toAdd the Space to add
     * @param connectTo Spaces to connect the new Space to
     * @throws IllegalArgumentException if toAdd is null or cannot be added
     */

    void addSpace(Space toAdd, Space ... connectTo);
    /**
     * Removes a Space from this Entity.
     *
     * @param toRemove the Space to remove
     * @throws IllegalArgumentException if toRemove is null or cannot be removed
     */

    void removeSpace(Space toRemove);
}
