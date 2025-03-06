package Aufgabe4;
/**
 * PublicRoad: A public road is a Space that is unrestrictedly accessible and serves exclusively for movement.
 *
 *
 * - Preconditions: Objects of this type must represent public roads.
 * - Postconditions: The method escape() always returns null.
 */
public interface PublicRoad extends Space, PureCirculation {
    /**
     * Returns null, as public roads provide sufficient escape options.
     *
     * @return Always null.
     */
    @Override
    default Escape escape() {
        return null; // Always returns null
    }
}
