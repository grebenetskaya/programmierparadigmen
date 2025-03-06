package Aufgabe7;
@Author("Varvara Grebenetska")
public class PumpLargeUnderfloorHeating extends PumpLarge {
    @PreCondition("price >= 0")
    protected PumpLargeUnderfloorHeating(double price) {
        super(price);
    }

    @PostCondition("returned boolean, that indicates if the combination of bureau and pump is suited")
    @Override
    public boolean isSuitedForLowTemperature(BureauUnderfloorHeating bureauUnderfloorHeating) {
        return true;
    }

    @PostCondition("returned boolean, that indicates if the combination of bureau and pump is suited")
    @Override
    public boolean isSuitedForHighTemperature(BureauRibbedRadiator bureauRibbedRadiator) {
        return false;
    }


    @PostCondition("returned a string representation of this pump")
    @Override
    public String toString() {
        return "PumpLargeUnderfloorHeating{price=" + this.getPrice()+"}";
    }
}
