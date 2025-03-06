package Aufgabe6;

/**
 * Abstract class representing a usable room.
 *
 * - name != null
 * - length >= 0, width >= 0 (not strictly enforced, but recommended)
 * - purpose can be null. If null, methods relying on purpose must handle it.
 */
public abstract class RoomGen {
    private String name;
    private double length;
    private double width;
    private PurposeGen purpose;    // lager und office

    public RoomGen(String name, double length, double width){
        this.name = name;
        this.length = length;
        this.width = width;     // what should i do with total area??
        this.purpose = null;
    }

    public RoomGen(String name, double length, double width, PurposeGen purpose){
        this.name = name;
        this.length = length;
        this.width = width;     // what should i do with total area??
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
    public void setPurpose(PurposeGen purpose){
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
    public PurposeGen getPurpose() {
        return purpose;
    }

    /**
     * Changes the room's purpose. If Bureau -> Stock, if Stock -> Bureau.
     *
     * @throws IllegalAccessException if purpose is null.
     */
    public void changePurpose() throws IllegalAccessException {
        if(this.purpose == null){
            throw new IllegalAccessException("you are trying to access null!");
        }
        if(this.purpose instanceof BureauRoomGen){
            this.purpose = new StockRoomGen(0);
        }else{
            this.purpose = new BureauRoomGen(0);
        }
    }

    /**
     * Adds a workplace if purpose is Bureau and area norms allow it.
     *
     * @throws IllegalArgumentException if purpose is null.
     * @throws UnsupportedOperationException if purpose is Stock (no workplaces allowed).
     */
    public void addWorkplace(){         // maybe add method to add a couple of places
        if(purpose == null){
            throw new IllegalArgumentException("the purpose of the room is undefined!(null)");
        }
        if(purpose.getClass() == StockRoomGen.class){
            throw new UnsupportedOperationException("you are trying to add workplace to the stock room!");
        }
        if(purpose instanceof  BureauRoomGen) {
            double area = width * length;
            int currentWorkplaces = ((BureauRoomGen) purpose).getNumberOfWorkplaces();
            if (area /currentWorkplaces >= 5) {
                ((BureauRoomGen) purpose).addWorkplace();
            }else{
                System.out.println("it is impossible to add a workplace, the norm is exceeded!");
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
            throw new IllegalArgumentException("the purpose of the room is undefined!(null)");
        }
        if(purpose.getClass() == BureauRoomGen.class){
            throw new UnsupportedOperationException("you are trying to add workplace to the stock room!");
        }
        if(purpose instanceof  StockRoomGen) {
            double area = width * length;
            double currentVolume = ((StockRoomGen) purpose).getStockVolume();
            if (area * 3 * 0.7 > currentVolume) {
                ((StockRoomGen) purpose).addStockVolume(1 * 3 * 0.7);
            }else{
                System.out.println("it is impossible to add a shelf, the norm is exceeded!");
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
        if (!(obj instanceof RoomGen)) return false;
        RoomGen room = (RoomGen) obj;
        return name.equals(room.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
