package Aufgabe4;
/**
 * ServantSpace: A serving Interior space, intended for short-term human occupancy and specific functions.
 *
 *
 * - Preconditions: Objects of this type must be Interior spaces serving specific functions (e.g., WC, technical room).
 * - Postconditions: May or may not be Circulation spaces, based on isCirculation flag.
 */
public abstract class ServantSpace extends Room{

    private boolean isCirculation;  // is this WC or staircase

    /**
     * Constructs a ServantSpace with the given associated Entity.
     *
     * @param associatedEntity The Entity this Space belongs to.
     * @param isCirculation Whether this space serves as circulation.
     * @param adjazentSpaces Adjacent Spaces to this ServantSpace.
     */
    public ServantSpace(Entity associatedEntity, boolean isCirculation, Space... adjazentSpaces) {
        super(associatedEntity, adjazentSpaces);
        this.isCirculation = isCirculation;
    }
}
