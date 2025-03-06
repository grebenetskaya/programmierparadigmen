package Aufgabe5;

public class Exterior<P> extends Space<P>{
    private boolean accessible = false;
    public Exterior(String description, boolean accessible) {
        super(description);
        this.accessible = accessible;
    }

    public boolean isPublic() { // or public?
        return this.accessible;
    }
}
