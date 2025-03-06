package Aufgabe7;
@Author("Varvara Grebenetska")
public class BureauUnitSmallUnderfloorHeating extends BureauUnitSmall implements BureauUnderfloorHeating {

    @PreCondition("pump != null")
    @PostCondition("returned boolean, that indicates if temperature is fitted")
    @Override
    public boolean fitTemperature(Pump pump) {
        return pump.isSuitedForLowTemperature(this);
    }

    @PostCondition("string representation of a bureau unit large with underfloor heating is returned")
    @Override
    public String toString() {
        return "BureauUnitSmallUnderfloorHeating{pump=" + this.getPump() + ", pumpPerfectFit=" + this.isPumpPerfectSize() + "}";
    }

}
