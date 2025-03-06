package Aufgabe5;

/**
 * Class whose objects represent areas inside and outside buildings.
 * Space implements Approvable and has a type parameter P, which essentially corresponds to the
 * type parameter of the same name in Approvable.
 *
 * @param <P>
 */
public class Space<P> implements Approvable<P, Path<Space<P>>> {

    private String description;
    private GenericMap<P, Path<Space<P>>> approvals;

    public Space(String description) {
        this.description = description;
        this.approvals = new GenericMap<>();
    }

    /**
     * A call returns an object authorised in connection with this and the criterion p,
     * or null if no such object exists. For example, if this is an interior room and p stands
     * for ‘escape route’, then a description of the approved escape route from the room is returned,
     * or null if there is no authorisation to use the room with an escape route. this, p and the result
     * can also be something completely different. The result depends only on values in this and p.


     * @param p criterion.
     * @return T an object authorised in connection with this and the criterion p, or null if no such object exists.
     */
    @Override
    public Path<Space<P>> approved(P p) {
        return approvals.get(p);
    }

    /**
     * Method approves p with t.
     * Immediately after a call to x.approve(p,t), calls to x.approved(p) return the value t,
     * while the results of calls to approved with arguments other than p are not affected by x.approve(p,t).

     * @param p != null, criterion.
     * @param t description, can be null.
     */
    @Override
    public void approve(P p, Path<Space<P>> t) {
        approvals.put(p, t);
    }

    /**
     * The toString method returns a textual description of the area.
     * @return description
     */
    @Override
    public String toString() {
        return description;
    }
}