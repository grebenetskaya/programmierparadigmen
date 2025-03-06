package Aufgabe5;

/**
 * Interface to filter values, that were iterated by iterators, based on specified conditions.
 *
 * @param <E>
 */
public interface Filter<E> {
    /**
     * Returns boolean that shows if the specified by implementation condition is satisfied.
     *
     * @param e
     * @return true if satisfied, false otherwise.
     */
    boolean matches(E e);
}
