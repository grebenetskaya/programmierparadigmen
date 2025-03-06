package Aufgabe7;
@Author("Varvara Grebenetska")
public abstract class BureauUnit {

    @Invariant("pumpPerfectSize == false || pump != null")
    private Pump pump;
    private boolean pumpPerfectSize;
    @PreCondition("pump != null")
    @PostCondition("returned boolean, that indicates if temperature is fitted")
    public abstract boolean fitTemperature(Pump pump);
    @PreCondition("pump != null")
    @PostCondition("returned boolean, that indicates if size is fitted")
    public abstract boolean fitSize(Pump pump);
    @PostCondition("pump is returned")
    public Pump getPump() {
        return pump;
    }
    @PostCondition("perfect fit of a pump is returned")
    public boolean isPumpPerfectSize() {
        return pumpPerfectSize;
    }

    @PreCondition("pump may be zero; pumpPerfectFit not zero")
    @PostCondition("pump was set if necessary; pumpPerfectSize was adjusted")
    public void setPumpAndPumpPerfectSize(Pump pump, boolean pumpPerfectFit) {
        if (this.pump == null || (pumpPerfectFit && !this.pumpPerfectSize)) {
            System.out.println("Set pump " + pump.toString());
            this.pump = pump;
            this.pumpPerfectSize = pumpPerfectFit;
        }
    }
    @PostCondition("this.pump == null, pump is removed")
    public void removePump() {
        this.pump = null;
    }
}

