package Aufgabe6;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Interface for iterator to iterate over iterables.
 *
 * @param <E>
 */
public interface IteratorNodeGen<E> extends Iterator<E>{
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
    public E next();
}
