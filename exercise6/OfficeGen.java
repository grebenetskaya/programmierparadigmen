package Aufgabe6;

import java.util.function.Function;
import java.util.function.Predicate;
/**
 * Class representing an office within a building.
 * Each office contains multiple usable rooms and one non-usable area.
 *
 * - numberID is defined (no negative constraint here, but should be meaningful)
 * - usableRooms is never null
 * - nonUsableRoom is never null
 */
public class OfficeGen {
    private int numberID;
    private ListGen<RoomGen> usableRooms; // mehrere, mindestens 1
    private NonUsableRoomGen nonUsableRoom;

    public OfficeGen(int numberID, double areaOfNonUsableSpace) {
        if (areaOfNonUsableSpace < 0) {
            throw new IllegalArgumentException("Area cannot be negative.");
        }
        this.numberID = numberID;
        this.usableRooms = new ListGen<>();
        this.nonUsableRoom = new NonUsableRoomGen(areaOfNonUsableSpace);
    }
    /**
     * Returns the office ID.
     *
     * @return the office number ID
     */
    public int getNumberID() {
        return numberID;
    }
    /**
     * Returns the non-usable area in this office.
     *
     * @return areaOfNonUsableSpaces >= 0
     */
    public double getAreaOfNonUsableSpaces() {
        return this.nonUsableRoom.getTotalArea();
    }
    /**
     * Computes the total area of the office including usable and non-usable areas.
     *
     * @return totalArea >= 0
     */
    public double getTotalArea() {
        double totalArea = this.getAreaOfNonUsableSpaces();
        for (RoomGen room : usableRooms) {
            totalArea += room.getLength()* room.getWidth();
        }
        return totalArea;
    }
    /**
     * Adds a usable room to this office.
     *
     * @param roomToAdd must not be null
     * @throws UnsupportedOperationException if the room already exists (via internal list logic)
     */
    public void addRoom(RoomGen roomToAdd) {
        this.usableRooms.add(roomToAdd);
    }

    /**
     * Removes a room by name from this office.
     * A temporary WithWindowGen with the same name is used to identify it.
     *
     * @param nameOfRoomToRemove the name of the room to remove, must not be null
     * @throws UnsupportedOperationException if the room does not exist
     */
    public void removeRoom(String nameOfRoomToRemove) { // have to use name instead of reference !!!
        //this.usableRooms.remove(roomToRemove);
        this.usableRooms.remove(new WithWindowGen(nameOfRoomToRemove, 0, 0, 0));
    }
    /**
     * Changes the purpose of a specific room by name.
     * If the room has a Bureau purpose, it changes to Stock, and vice versa.
     *
     * @param name the room name, must exist in usableRooms
     * @throws IllegalAccessException if the room's purpose is null
     */
    public void changePurposeOfTheRoom(String name) throws IllegalAccessException {
        for (RoomGen room: this.usableRooms) {
            if (room.getName().equals(name)) {
                room.changePurpose();
            }
        }
    }


// The following statistical methods assume the existence of at least one relevant room.
    // If no such rooms exist, they throw RuntimeException as implemented.

    /**
     * Computes the average area of all usable rooms in this office.
     *
     * @return the average area if at least one room exists
     * @throws RuntimeException if there are no usable rooms
     */
    public double averageAreaOfAllRooms() {
        int numberOfRooms = this.usableRooms.getSize();
        double totalArea = getTotalArea() - getAreaOfNonUsableSpaces();
        return totalArea / numberOfRooms;
    }
    // Similar methods for other averages (like averageAreaOfRoomsWithWindows, etc.)
    // include checks and throw RuntimeException if no suitable rooms are found.
    public double averageAreaOfRoomsWithWindows() {
        int numberOfRoomsWithWindows = 0;
        double totalArea = 0;
        for (RoomGen room : usableRooms) {
            if (room instanceof WithWindowGen) {
                numberOfRoomsWithWindows += 1;
                totalArea += room.getLength()* room.getWidth();
            }
        }
        if (numberOfRoomsWithWindows < 1) {
            throw new RuntimeException("Cannot compute averageAreaOfRoomsWithWindows as numberOfRoomsWithWindows = 0."); // or no exception???
            //return 0;
        }
        return totalArea / numberOfRoomsWithWindows;
    }

    public double averageAreaOfRoomsWithoutWindows() {
        int numberOfRoomsWithoutWindows = 0;
        double totalArea = 0;
        for (RoomGen room : usableRooms) {
            if (room instanceof WithoutWindowGen) {
                numberOfRoomsWithoutWindows += 1;
                totalArea += room.getLength()* room.getWidth();
            }
        }
        if (numberOfRoomsWithoutWindows < 1) {
            throw new RuntimeException("Cannot compute averageAreaOfRoomsWithoutWindows as numberOfRoomsWithoutWindows = 0."); // or no exception???
        }
        return totalArea / numberOfRoomsWithoutWindows;
    }


