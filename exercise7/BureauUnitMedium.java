package Aufgabe7;
@Author("Iryna Hontsovska")
public abstract class BureauUnitMedium extends BureauUnit {
    @PreCondition("pump != null")
    @PostCondition("returned boolean, that indicates if temperature is fitted")
    @Override
    public boolean fitSize(Pump pump) {
        return pump.isSuitedForMediumBureau(this);
    }
}