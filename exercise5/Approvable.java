package Aufgabe5;

/**
 * Approvable is a generic interface with two type parameters P and T
 * as well as the following methods: approved(P p) and approve(P p, T t).
 *
 * @param <P>
 * @param <T>
 */
public interface Approvable <P, T> {

    /**
     * A call returns an object authorised in connection with this and the criterion p,
     * or null if no such object exists. For example, if this is an interior room and p stands
     * for ‘escape route’, then a description of the approved escape route from the room is returned,
     * or null if there is no authorisation to use the room with an escape route. this, p and the result
     * can also be something completely different. The result depends only on values in this and p.


     * @param p criterion.
     * @return T an object authorised in connection with this and the criterion p, or null if no such object exists.
     */
    T approved(P p);

    /**
     * Method approves p with t.
     * Immediately after a call to x.approve(p,t), calls to x.approved(p) return the value t,
     * while the results of calls to approved with arguments other than p are not affected by x.approve(p,t).

     * @param p != null, criterion.
     * @param t description, can be null.
     */
    void approve(P p, T t);
}
