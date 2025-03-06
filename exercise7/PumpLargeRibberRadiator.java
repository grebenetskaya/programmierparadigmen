package Aufgabe7;
@Author("Varvara Grebenetska")
public class PumpLargeRibberRadiator extends PumpLarge {
    @PreCondition("price >= 0")
    protected PumpLargeRibberRadiator(double price) {
        super(price);
    }

    @PostCondition("returned boolean, that indicates if the combination of bureau and pump is suited")
    @Override
    public boolean isSuitedForLowTemperature(BureauUnderfloorHeating bureauUnderfloorHeating) {
        return false;
    }

    @PostCondition("returned boolean, that indicates if the combination of bureau and pump is suited")
    @Override
    public boolean isSuitedForHighTemperature(BureauRibbedRadiator bureauRibbedRadiator) {
        return true;
    }
    @PostCondition("returned a string representation of this pump")
    @Override
    public String toString() {
        return "PumpLageRibberRadiator{price=" + this.getPrice()+"}";
    }
}
