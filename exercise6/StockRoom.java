package Aufgabe6;
/**
 * Class to represent purpose implementation for stock rooms.
 *
 * Objects of this class are used as a purpose within a Room instance to indicate that the room
 * is used as a stock. It holds stock volume, where "something" assumed to occupy a certain amount of area.
 * (formula to compute stock volume:
 * 1 m^2 of an area * 3m * 70% effective space for shelving, where 1m^2 is unit of measure and 3m is ceiling height).
 */
public class StockRoom implements Purpose {
    private double stockVolume;

    /**
     * Invariant: stockVolume >= 0
     */
    public StockRoom(double stockVolume){
        this.stockVolume = stockVolume;
    }

    /**
     * Returns current stock volume.
     *
     * @return stockVolume >= 0.
     */
    public double getStockVolume(){
        return this.stockVolume;
    }

    /**
     * Adds volume to stock.
     *
     * @param value >= 0.
     */
    public void addStockVolume(double value){
        this.stockVolume += value;
    }

    /**
     * String representation of instance of this class.
     *
     * @return string representation of stock purpose.
     */
    @Override
    public String toString(){
        return "stock room";
    }
}
