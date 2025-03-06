package Aufgabe7;
@Author("Varvara Grebenetska")
public class BureauUnitMediumRibbedRadiator extends BureauUnitMedium implements BureauRibbedRadiator {
    @PreCondition("pump != null")
    @PostCondition("returned boolean, that indicates if temperature is fitted")
    @Override
    public boolean fitTemperature(Pump pump) {
        return pump.isSuitedForHighTemperature(this);
    }
    @PostCondition("string representation of a bureau unit medium with ribbed radiator is returned")
    @Override
    public String toString() {
        return "BureauUnitMediumRibbedRadiator{pump=" + this.getPump() + ", pumpPerfectFit=" + this.isPumpPerfectSize() + "}";
    }
}