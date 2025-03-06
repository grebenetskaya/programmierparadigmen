package Aufgabe6;

/**
 * Class representing an office within a building.
 * Each office contains multiple usable rooms and one non-usable area.
 *
 * - numberID is defined (no negative constraint here, but should be meaningful)
 * - usableRooms is never null
 * - nonUsableRoom is never null
 */
public class Office {
    private int numberID;
    private List usableRooms;
    private NonUsableRoom nonUsableRoom;

    public Office(int numberID, double areaOfNonUsableSpace) {
        if (areaOfNonUsableSpace < 0) {
            throw new IllegalArgumentException("Area cannot be negative.");
        }
        this.numberID = numberID;
        this.usableRooms = new List();
        this.nonUsableRoom = new NonUsableRoom(areaOfNonUsableSpace);
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
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room room = (Room)it.next();
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
    public void addRoom(Room roomToAdd) {
        usableRooms.add(roomToAdd);
    }

    /**
     * Removes a room by name from this office.
     * A temporary WithWindowGen with the same name is used to identify it.
     *
     * @param nameOfRoomToRemove the name of the room to remove, must not be null
     * @throws UnsupportedOperationException if the room does not exist
     */
    public void removeRoom(String nameOfRoomToRemove) {
        // Fake-Raum zum Entfernen anlegen:
        // Wir brauchen einen equals-Vergleich anhand des Namens
        // Ein Raum gilt als gleich, wenn der Name gleich ist.
        Room dummy = new WithWindow(nameOfRoomToRemove,0,0,0);
        usableRooms.remove(dummy);
    }

    /**
     * Changes the purpose of a specific room by name.
     * If the room has a Bureau purpose, it changes to Stock, and vice versa.
     *
     * @param name the room name, must exist in usableRooms
     * @throws IllegalAccessException if the room's purpose is null
     */
    public void changePurposeOfTheRoom(String name) throws IllegalAccessException {
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if (r.getName().equals(name)) {
                r.changePurpose();
            }
        }
    }

    /**
     * Computes the average area of all usable rooms in this office.
     *
     * @return the average area if at least one room exists
     * @throws RuntimeException if there are no usable rooms
     */
    public double averageAreaOfAllRooms() {
        int numberOfRooms = usableRooms.getSize();
        double totalArea = getTotalArea() - getAreaOfNonUsableSpaces();
        if(numberOfRooms == 0) return 0; // Division by zero vermeiden
        return totalArea / numberOfRooms;
    }

    // Similar methods for other averages (like averageAreaOfRoomsWithWindows, etc.)
    // include checks and throw RuntimeException if no suitable rooms are found.
    public double averageAreaOfRoomsWithWindows() {
        int count = 0;
        double total = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r instanceof WithWindow){
                count++;
                total += r.getLength()*r.getWidth();
            }
        }
        if(count < 1) throw new RuntimeException("Keine Räume mit Fenstern!");
        return total / count;
    }

    public double averageAreaOfRoomsWithoutWindows() {
        int count = 0;
        double total = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r instanceof WithoutWindow){
                count++;
                total += r.getLength()*r.getWidth();
            }
        }
        if(count < 1) throw new RuntimeException("Keine fensterlosen Räume!");
        return total / count;
    }

    public double averageStockVolumeOfStockRooms() {
        int count = 0;
        double totalVolume = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r.getPurpose() instanceof StockRoom){
                count++;
                totalVolume += ((StockRoom)r.getPurpose()).getStockVolume();
            }
        }
        if(count < 1) throw new RuntimeException("Keine Lagerräume!");
        return totalVolume / count;
    }

    public double averageNumberOfWorkplacesOfBureauRooms() {
        int count = 0;
        int workplaces = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r.getPurpose() instanceof BureauRoom){
                count++;
                workplaces += ((BureauRoom)r.getPurpose()).getNumberOfWorkplaces();
            }
        }
        if(count < 1) throw new RuntimeException("Keine Büroräume!");
        return (double) workplaces / count;
    }

    public double averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindows() {
        double sum = 0;
        int count = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r instanceof WithWindow){
                count++;
                sum += ((WithWindow)r).getWindowArea() / (r.getLength()*r.getWidth());
            }
        }
        if(count < 1) throw new RuntimeException("Keine Räume mit Fenstern!");
        return sum / count;
    }

    public double averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindowsUsedAsBureaus() {
        double sum = 0;
        int count = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r instanceof WithWindow && r.getPurpose() instanceof BureauRoom){
                count++;
                sum += ((WithWindow)r).getWindowArea() / (r.getLength()*r.getWidth());
            }
        }
        if(count < 1) throw new RuntimeException("Keine Fenster-Büroräume!");
        return sum / count;
    }

    public double averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindowsUsedAsStockRooms() {
        double sum = 0;
        int count = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r instanceof WithWindow && r.getPurpose() instanceof StockRoom){
                count++;
                sum += ((WithWindow)r).getWindowArea() / (r.getLength()*r.getWidth());
            }
        }
        if(count < 1) throw new RuntimeException("Keine Fenster-Lagerräume!");
        return sum / count;
    }

    public double averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindows() {
        double sum = 0;
        int count = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r instanceof WithoutWindow){
                count++;
                sum += ((WithoutWindow)r).getLumen() / (r.getLength()*r.getWidth());
            }
        }
        if(count < 1) throw new RuntimeException("Keine fensterlosen Räume!");
        return sum / count;
    }

    public double averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindowsUsedAsBureaus() {
        double sum = 0;
        int count = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r instanceof WithoutWindow && r.getPurpose() instanceof BureauRoom){
                count++;
                sum += ((WithoutWindow)r).getLumen() / (r.getLength()*r.getWidth());
            }
        }
        if(count < 1) throw new RuntimeException("Keine fensterlosen Büroräume!");
        return sum / count;
    }

    public double averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindowsUsedAsStockRooms() {
        double sum = 0;
        int count = 0;
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room r = (Room)it.next();
            if(r instanceof WithoutWindow && r.getPurpose() instanceof StockRoom){
                count++;
                sum += ((WithoutWindow)r).getLumen() / (r.getLength()*r.getWidth());
            }
        }
        if(count < 1) throw new RuntimeException("Keine fensterlosen Lagerräume!");
        return sum / count;
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
        IteratorOverList iterator = usableRooms.iterator();
        while (iterator.hasNext()) {
            Room room = (Room)iterator.next();
            System.out.println("- " + room.getName() + ": " + room.getArea() + " m²");
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("\n\t\t\toffice id: ").append(numberID).append(" rooms: ");
        IteratorOverList it = usableRooms.iterator();
        while(it.hasNext()){
            Room room = (Room)it.next();
            result.append(room.toString());
        }
        result.append(" ").append(nonUsableRoom.toString());
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Office office = (Office) obj;
        return office.numberID == this.numberID;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(42).hashCode();
    }
}
