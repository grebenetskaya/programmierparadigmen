package Aufgabe5;

//import java.util.Iterator;  // mb delete and write Iterator on our own

public interface Iterable<E> extends java.lang.Iterable<E> {
    public IteratorOverElements<E> iterator();
}