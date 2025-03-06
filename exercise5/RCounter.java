package Aufgabe5;


/**
 * Class whose objects count method calls for test purposes (without type parameter).
 * Counter implements Approvable.
 * Objects of Counter contain a counting variable.
 */
public class RCounter implements Approvable<RCounter, Path<RCounter>>{
    private int count;
    private GenericMap<RCounter, Path<RCounter>> approvals;

    public RCounter() {
        this.count = 0;
        this.approvals = new GenericMap<>();
    }

    /**
     * A call to x.approved(y) increases the count variable of x by 1000 and that of y by 1.
     *
     * @param p criterion.
     * @return T an object authorised in connection with this and the criterion p, or null if no such object exists.
     */
    @Override
    public Path<RCounter> approved(RCounter p) {
        this.count += 1000;
        if (p != null) {
            p.incrementCount(1);
        }
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
    public void approve(RCounter p, Path<RCounter> t) {
        approvals.put(p, t);
    }

    /**
     * Increments counter by 1.
     *
     * @param value value to be incremented.
     */
    private void incrementCount(int value) {
        this.count += value;
    }

    /**
     * Return the content of the count variable, for example "10" not "ten".
     *
     * @return string representation od count variable.
     */
    @Override
    public String toString() {
        return Integer.toString(count);
    }

}
