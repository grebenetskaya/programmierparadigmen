package Aufgabe5;

//import java.util.Iterator;

/**
 * Abstract class that implement ApprovableSet.
 * The type parameters of AbstractApprovableSet and ApprovableSet correspond to each other.
 *
 * @param <X>
 * @param <P>
 * @param <T>
 */

public class AbstractApprovableSet<X extends Approvable<P, T>,P,T> implements ApprovableSet<X,P,T>{

    /**
     * Generic class for representing a node od a double linked list.
     *
     * @param <E>
     */
    class Node<E> {
        private E value;    // this
        private Node<E> next;   //next
        private Node<E> previous;   // previous
        Node(E value, Node<E> next, Node<E> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        /**
         * Method to add node to the double linked list iff it is not already in the list.
         *
         * @param newValue new node to be added.
         */
        private void genericAdd(E newValue) {
            Node<E> currentNode = this;
            Node<E> nodeToInsert = new Node<E>(newValue, null, null);

            while (currentNode.next != null) {
                if (nodeToInsert.value.equals(currentNode.value)) {
                    return;
                }
                currentNode = currentNode.next;
            }
            if(!currentNode.value.equals(nodeToInsert)) {
                currentNode.next = nodeToInsert;
                nodeToInsert.previous = currentNode;
            }
        }


        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        /**
         * Method to remove specified node by iterator from the double linked list.
         */
        public Node<E> remove() {
            if (this.previous != null) {
                this.previous.next = this.next;
            }
            if (this.next != null) {
                this.next.previous = this.previous;
            }
            this.next = this.previous = null;
            return this;
        }

    }

    private Node<X> entry;
    private Node<P> criterion;

    public AbstractApprovableSet() {
        this.entry = null;
        this.criterion = null;
    }


    /**
     * Method to add nodes to the double linked list.
     * If the container does not yet contain an identical object as an entry,
     * it will be inserted, but if an identical object has already been inserted using add,
     * it will not be inserted again.
     * Ensures that the argument is an entry in the container.
     *
     * @param x something to be added.
     */
    @Override
    public void add(X x) {
        if(entry == null){
            entry = new Node<>(x, null, null);
        }else{
            entry.genericAdd(x);
        }
    }

    /**
     * If the container does not yet contain an identical object as an entry,
     * it will be inserted. Involves entries of type P and no identical objects
     * that are inserted using addCriterion may occur more than once.
     * Ensures that the argument is an entry in the container.
     *
     * @param p something to be added.
     */
    @Override
    public void addCriterion(P p) {
        if(criterion == null){
            criterion = new Node<>(p, null, null);
        }else{
            criterion.genericAdd(p);
        }
    }

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using add().
     *
     * @return iterator which runs in any order over all entries in the container.
     */
    @Override
    public IteratorOverElements<X> iteratorAll() {
        return new IteratorOverAllFiltered<X>(entry, new Filter<X>() {

            /**
             * Returns boolean that shows if the specified by implementation condition is satisfied.
             *
             * @param x
             * @return true.
             */
            @Override
            public boolean matches(X x) {
                return true;
            }
        });
    }

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using add for which x.approved(p) != null.
     *
     * @param p condition
     * @return iterator which runs in any order over all entries in the container for which x.approved(p) != null.
     */
    @Override
    public IteratorOverElements<X> iterator(P p) {
        return new IteratorOverAllFiltered<>(entry, new Filter<X>() {

            /**
             * Returns boolean that shows if the specified by implementation condition is satisfied.
             *
             * @param x
             * @return true if x.approved(p) != null, otherwise false.
             */
            @Override
            public boolean matches(X x) {
                return x.approved(p) != null;
            }
        });
    }

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using add for which x.approved(p) == null.
     *
     * @param p condition
     * @return iterator which runs in any order over all entries in the container for which x.approved(p) == null.
     */
    @Override
    public IteratorOverElements<X> iteratorNot(P p) {
        return new IteratorOverAllFiltered<>(entry, new Filter<X>() {

            /**
             * Returns boolean that shows if the specified by implementation condition is satisfied.
             *
             * @param x
             * @return true if x.approved(p) == null, otherwise false.
             */
            @Override
            public boolean matches(X x) {
                return x.approved(p) == null;
            }
        });
    }

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using add for which x.approved(p) != null for all p that were added with addCriterion().
     *
     * @return iterator which runs in any order over all entries in the
     * container for which x.approved(p) != null for all p that were added with addCriterion().
     */
    @Override
    public IteratorOverElements<X> iterator() {
        return new IteratorOverAllFiltered<>(entry, new Filter<X>() {

            /**
             * Returns boolean that shows if the specified by implementation condition is satisfied.
             *
             * @param x
             * @return true if x.approved(p) == null for all p that were added with addCriterion(), otherwise false.
             */
            @Override
            public boolean matches(X x) {
                for (Node<P> c = criterion; c != null; c = c.next) {
                    if (x.approved(c.value) == null) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

    /**
     * Returns an iterator which runs in any order over all entries in the container,
     * that were inserted using addCriterion().
     *
     * @return iterator which runs in any order over all entries in the container.
     */
    @Override
    public IteratorOverElements<P> criterions() {
        return new IteratorOverAllFiltered<P>(criterion, new Filter<P>() {

            /**
             * Returns boolean that shows if the specified by implementation condition is satisfied.
             *
             * @param p criterion.
             * @return true.
             */
            @Override
            public boolean matches(P p) {
                return true;
            }
        });
    }
}
