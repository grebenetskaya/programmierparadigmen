package Aufgabe6;

/**
 * Represents a node in a doubly linked list.
 *
 * @param <T> the type of value stored in the node
 *
 * - value is not null after creation.
 * - 'next' and 'previous' references either point to valid nodes or are null.
 */
public class NodeGen <T>{
    private T value;
    private NodeGen<T> next;
    private NodeGen<T> previous;

    public NodeGen(T value, NodeGen<T> next, NodeGen<T> previous){
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    /**
     * Inserts a new node with the given value at the beginning of the list,
     * if that value does not already appear in the list.
     *
     * @param value the value to insert, must not be null
     * @return the new head node if insertion is successful, or null if the value already exists
     */
    protected NodeGen<T> addFirst(T value){  // inserting in the beginning
        if(!this.contains(value)){
            NodeGen<T> toInsert = new NodeGen<>(value, this, null);
            this.previous = toInsert;
            return toInsert;
        }else{
            return null;
        }
    }

    /**
     * Removes the first occurrence of the specified value from the list.
     *
     * @param value the value to remove. Must not be null.
     * @return the updated head of the list if removal is successful, null if value not found.
     */
    protected NodeGen<T> remove(T value){
        if(this.contains(value)) {
            NodeGen<T> current = this;
            while (current != null && !current.value.equals(value)) {
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
        }else{
            return null;
        }
    }
    /**
     * Checks whether the given value is contained in this list starting from this node.
     *
     * @param value the value to find. Must not be null.
     * @return true if found, false otherwise
     */
    private boolean contains(T value){
        NodeGen<T> current = this;
        while(current != null){
            if(current.value.equals(value)){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    /**
     * @return the value of this node. Guaranteed non-null.
     */
    public T getValue() {
        return value;
    }
    /**
     * @return the next node in the list or null if none.
     */
    public NodeGen<T> getNext(){
        return  this.next;
    }
}
