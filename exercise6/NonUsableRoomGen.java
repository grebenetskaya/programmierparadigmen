package Aufgabe6;

/**
 * Represents a non-usable room area in an office.
 */

public class NonUsableRoomGen  {
    //  >= 0
    private double totalArea = 0; // Gesamtfläche pro Büroeinheit
    /**
     * @param totalArea the area of the non-usable room. Must be >= 0.
     * @throws IllegalArgumentException if totalArea < 0
     */
    public NonUsableRoomGen(double totalArea) {
        if(totalArea < 0 ){
            throw new IllegalArgumentException("you are trying to make area negative valued!");
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
