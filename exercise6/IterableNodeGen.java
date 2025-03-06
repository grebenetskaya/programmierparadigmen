package Aufgabe6;
/**
 * Defines that classes implementing this interface can return an iterator over their nodes.
 *
 * @param <E> type of elements
 */
public interface IterableNodeGen<E> extends java.lang.Iterable<E>{
    public IteratorNodeGen<E> iterator();
}
