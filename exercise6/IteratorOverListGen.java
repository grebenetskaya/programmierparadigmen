package Aufgabe6;

import java.util.NoSuchElementException;
/**
 * An iterator implementation for ListGen.
 */

public class IteratorOverListGen<T> implements IteratorNodeGen<T>{


    private NodeGen<T> current;


    public IteratorOverListGen(NodeGen<T> list){
        this.current = list;
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
    public T next() {
        if (current == null) {
            throw new NoSuchElementException("no elements to iterate through!");
        }
        T value = current.getValue();
        current = current.getNext();
        return value;
    }
}