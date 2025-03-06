package Aufgabe7;
@Author("Iryna Hontsovska")
public abstract class BureauUnitLarge extends BureauUnit {

    @PreCondition("pump != null")
    @PostCondition("returned boolean, that indicates if size is fitted")
    @Override
    public boolean fitSize(Pump pump) {
        return pump.isSuitedForLargeBureau(this);
    }
}

