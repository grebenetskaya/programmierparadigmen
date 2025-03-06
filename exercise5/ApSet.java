package Aufgabe5;

/**
 * Additional class that implements AbstractApprovableSet.
 * The type parameters of ApSet and ApprovableSet correspond to each other.
 *
 * @param <X>
 * @param <P>
 * @param <T>
 */
public class ApSet<X extends Approvable<P, T>, P, T> extends AbstractApprovableSet<X, P, T> {
    // No additional methods or constraints are needed for ApSet
}
