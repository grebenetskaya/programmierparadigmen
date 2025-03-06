package Aufgabe6;
/**
 * Enum of predefined office IDs.
 *
 */
public enum OfficeIDs {
    OFFICE1(101),
    OFFICE2(102),
    OFFICE3(103),
    OFFICE4(104),
    OFFICE5(105),
    OFFICE6(106),
    OFFICE7(106),     // special test case
    OFFICE8(105);


    private final int id;

    OfficeIDs(int id) {
        this.id = id;
    }
    /**
     * @return the office id, positive integer.
     */
    public int getId() {
        return id;
    }
}
