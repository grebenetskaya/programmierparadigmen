package Aufgabe6;
/**
 * Enum of predefined room names.
 * - name not null
 */
public enum RoomNames {
    R1("room1"),
    R2("room2"),
    R3("room3"),
    R4("room4"),
    R5("room5"),
    R6("room6"),
    R7("room7"),
    R8("room7"),     // special for testing goals
    R9("room6"),    // special for testing goals

    //for statistic
    R10("room10"),
    R11("room11"),
    R12("room12"),
    R13("room13"),
    R14("room14"),
    R15("room15"),
    R16("room16"),
    R17("room17"),
    R18("room18"),
    R19("room19"),
    R20("room20"),
    R21("room21"),
    R22("room22"),
    R23("room23"),
    R24("room24"),
    R25("room25");

    private final String name;

    RoomNames(String name) {
        this.name = name;
    }
    /**
     * @return the room name, not null.
     */
    public String getName() {
        return name;
    }
}
