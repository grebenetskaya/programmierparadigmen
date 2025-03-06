package Aufgabe6;

// our offices are modern open space offices, so the normal area distribution is 5 m^2 pro person
/**
 Class representing a bureau (office) room purpose.
 *
 * Objects of this class are used as a purpose within a RoomGen instance to indicate that the room
 * is used as a bureau. It holds a number of workplaces, where each workplace is assumed to take up
 * a certain amount of area (e.g., 5 m² per person). */
public class BureauRoomGen implements PurposeGen{

    /**
     * Invariant: numberOfWorkPlaces >= 0.
     */
    private int numberOfWorkplaces;
    public BureauRoomGen(int numberOfWorkplaces) {
        this.numberOfWorkplaces = numberOfWorkplaces;
    }

    /**
     * Returns the current number of workplaces in this bureau room.
     *
     * @return numberOfWorkplaces >= 0.
     */
    public int getNumberOfWorkplaces(){
        return this.numberOfWorkplaces;
    }

    /**
     * Increments the number of workplaces by one.
     * The room can logically hold an additional workplace. This is handled outside by checking norms.
     */
    public void addWorkplace(){
        this.numberOfWorkplaces += 1;
    }

    /**
     * String representation of instance of this class.
     *
     * @return string representation of bureau purpose.
     */
    @Override
    public String toString(){
        return "bureau room";
    }
}
