package Aufgabe7;
@Author("Sofiia Deiak")
public abstract   class PumpMedium extends Pump {
    @PreCondition("price >= 0")
    protected PumpMedium(double price) {
        super(price);
    }

    @PostCondition("returned boolean, that indicates if the pump is suited for small size bureau")
    @Override
    public boolean isSuitedForSmallBureau(BureauUnitSmall bureauUnitSmall) {
        bureauUnitSmall.setPumpAndPumpPerfectSize(this, false);
        return true;
    }

    @PostCondition("returned boolean, that indicates if the pump is suited for medium size bureau")
    @Override
    public boolean isSuitedForMediumBureau(BureauUnitMedium bureauUnitMedium) {
        bureauUnitMedium.setPumpAndPumpPerfectSize(this, true);
        return true;
    }

    @PostCondition("returned boolean, that indicates if the pump is suited for large size bureau")
    @Override
    public boolean isSuitedForLargeBureau(BureauUnitLarge bureauUnitLarge) {
        return false;
    }

}
