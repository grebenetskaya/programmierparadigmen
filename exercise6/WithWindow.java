package Aufgabe6;
/**
 * A class to represent room type with windows.
 * Subclass of RoomGen abstract class.
 */
public class WithWindow extends Room {
    /**
     * Invariant: windowArea >= 0
     */
    private double windowArea = 0;

    public WithWindow(String name, double length, double width, double windowArea) {
        super(name, length, width);
        this.windowArea = windowArea;
    }

    public WithWindow(String name, double length, double width, Purpose purpose, double windowArea) {
        super(name, length, width, purpose);
        this.windowArea = windowArea;
    }
    /**
     * Returns window area.
     *
     * @return window area >= 0
     * */
    public double getWindowArea(){
        return this.windowArea;
    }

    /**
     * String representation of instance of this class.
     *
     * @return string representation of the room with window.
     * */
    @Override
    public String toString(){
        String result = super.toString();
        return result + "\t\t\t\t\troom with window";
    }
}
