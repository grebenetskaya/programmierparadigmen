package Aufgabe4;

import java.util.Iterator;

/**
 * An object providing an iterator over its 'Space' elements.
 */

public interface SpaceIterable extends Iterable<Space> {
    /**
     * Returns an iterator over elements of type 'Space'.
     *
     * @return an iterator over elements of type 'Space'.
     */
    public EscapeIterator iterator();
}
