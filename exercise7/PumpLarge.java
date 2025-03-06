package Aufgabe7;
@Author("Sofiia Deiak")
public abstract class PumpLarge extends Pump {
    @PreCondition("price >= 0")
    protected PumpLarge(double price) {
        super(price);
    }


    @Override
    public boolean isSuitedForSmallBureau(BureauUnitSmall bureauUnitSmall) {
        return false;
    }


    @Override
    public boolean isSuitedForMediumBureau(BureauUnitMedium bureauUnitMedium) {
        bureauUnitMedium.setPumpAndPumpPerfectSize(this, false);

        return true;
    }

    @Override
    public boolean isSuitedForLargeBureau(BureauUnitLarge bureauUnitLarge) {
        bureauUnitLarge.setPumpAndPumpPerfectSize(this, true);

        return true;
    }


}

