package Aufgabe5;

/**
 * Class to represent a map node.
 *
 * @param <K> key
 * @param <V> value
 */

public class GenericMapNode <K, V>{
    private K key;
    private V value;
    private GenericMapNode<K, V> next;

    public GenericMapNode(K key, V value, GenericMapNode<K, V> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * Method to put key-value pair to the map.
     *
     * @param key !=null,  key
     * @param value != null, value
     */
    public void put(K key, V value){
        GenericMapNode<K, V> toPut = new GenericMapNode<>(key, value, null);
        GenericMapNode<K, V> current = this;
        while(current.next != null){
            if(current.key.equals(key)){
                current.value = value;
                return;
            }
            current = current.next;
        }
        current.next = toPut;
    }

    /**
     * Method to get value from the map.
     *
     * @param key != null, key
     * @return value according to a key, null if the key is not contained in the map.
     */
    public V getValue(K key){
        GenericMapNode<K, V> current = this;
        while(current.next != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
}
