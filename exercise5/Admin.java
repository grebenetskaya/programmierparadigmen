package Aufgabe5;

/**
 * Admin is a generic interface with two type parameters X and T
 * as well as the following methods: add(X x) and remove(X x).
 *
 * @param <X>
 * @param <T>
 */
public interface Admin <X, T> {
    /***
     * Returns something that extends this by x. It is not specified what the extension consists of.
     * The result is identical to this if this cannot be extended by x.
     *
     * @param x
     * @return T something that extends this by x.
     */
    T add(X x);

    /***
     * Returns something corresponding to this, from which x is removed.
     * The result is identical to this if x cannot be removed from this.
     *
     * @param x
     * @return T something that extends this by x.
     */
    T remove(X x);
}
