package Aufgabe6;
/**
 * A generic iterable list implementation backed by a doubly-linked list of NodeGen.
 *
 * - size >= 0
 * - head is either null (empty list) or references the first node.
 * - The number of reachable nodes from head equals size.
 */

public class  ListGen<T> implements IterableNodeGen<T>{
    private NodeGen<T> head;
    private int size;

    public ListGen(){
        this.head  = null;
        this.size = 0;
    }
    /**
     * Adds a unique value to the front of the list.
     *
     * @param value the value to add. Must not be null.
     * @throws UnsupportedOperationException if the value is already in the list.
     *
     * - value != null
     * - The list size increases by 1 if insertion is successful.
     */
    public void add(T value){
        if(head == null){
            head = new NodeGen<>(value, null, null);
            size++;
        }else{
            NodeGen<T> check = head.addFirst(value);
            if(check == null){
                throw new UnsupportedOperationException("the element is already contained in the list!");
            }else{
                head = check;
                size++;
            }
        }
    }

    /**
     * Removes a value from the list.
     *
     * @param value the value to remove. Must not be null.
     * @throws UnsupportedOperationException if the list is empty or if the element is not in the list.
     *
     * - value != null
     * - The list must not be empty.
     *
     * - The size decreases by 1 if successful.
     */
    protected void remove(T value){
        if(head == null){
            throw new UnsupportedOperationException("you are trying to remove element from an empty list!");
        }else{
            NodeGen<T> check = head.remove(value);
            if(check == null){
                throw new UnsupportedOperationException("the element is not contained in the list!");
            }else {
                head = check;
                size--;
            }
        }
    }
    /**
     * @return the current size of the list, size >= 0
     */
    public int getSize(){
        return this.size;
    }

    @Override
    public IteratorOverListGen<T> iterator(){
        return new IteratorOverListGen<T>(head);
    }

}


