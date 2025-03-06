package Aufgabe6;

/**
 * Represents a non-usable room area in an office.
 */
public class NonUsableRoom {
    private double totalArea = 0;

    /**
     * @param totalArea the area of the non-usable room. Must be >= 0.
     * @throws IllegalArgumentException if totalArea < 0
     */
    public NonUsableRoom(double totalArea) {
        if(totalArea < 0 ){
            throw new IllegalArgumentException("Negative Fläche nicht erlaubt!");
        }
        this.totalArea = totalArea;
    }

    /**
     * @return the total non-usable area, guaranteed >= 0.
     */
    public double getTotalArea() {
        return totalArea;
    }

    @Override
    public String toString(){
        return "\n\t\t\tnon usable room area: " + totalArea;
    }
}
