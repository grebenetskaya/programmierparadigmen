package Aufgabe5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implements Admin interface and supertype java.lang.Iterable<X>
 * with the following methods: add(X x), remove(X x) and iterator().
 * Path objects represent paths (e.g. escape routes) as lists with elements of type X.
 *
 * @param <X>
 */
public class Path<X> implements Admin<X, Path<X>>, java.lang.Iterable<X> {
    /**
     * Class to represent a double linked list node.
     *
     * @param <X>
     */
    class PathNode<X>{
        private X value;
        private PathNode<X> next;
        private PathNode<X> previous;

        PathNode(X value, PathNode<X> next, PathNode<X> previous){
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        /**
         * Method to add node from beginn of the double linked list iff the element is not already present in the list.
         *
         * @param x element to be added.
         */
        private void addPathNode(X x){
            if(!this.contains(x)){
                this.previous = new PathNode<>(x, this, null);
            }
        }

        /**
         * Method to remove node from the list, if the element is identical.
         *
         * @param x element to remove.
         * @return modified list.
         */
        private PathNode<X> removePathNode(X x){
            PathNode<X> current = this;
            while (current != null && !current.value.equals(x)) {
                current = current.next;
            }
            if (current != null) {
                if (current.previous != null) {
                    current.previous.next = current.next;
                }
                if (current.next != null) {
                    current.next.previous = current.previous;
                }
                return current == this ? current.next : this;
            }
            return this;
        }

        /**
         * Method to check if the element is already contained in the list.
         *
         * @param x element to check.
         * @return true if the element is contained in the list, false otherwise.
         */
        private boolean contains(X x){
            PathNode<X> current = this;
            while (current != null) {
                if (current.value.equals(x)) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    private PathNode<X> path;

    public Path(){
        this.path = null;
    }

    /***
     * Method that adds element to the list.
     * Returns something that extends this by x. It is not specified what the extension consists of.
     * The result is identical to this if this cannot be extended by x.
     *
     * @param x element to add.
     * @return list
     */
    @Override
    public Path<X> add(X x) {
        if(path == null){
            path = new PathNode<>(x, null, null);
        }else{
            path.addPathNode(x);
        }
        return this;
    }

    /***
     * Method that removes identical entries.
     * Returns something corresponding to this, from which x is removed.
     * The result is identical to this if x cannot be removed from this.
     *
     * @param x element to remove.
     * @return list.
     */
    @Override
    public Path<X> remove(X x) {
        if(path == null){
            return null;        // must be an exception !!!!
        }
        path = path.removePathNode(x);
        return this;
    }

    /**
     * Iterator that iterates over the list from front to back.
     *
     * @return iterator to iterate over the elements i the list.
     */
    @Override
    public Iterator<X> iterator() {
        return new Iterator<X>() {
            private PathNode<X> current = path;

            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public X next() {
                if (current == null) {
                    throw new NoSuchElementException("no elements to iterate through!");
                }
                X value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}
