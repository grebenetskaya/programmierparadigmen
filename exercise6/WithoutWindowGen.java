package Aufgabe6;
/**
 * A class to represent room type without windows, but with artificial lighting (lumen).
 * Subclass of RoomGen abstract class.
 */
public class WithoutWindowGen extends RoomGen {
    /**
     * Invariant: lumen >= 0.
     */
    private double lumen = 0;
    public WithoutWindowGen(String name, double length, double width, double lumen) {
        super(name, length, width);
        this.lumen = lumen;
    }

    public WithoutWindowGen(String name, double length, double width, PurposeGen purpose, double lumen) {
        super(name, length, width, purpose);
        this.lumen = lumen;
    }
    /**
     * Returns quantity of lumens.
     *
     * @return lumen >= 0.
     */
    public double getLumen(){
        return this.lumen;
    }

    /**
     * String representation of instance of this class.
     *
     * @return string representation of the room without windows but with artificial lightning.
     */
    @Override
    public String toString(){
        String result = super.toString();
        return result + "\t\t\t\t\troom without window";
    }
}
