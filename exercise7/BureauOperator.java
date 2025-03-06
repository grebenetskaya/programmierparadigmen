package Aufgabe7;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
@Author("Iryna Hontsovska")
public class BureauOperator {

    @Invariant("bureauUnits != null")
    private List<BureauUnit> bureauUnits = new LinkedList<>();

    @Invariant("pumps != null")
    private List<Pump> pumps = new LinkedList<>();

    @Invariant("id > 0")
    private int id;

   @PreCondition("id > 0")
    public BureauOperator(int id){
        this.id = id;
    }

    @PreCondition("pump != null")
    @PostCondition("pump is part of the pumps list")
    public void addHeatPump(Pump pump) {
        this.pumps.add(pump);
    }

    @PreCondition("pump != null")
    @PostCondition("pump is no longer part of the pumps list")
    public void deleteHeatPump(Pump pump) {
        this.pumps.remove(pump);
    }

    @PreCondition("bureauUnit != null")
    @PostCondition("bureauUnit is part of the bureauUnits list")
    public void addBureauUnit(BureauUnit bureauUnit){
        this.bureauUnits.add(bureauUnit);
    }

    @PreCondition("bureauUnit != null")
    @PostCondition("either a suitable pump was assigned or null was returned id not contained")
    public Pump assignHeatPump(BureauUnit bureauUnit) {
        if (!bureauUnits.contains(bureauUnit)) {
            return null;
        } else if (bureauUnit.getPump() != null) {
            this.returnHeatPump(bureauUnit);
        }
        for (Pump p: pumps) {
            if (!bureauUnit.fitTemperature(p)) {
                continue;
            }
            boolean sizeFits = bureauUnit.fitSize(p);
            if (sizeFits && bureauUnit.isPumpPerfectSize()) {
                break;
            }
        }
        return bureauUnit.getPump();
    }
    @PreCondition("bureauUnit != null; bureauUnit.getPump() != null")
    @PostCondition("bureauUnit no longer has a pump and pump is included in pumps again")
    public void returnHeatPump(BureauUnit bureauUnit) {
        if (!bureauUnits.contains(bureauUnit)) {
            return;
        } else {
            this.pumps.add(bureauUnit.getPump());
            bureauUnit.removePump();
        }
    }
    @PostCondition("return value >= 0")
    public double priceAvailable() {
        return this.pumps.stream()
                .map(pump -> pump.getPrice())
                .reduce(0.0, Double::sum);
    }
    @PostCondition("return value >= 0")
    public double priceInstalled() {
        return this.bureauUnits.stream()
                .filter(bureauUnit -> bureauUnit.getPump() != null)
                .map(bureauUnit -> bureauUnit.getPump().getPrice())
                .reduce(0.0, Double::sum);
    }
    @PostCondition("pumps are shown")
    public void showHeatPumps() {
        System.out.println("Heat Pumps: \n" + this.pumps.stream()
                .map(pump -> "\t" + pump.toString())
                .collect(Collectors.joining("\n")));
    }
    @PostCondition("bureaus are shown")
    public void showOffices() {
        System.out.println("Offices: \n" + this.bureauUnits.stream()
                .map(bureauUnit -> "\t" + bureauUnit.toString())
                .collect(Collectors.joining("\n")));

    }
    @PostCondition("return value != null")
    @Override
    public String toString(){
        return "bureau operator number " + id + " ";
    }


}