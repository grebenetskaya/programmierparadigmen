package Aufgabe7;
@Author("Varvara Grebenetska")
public class PumpMediumUnderfloorHeating extends PumpMedium {
    @PreCondition("price >= 0")
    protected PumpMediumUnderfloorHeating(double price) {
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
        return "PumpMediumUnderfloorHeating{price=" + this.getPrice()+"}";
    }

}
