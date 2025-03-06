package Aufgabe7;
@Author("Varvara Grebenetska")
public class BureauUnitLargeRibbedRadiator extends BureauUnitLarge implements BureauRibbedRadiator {
    @PreCondition("pump != null")
    @PostCondition("returned boolean, that indicates if temperature is fitted")
    @Override
    public boolean fitTemperature(Pump pump) {
        return pump.isSuitedForHighTemperature(this);
    }
    @PostCondition("string representation of a bureau unit large with ribbed radiator is returned")
    @Override
    public String toString() {
        return "BureauUnitLargeRibbedRadiator{pump=" + this.getPump() + ", pumpPerfectFit=" + this.isPumpPerfectSize() + "}";
    }

}
