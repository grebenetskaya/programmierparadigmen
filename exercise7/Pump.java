package Aufgabe7;
@Author("Sofiia Deiak")
public abstract class Pump {

    @Invariant("price >= 0")
    private final double price;


    @PreCondition("price >= 0")
    @PostCondition("price is assigned")
    protected Pump(double price) {
        this.price = price;
    }

    @PostCondition("return value >= 0")
    public double getPrice() {
        return price;
    }

    @PostCondition("returned boolean, that indicates if the combination of bureau and pump is suited")
    public abstract boolean isSuitedForLowTemperature(BureauUnderfloorHeating bureauUnderfloorHeating  );

    @PostCondition("returned boolean, that indicates if the combination of bureau and pump is suited")
    public abstract boolean isSuitedForHighTemperature(BureauRibbedRadiator bureauRibbedRadiator );

    @PostCondition("returned boolean, that indicates if the pump is suited for small size bureau")
    public abstract boolean isSuitedForSmallBureau(BureauUnitSmall bureauUnitSmall);

    @PostCondition("returned boolean, that indicates if the pump is suited for medium size bureau")
    public abstract boolean isSuitedForMediumBureau(BureauUnitMedium bureauUnitMedium);

    @PostCondition("returned boolean, that indicates if the pump is suited for large size bureau")
    public abstract boolean isSuitedForLargeBureau(BureauUnitLarge bureauUnitLarge);

}