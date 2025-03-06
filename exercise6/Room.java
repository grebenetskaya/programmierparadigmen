package Aufgabe6;

/**
 * Abstract class representing a usable room.
 *
 * - name != null
 * - length >= 0, width >= 0 (not strictly enforced, but recommended)
 * - purpose can be null. If null, methods relying on purpose must handle it.
 */
public abstract class Room {
    private String name;
    private double length;
    private double width;
    private Purpose purpose;

    public Room(String name, double length, double width){
        this.name = name;
        this.length = length;
        this.width = width;
        this.purpose = null;
    }

    public Room(String name, double length, double width, Purpose purpose){
        this.name = name;
        this.length = length;
        this.width = width;
        this.purpose = purpose;
    }

    /**
     * Returns name of this room.
     *
     * @return name != null.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns length of this room.
     *
     * @return length >= 0.
     */
    public double getLength(){
        return this.length;
    }

    /**
     * Returns width of this room.
     *
     * @return width >= 0.
     */
    public double getWidth(){
        return this.width;
    }

    /**
     * Sets the room's purpose.
     *
     * @param purpose can be null or non-null.
     */
    public void setPurpose(Purpose purpose){
        this.purpose = purpose;
    }

    /**
     * Clears the purpose (sets to null).
     */
    public void clearPurpose(){
        this.purpose = null;
    }

    /**
     * Returns current purpose of this room.
     *
     * @return the current purpose, can be null.
     */
    public Purpose getPurpose() {
        return purpose;
    }

    /**
     * Changes the room's purpose. If Bureau -> Stock, if Stock -> Bureau.
     *
     * @throws IllegalAccessException if purpose is null.
     */
    public void changePurpose() throws IllegalAccessException {
        if(this.purpose == null){
            throw new IllegalAccessException("Purpose ist null!");
        }
        if(this.purpose instanceof BureauRoom){
            this.purpose = new StockRoom(0);
        }else{
            this.purpose = new BureauRoom(0);
        }
    }

    /**
     * Adds a workplace if purpose is Bureau and area norms allow it.
     *
     * @throws IllegalArgumentException if purpose is null.
     * @throws UnsupportedOperationException if purpose is Stock (no workplaces allowed).
     */
    public void addWorkplace(){
        if(purpose == null){
            throw new IllegalArgumentException("purpose ist undefined!");
        }
        if(purpose instanceof StockRoom){
            throw new UnsupportedOperationException("Büroarbeitsplatz zu Lagerraum hinzufügen nicht möglich!");
        }
        if(purpose instanceof  BureauRoom) {
            double area = width * length;
            int currentWorkplaces = ((BureauRoom) purpose).getNumberOfWorkplaces();
            if (area / currentWorkplaces >= 5) {
                ((BureauRoom) purpose).addWorkplace();
            }else{
                System.out.println("Norm überschritten! Kein weiterer Arbeitsplatz möglich.");
            }
        }
    }

    /**
     * Adds a shelf if purpose is Stock and capacity allows it.
     *
     * @throws IllegalArgumentException if purpose is null
     * @throws UnsupportedOperationException if purpose is Bureau
     */
    public void addShelf(){
        if(purpose == null){
            throw new IllegalArgumentException("purpose ist undefined!");
        }
        if(purpose instanceof BureauRoom){
            throw new UnsupportedOperationException("Stellregal in Büroraum nicht möglich!");
        }
        if(purpose instanceof StockRoom) {
            double area = width * length;
            double currentVolume = ((StockRoom) purpose).getStockVolume();
            if (area * 3 * 0.7 > currentVolume) {
                ((StockRoom) purpose).addStockVolume(1 * 3 * 0.7);
            }else{
                System.out.println("Norm überschritten! Kein weiteres Regal möglich.");
            }
        }
    }

    /**
     * Returns are of this room.
     *
     * @return area of the room (width * length), >= 0.
     */
    public double getArea(){
        return width * length;
    }

    /**
     * String representation of instance of this class.
     *
     * @return string representation of the room.
     */
    @Override
    public String toString(){
        return purpose == null ?
                "\n\t\t\t\tname: " + name
                        + ", \n\t\t\t\t\tlength: " + length
                        + ", \n\t\t\t\t\twidth: " + width
                        + ", \n\t\t\t\t\tpurpose: null(no purpose) " + "\n"
                :
                "\n\t\t\t\tname: " + name
                        + ", \n\t\t\t\t\tlength: " + length
                        + ", \n\t\t\t\t\twidth: " + width
                        + ", \n\t\t\t\t\tpurpose: " + purpose.toString() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Room)) return false;
        Room room = (Room) obj;
        return name.equals(room.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
