package Aufgabe7;

/*
 Arbeitsverteilung Aufgabe 5:


         Wir haben immer zusammen (offline) gearbeitet und das Beispiel so ausgearbeitet,
         meistens hat eine Person aktiv programmiert (die Verteilung können Sie unten sehen), während die anderen zugesehen und Ideen beigesteuert haben.
         Insofern wurde ein guter Teil des Programmes wirklich zusammen entwickelt.

         Varvara Grebenetska (12126394): BureauUnit, BureauUnitLargeUnderfloorHeating, BureauUnitMediumRibbedRadiator, Test, ReflectionUtilS
         Sofiia Deiak (12240248): Author, Invariant, PreCondition, PostCondition, ServerHistoryConstraint, Pump,
         PumpLarge, PumpMedium, PumpSmall
         Iryna Hontsovska (12235752): BureauOperator, BureauUnitLarge, BureauUnitMedium, BureauUnitSmall,
         Test, ReflectionUtil

 */

// this class was made together by Varvara Grebenetska and Iryna Hontsovska
public class Test {

    @PreCondition("args may be null; no input arguments are actually required")
    @PostCondition("test scenarios are executed and results printed")

    public static void main(String[] args){

        System.out.println("\n================== Kontexte A ==================\n");
        System.out.println();
        // Sofiia Deiak
        System.out.println("\n==================TEST 1: showing Pump==================\n");

        BureauOperator operator1 = new BureauOperator(1);

        operator1.addHeatPump(new PumpLargeRibberRadiator(30));
        operator1.addHeatPump(new PumpMediumRibbedRadiator(25));
        operator1.addHeatPump(new PumpLargeUnderfloorHeating(45));
        operator1.addHeatPump(new PumpLargeUnderfloorHeating(45));
        operator1.addHeatPump(new PumpLargeUnderfloorHeating(45));
        operator1.addHeatPump(new PumpMediumUnderfloorHeating(40));
        operator1.addHeatPump(new PumpMediumUnderfloorHeating(40));
        operator1.addHeatPump(new PumpMediumUnderfloorHeating(40));
        System.out.println("show all pumps: expected: 8");
        operator1.showHeatPumps();
        System.out.println("\n==================END TEST 1==================\n");


        System.out.println();
        // Varvara Grebenetska
        System.out.println("\n==================TEST 2: showing Bureaus==================\n");

        BureauOperator operator2 = new BureauOperator(2);

        operator2.addBureauUnit(new BureauUnitLargeUnderfloorHeating());
        operator2.addBureauUnit(new BureauUnitMediumUnderfloorHeating());
        operator2.addBureauUnit(new BureauUnitMediumRibbedRadiator());
        operator2.addBureauUnit(new BureauUnitSmallRibbedRadiator());
        operator2.addBureauUnit(new BureauUnitSmallUnderfloorHeating());
        operator2.addBureauUnit(new BureauUnitSmallUnderfloorHeating());
        operator2.addBureauUnit(new BureauUnitSmallUnderfloorHeating());

        System.out.println("show all bureaus: expected: 7");
        operator2.showOffices();
        System.out.println("\n==================END TEST 2==================\n");


        System.out.println("\n==================TEST 3: showing Bureaus==================\n");

        BureauUnit bureauUnit = new BureauUnitMediumUnderfloorHeating();
        BureauOperator operator3 = new BureauOperator(3);
        operator3.addBureauUnit(bureauUnit);
        operator3.addHeatPump(new PumpSmallRibbedRadiator(20));
        operator3.addHeatPump(new PumpMediumRibbedRadiator(25));
        operator3.addHeatPump(new PumpLargeRibberRadiator(30));
        operator3.addHeatPump(new PumpSmallUnderfloorHeating(40));
        operator3.addHeatPump(new PumpMediumUnderfloorHeating(45));
        operator3.addHeatPump(new PumpLargeUnderfloorHeating(50));

        System.out.println("Assign " + bureauUnit + "\n");
        System.out.println("Assign " + bureauUnit + " to " + operator3);
        Pump assignedPump = operator3.assignHeatPump(bureauUnit);

        System.out.println("Assigned Pump: " + assignedPump);

        System.out.println("Should be a good fit: ");
        operator3.showOffices();

        System.out.println("Testing price: \n");
        System.out.println("Price available: " + operator3.priceAvailable());
        System.out.println("Price installed: " + operator3.priceInstalled());
        System.out.println("\n==================END TEST 3==================\n");



        System.out.println();
        // Iryna Hontsovska
        System.out.println("\n==================TEST 4: assign pumps that do not fit==================\n");

        BureauUnit unit4 = new BureauUnitMediumRibbedRadiator();
        BureauOperator operator4 = new BureauOperator(4);

        operator4.addBureauUnit(unit4);
        operator4.addHeatPump(new PumpLargeRibberRadiator(30));
        operator4.addHeatPump(new PumpMediumRibbedRadiator(25));
        operator4.addHeatPump(new PumpLargeUnderfloorHeating(45));
        operator4.addHeatPump(new PumpLargeUnderfloorHeating(45));
        operator4.addHeatPump(new PumpLargeUnderfloorHeating(45));

        System.out.printf("assign " + unit4.toString() + " ");
        System.out.printf("assign " +  unit4.toString() + " to " +  operator4.toString() + "\n");
        Pump assignedPump2 = operator4.assignHeatPump(unit4);

        System.out.println("print the assigned pump: ");
        System.out.println(assignedPump2);

        System.out.println("check if it a good fit. expected: pumpPerfectFit = false");
        operator4.showOffices();
        System.out.println("\n==================END TEST 4==================\n");

        System.out.println("\n================== END Kontexte A ==================\n");

        System.out.println("\n================== Kontexte B ==================\n");
        ReflectionUtil.extractAndDisplayMetadata();
        System.out.println("\n================== End Kontexte B ==================\n");



    }
}
