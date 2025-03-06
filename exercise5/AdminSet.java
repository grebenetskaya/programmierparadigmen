package Aufgabe5;

import java.util.Iterator;

/**
 * Class that implements ApprovableSet by extending AbstractApprovableSet
 * with the following methods: extend() and shorten().
 *
 * @param <X>
 * @param <P>
 * @param <T>
 */
public class AdminSet<X extends Approvable<P, T>, P, T extends Admin<? super X, T>> extends AbstractApprovableSet<X, P, T> {

    /**
     * Method that performs the following operation for each p returned by an iterator criterions
     * and each x returned by an iterator iterator(p): x.approve(p, x.approved(p).add(x));
     */
    public void extend() {
        Iterator<P> pIter = criterions();
        while (pIter.hasNext()) {
            P p = pIter.next();
            Iterator<X> xIter = iterator(p);
            while (xIter.hasNext()) {
                X x = xIter.next();
                T t = x.approved(p);
                if (t != null) {
                    x.approve(p, t.add(x));
                }
            }
        }
    }

    /**
     * Method that performs the following operation for each p returned by an iterator criterions
     * and each x returned by an iterator iterator(p): x.approve(p, x.approved(p).remove(x))
     */
    public void shorten() {
        Iterator<P> pIter = criterions();
        while (pIter.hasNext()) {
            P p = pIter.next();
            Iterator<X> xIter = iterator(p);
            while (xIter.hasNext()) {
                X x = xIter.next();
                T t = x.approved(p);
                if (t != null) {
                    x.approve(p, t.remove(x));
                }
            }
        }
    }
}
