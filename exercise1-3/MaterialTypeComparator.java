import java.util.Comparator;
/**
 * The MaterialTypeComparator class provides a comparison method for MaterialType objects, allowing them
 * to be sorted or ordered based on their hash codes. This comparator implements the Comparator interface
 * and overrides the `compare` method to compare two MaterialType instances by their hash codes.
 *
 * This approach ensures a consistent ordering for MaterialType objects, which can be useful in
 * data structures like TreeMap or for general sorting purposes.
 */
public class MaterialTypeComparator implements Comparator<MaterialType> {
    @Override
    public int compare(MaterialType m1, MaterialType m2) {
        return Integer.compare(m1.hashCode(), m2.hashCode()); // Порівняння за хеш-кодами
    }
}
