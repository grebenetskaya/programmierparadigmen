package Aufgabe5;

import java.util.NoSuchElementException;

/**
 * Class that implements iterator based on specified condition.
 *
 * @param <E>
 */
public class IteratorOverAllFiltered<E > implements IteratorOverElements<E> {
    private AbstractApprovableSet<?, ?, ?>.Node<E> current;
    private AbstractApprovableSet<?, ?, ?>.Node<E> previousReturnedFromNext;
    private Filter<E> filter;

    public IteratorOverAllFiltered(AbstractApprovableSet<?, ?, ?>.Node<E> start, Filter<E> filter) {
        this.current = start;
        this.previousReturnedFromNext = null;
        this.filter = filter;
        moveToNextValid();
    }

    /**
     * Method to move to the next valid element.
     * If current element is valid, then do nothing, otherwise find next valid element and assign its value to current.
     * Ensures that current is valid.
     */
    private void moveToNextValid() {
        while (current != null && !filter.matches(current.getValue())) {
            current = current.getNext();
        }
    }

    /**
     * Returns 'true' if the iteration has more elements, 'false' otherwise.
     *
     * @return 'true' if the iteration has more elements, 'false' otherwise.
     */
    @Override
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Returns the next element of the iteration. Throws a 'java.util.NoSuchElementException'
     * if the iteration has no more elements.
     *
     * @return the next element of the iteration.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    @Override
    public E next() {
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        E value = current.getValue();
        previousReturnedFromNext = current;
        current = current.getNext();
        moveToNextValid();
        return value;
    }

    /**
     * Removes from the list the last element that was returned by 'next()'.
     * This call can only have effect if the next() was called before.
     */
    public void remove() {
        // remove previousReturnedFromNext
        this.previousReturnedFromNext.remove();
    }


}
