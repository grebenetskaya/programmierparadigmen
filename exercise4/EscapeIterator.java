package Aufgabe4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over elements of type 'Space'.
 */
public interface EscapeIterator extends Iterator<Space> {
    /**
     * Returns 'true' if the iteration has more elements, 'false' otherwise.
     *
     * @return 'true' if the iteration has more elements, 'false' otherwise.
     */
    public boolean hasNext();

    /**
     * Returns the next element of the iteration. Throws a 'java.util.NoSuchElementException'
     * if the iteration has no more elements.
     *
     * @return the next element of the iteration.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    public Space next();

}
