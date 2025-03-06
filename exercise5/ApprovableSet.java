package Aufgabe5;

//import java.util.Iterator;  // mb delete and write Iterator on our own

/**
 * ApprovableSet is an interface with three parameters X, P and T and supertype java.lang.Iterable<X>.
 * One object of this is a container with entries of types X and P, where X is a subtype of Approvable
 * (with suitable type parameter substitutions), so that for each entry x of type X and each entry p of type P,
 * the method x.approved(p) can be called and returns a result of type T (or null).
 */

public interface ApprovableSet <X extends Approvable<P, T>, P, T> extends java.lang.Iterable<X> {

    /**
     * If the container does not yet contain an identical object as an entry,
     * it will be inserted, but if an identical object has already been inserted using add,
     * it will not be inserted again.
     * Ensures that the argument is an entry in the container.
     *
     * @param x something to be added.
     */
    void add(X x);

    /**
     * If the container does not yet contain an identical object as an entry,
     * it will be inserted. Involves entries of type P and no identical objects
     * that are inserted using addCriterion may occur more than once.
     * Ensures that the argument is an entry in the container.
     *
     * @param p something to be added.
     */
    void addCriterion(P p);

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using add().
     *
     * @return iterator which runs in any order over all entries in the container.
     */
    IteratorOverElements<X> iteratorAll();

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using add for which x.approved(p) != null.
     *
     * @param p condition
     * @return iterator which runs in any order over all entries in the container for which x.approved(p) != null.
     */
    IteratorOverElements<X> iterator(P p);

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using add for which x.approved(p) == null.
     *
     * @param p condition
     * @return iterator which runs in any order over all entries in the container for which x.approved(p) == null.
     */
    IteratorOverElements<X> iteratorNot(P p);

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using add for which x.approved(p) != null for all p that were added with addCriterion().
     *
     * @return iterator which runs in any order over all entries in the
     * container for which x.approved(p) != null for all p that were added with addCriterion().
     */
    IteratorOverElements<X> iterator();

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using addCriterion().
     *
     * @return iterator which runs in any order over all entries in the container.
     */
    IteratorOverElements<P> criterions();



}
