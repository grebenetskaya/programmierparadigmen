package Aufgabe5;

public enum InteriorDescriptions {
    ROOM1("Kitchen", 14),
    ROOM2("Elevator", 2),
    ROOM3("Guest room", 20.8),
    ROOM4("Office", 15),
    ROOM5("WC", 5.3),
    ROOM6("Garderobe", 40),
    ROOM7("Nursery", 17.2),
    ROOM8("Living room", 30),
    ROOM9("Bedroom", 14),
    ROOM10("Bathroom", 34.4);

    private String description;
    private double area;


    InteriorDescriptions(String description, double area) {
        this.description = description;
        this.area = area;
    }


    public double getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }

}
