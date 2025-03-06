package Aufgabe5;

/**
 * Class to represent a generic map.
 *
 * @param <K> key.
 * @param <V> value.
 */
public class GenericMap<K, V>{
    private GenericMapNode<K, V> head;

    public GenericMap(){
        this.head = null;
    }

    /**
     * Method to put key-value pair to the map.
     *
     * @param key !=null,  key
     * @param value != null, value
     */
    public void put(K key, V value){
        if(head == null){
            head = new GenericMapNode<>(key, value, null);
        }else{
            head.put(key, value);
        }
    }

    /**
     * Method to get value from the map.
     *
     * @param key != null, key
     * @return value according to a key, null if the key is not contained in the map or map is empty.
     */
    public V get(K key){
        if (head == null){
            return null;
        }else {
            return head.getValue(key);
        }
    }
}

