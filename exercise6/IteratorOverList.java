package Aufgabe6;

import java.util.NoSuchElementException;

/**
 * An iterator implementation for ListGen.
 */
public class IteratorOverList implements IteratorNode{

    private Node current;

    public IteratorOverList(Node list){
        this.current = list;
    }

    /**
     * Returns 'true' if the iteration has more elements, 'false' otherwise.
     *
     * @return 'true' if the iteration has more elements, 'false' otherwise.
     */
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

    public Object next() {
        if (current == null) {
            throw new NoSuchElementException("Keine weiteren Elemente!");
        }
        Object value = current.getValue();
        current = current.getNext();
        return value;
    }
}
