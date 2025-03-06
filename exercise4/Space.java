package Aufgabe4;

import java.util.HashSet;
import java.util.Set;
/**
 * Space: An area allowing human presence, which can be a Room or other type of space.
 *
 * Assertions:
 * - Preconditions: Objects of this type must represent a usable area for human occupancy.
 * - Different Space objects do not overlap.
 * - The method entity() returns the smallest Entity this Space is embedded in, or null if not embedded.
 */
public interface Space {
    /**
     * Returns the smallest Entity this Space is embedded in, or null if not embedded.
     *
     * @return the associated Entity or null
     */
    Entity entity();
    /**
     * Sets the associated Entity for this Space.
     *
     * @param entity the Entity to associate with
     * @throws IllegalArgumentException if entity is null
     */
    void setEntity(Entity entity);
    /**
     * Returns an Escape object describing shortest paths for escaping or entering this Space.
     *
     * @return an Escape object or null if not applicable
     */
    Escape escape();
    /**
     * Removes this Space from its environment, making it unusable, and removes all other spaces
     * that are no longer sufficiently accessible.
     *
     * @return a set of removed Spaces
     */
    Set<Space> remove();
    /**
     * Deletes a bidirectional connection to an adjacent Space.
     *
     * @param space the adjacent Space to disconnect from
     * @throws IllegalArgumentException if space is null or not adjacent
     */

    void deleteAdjazentSpace(Space space);
    /**
     * Adds a bidirectional connection to an adjacent Space.
     *
     * @param space the adjacent Space to connect to
     * @throws IllegalArgumentException if space is null or already adjacent
     */

    // додає двобічний зв'язок
    void addAdjazentSpace(Space space);
}

