package Aufgabe5;

public class Interior<P> extends Space<P>{
    private double area;
    public Interior(String description, double area) {
        super(description);
        this.area = area;
    }

    public double area() { // or public?
        return this.area;
    }
}