    public double averageStockVolumeOfStockRooms() {
        int numberOfStockRooms = 0;
        double totalVolume = 0;
        for (RoomGen room: usableRooms) {
            if (room.getPurpose() instanceof StockRoomGen) {
                numberOfStockRooms += 1;
                totalVolume += ((StockRoomGen) room.getPurpose()).getStockVolume();
            }
        }
        if (numberOfStockRooms < 1) {
            throw new RuntimeException("Cannot compute averageStockVolumeOfStockRooms as numberOfStockRooms = 0."); // or no exception???
        }
        return totalVolume / numberOfStockRooms;
    }


    public double averageNumberOfWorkplacesOfBureauRooms() {
        int numberOfBureauRooms = 0;
        int numberOfWorkplaces = 0;
        for (RoomGen room: usableRooms) {
            if (room.getPurpose() instanceof BureauRoomGen) {
                numberOfBureauRooms += 1;
                numberOfWorkplaces += ((BureauRoomGen) room.getPurpose()).getNumberOfWorkplaces();
            }
        }
        if (numberOfBureauRooms < 1) {
            throw new RuntimeException("Cannot compute averageNumberOfWorkplacesOfBureauRooms as numberOfBureauRooms = 0."); // or no exception???
        }
        return (double) numberOfWorkplaces / numberOfBureauRooms;
    }



    public double calculateAverageRatio(Predicate<RoomGen> filter, Function<RoomGen, Double> valueExtractor) {
        double sumOfRatios = 0;
        int count = 0;

        for (RoomGen room : usableRooms) {
            if (filter.test(room)) {
                count++;
                sumOfRatios += valueExtractor.apply(room);
            }
        }

        if (count < 1) {
            throw new RuntimeException("Cannot compute average ratio as no matching rooms found.");
        }

        return sumOfRatios / count;
    }


    public double averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindows() {
        return calculateAverageRatio(
                room -> room instanceof WithWindowGen,
                room -> {
                    WithWindowGen withWindow = (WithWindowGen) room;
                    return withWindow.getWindowArea() / (room.getLength() * room.getWidth());
                }
        );
    }

    public double averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindowsUsedAsBureaus() {
        return calculateAverageRatio(
                room -> room instanceof WithWindowGen && room.getPurpose() instanceof BureauRoomGen,
                room -> {
                    WithWindowGen withWindow = (WithWindowGen) room;
                    return withWindow.getWindowArea() / (room.getLength() * room.getWidth());
                }
        );
    }

    public double averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindowsUsedAsStockRooms() {
        return calculateAverageRatio(
                room -> room instanceof WithWindowGen && room.getPurpose() instanceof StockRoomGen,
                room -> {
                    WithWindowGen withWindow = (WithWindowGen) room;
                    return withWindow.getWindowArea() / (room.getLength() * room.getWidth());
                }
        );
    }

    public double averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindows() {
        return calculateAverageRatio(
                room -> room instanceof WithoutWindowGen,
                room -> {
                    WithoutWindowGen withoutWindow = (WithoutWindowGen) room;
                    return withoutWindow.getLumen() / (room.getLength() * room.getWidth());
                }
        );
    }

    public double averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindowsUsedAsBureaus() {
        return calculateAverageRatio(
                room -> room instanceof WithoutWindowGen && room.getPurpose() instanceof BureauRoomGen,
                room -> {
                    WithoutWindowGen withoutWindow = (WithoutWindowGen) room;
                    return withoutWindow.getLumen() / (room.getLength() * room.getWidth());
                }
        );
    }

    public double averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindowsUsedAsStockRooms() {
        return calculateAverageRatio(
                room -> room instanceof WithoutWindowGen && room.getPurpose() instanceof StockRoomGen,
                room -> {
                    WithoutWindowGen withoutWindow = (WithoutWindowGen) room;
                    return withoutWindow.getLumen() / (room.getLength() * room.getWidth());
                }
        );
    }

    /**
     * Returns the number of usable rooms in this office.
     *
     * @return number of usable rooms >= 0
     */
    public int getUsableRoomsNumber(){
        return usableRooms.getSize();
    }
    /**
     * Prints the names and areas of all usable rooms to stdout.
     */
    public void printRooms() {
        System.out.println("rooms in office " + numberID + ":");
        IteratorOverListGen<RoomGen> iterator = usableRooms.iterator();
        while (iterator.hasNext()) {
            RoomGen room = iterator.next();
            System.out.println("- " + room.getName() + ": " + room.getArea() + " m²");
        }
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("\n\t\t\toffice id: ").append(numberID).append(" rooms: ");
        for(RoomGen room : usableRooms){
            result.append(room.toString());
        }
        return result + " " + nonUsableRoom.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OfficeGen office = (OfficeGen) obj;
        return office.numberID == this.numberID;
    }

    @Override
    public int hashCode() {
        int newInt = 42;
        Integer myInteger = Integer.valueOf(newInt);
        return myInteger.hashCode();
    }


}
