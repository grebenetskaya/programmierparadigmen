package Aufgabe5;

public enum ExteriorDescriptions {
    ROOM1("Main Garden", true),
    ROOM2("Parking Lot", false),
    ROOM3("Rooftop Terrace", true),
    ROOM4("Outdoor Swimming Pool", true),
    ROOM5("Backyard", false),
    ROOM6("Courtyard", true),
    ROOM7("Playground", false),
    ROOM8("Front Lawn", true),
    ROOM9("Walking Trail", false),
    ROOM10("Patio Area", true);

    private String description;
    private boolean accessible;


    ExteriorDescriptions(String description, boolean accessible) {
        this.description = description;
        this.accessible = accessible;
    }


    public boolean getPublic() {
        return accessible;
    }

    public String getDescription() {
        return description;
    }
}
