package Aufgabe5;

/*
Main Test
        Arbeitsverteilung Aufgabe 5:


        Wir haben immer zusammen (offline) gearbeitet und das Beispiel so ausgearbeitet,
        meistens hat eine Person aktiv programmiert (die Verteilung können Sie unten sehen), während die anderen zugesehen und Ideen beigesteuert haben.
        Insofern wurde ein guter Teil des Programmes wirklich zusammen entwickelt.

        Varvara Grebenetska (12126394): Path, GenericMapNode, GenericNode, Iterable, IteratorOverAllFiltered, IteratorOverAllElements, Test part 1
        Sofiia Deiak (12240248): Space, Interior, Exterior, Counter, RCounter, Enums, Test part 2&3
        Iryna Hontsovska (12235752): AbstractApprovableSet, ApSet, AdminSet, ApprovableSet, Filter, Test part 4

 */

import java.util.*;

public class Test {

    public static void main(String[] args) {
        // 1.
        System.out.println("\n TEST: Sub-exercise 1.: ");

        List<Counter<String>> countersStrX = new ArrayList<>();
        List<Counter<String>> countersStrP = new ArrayList<>();

        List<Counter<Integer>> countersIntX = new ArrayList<>();
        List<Counter<Integer>> countersIntP = new ArrayList<>();

        List<Counter<Path<String>>> countersPathX = new ArrayList<>();
        List<Counter<Path<String>>> countersPathP = new ArrayList<>();

        List<RCounter> RCountersX = new ArrayList<>();
        List<RCounter> RCountersP = new ArrayList<>();

        List<Space<String>> list5X = new ArrayList<>();
        List<String> criterionsList = new ArrayList<>();

        List<Interior<String>> list6X = new ArrayList<>();

        List<Exterior<String>> list7X = new ArrayList<>();

        List<RCounter> list8X = new ArrayList<>();
        List<RCounter> list8P = new ArrayList<>();





        SpaceDescriptions[] spaceDescriptions = SpaceDescriptions.values();
        InteriorDescriptions[] interiorDescriptions = InteriorDescriptions.values();
        Criterions[] criterions = Criterions.values();
        ExteriorDescriptions[] exteriorDescriptions = ExteriorDescriptions.values();



        for(int i = 0; i < 10; i++) {

            countersStrX.add(new Counter<>());
            countersIntX.add(new Counter<>());
            countersPathX.add(new Counter<>());
            RCountersX.add(new RCounter());
            list5X.add(new Space<>(spaceDescriptions[i].name()));
            list6X.add(new Interior<>(interiorDescriptions[i].getDescription(), interiorDescriptions[i].getArea()));
            list7X.add(new Exterior<>(exteriorDescriptions[i].getDescription(), exteriorDescriptions[i].getPublic()));
            list8X.add(new RCounter());


            countersStrP.add(new Counter<>());
            countersIntP.add(new Counter<>());
            countersPathP.add(new Counter<>());
            RCountersP.add(new RCounter());
            criterionsList.add(criterions[i].getDescription());
            list8P.add(new RCounter());


        }



        ApSet<Counter<String>, Counter<String>, String> apset1 = new ApSet<Counter<String>, Counter<String>, String>();
        ApSet<Counter<Integer>, Counter<Integer>, Integer> apset2 = new ApSet<Counter<Integer>, Counter<Integer>, Integer>();
        ApSet<Counter<Path<String>>, Counter<Path<String>>, Path<String>> apset3 = new ApSet<Counter<Path<String>>, Counter<Path<String>>, Path<String>>();
        ApSet<RCounter, RCounter, Path<RCounter>> apset4 = new ApSet<RCounter, RCounter, Path<RCounter>>();
        ApSet<Space<String>, String, Path<Space<String>>> apset5 = new ApSet<Space<String>, String, Path<Space<String>>>();
        ApSet<Interior<String>, String, Path<Space<String>>> apset6 = new ApSet<Interior<String>, String, Path<Space<String>>>();
        ApSet<Exterior<String>, String, Path<Space<String>>> apset7 = new ApSet<Exterior<String>, String, Path<Space<String>>>();
        AdminSet<RCounter, RCounter, Path<RCounter>> adminset8 = new AdminSet<RCounter, RCounter, Path<RCounter>>();
        AdminSet<Space<String>, String, Path<Space<String>>> adminset9 = new AdminSet<Space<String>, String, Path<Space<String>>>();
        AdminSet<Interior<String>, String, Path<Space<String>>> adminset10 = new AdminSet<Interior<String>, String, Path<Space<String>>> ();
        AdminSet<Exterior<String>, String, Path<Space<String>>> adminset11 = new AdminSet<Exterior<String>, String, Path<Space<String>>>();


        fillInXs(countersStrX, apset1);
        fillInPs(countersStrP, apset1);

        fillInXs(countersIntX, apset2);
        fillInPs(countersIntP, apset2);

        fillInXs(countersPathX, apset3);
        fillInPs(countersPathP, apset3);

        fillInXs(RCountersX, apset4);
        fillInPs(RCountersP, apset4);

        fillInXs(list5X, apset5);
        fillInPs(criterionsList, apset5);

        fillInXs(list6X, apset6);
        fillInPs(criterionsList, apset6);

        fillInXs(list7X, apset7);
        fillInPs(criterionsList, apset7);

        fillInXs(list8X, adminset8);
        fillInPs(list8P, adminset8);

        fillInXs(list5X, adminset9);
        fillInPs(criterionsList, adminset9);

        fillInXs(list6X, adminset10);
        fillInPs(criterionsList, adminset10);

        fillInXs(list7X, adminset11);
        fillInPs(criterionsList, adminset11);


        System.out.println("\n END OF TEST: Sub-exercise 1.: ");

        // 2.
        // a = adminset9
        // b = adminset10
        // c = adminset11



        System.out.println("\n TEST: Sub-exercise 2.: ");

        /*
        IteratorOverElements<Space<String>> iteratorForA = adminset9.iteratorAll();
        while(iteratorForA.hasNext()) {
            Space<String> space = iteratorForA.next();
            if (space instanceof Interior<String>) {
                System.out.println("Area: " + ((Interior<?>)space).area());
            } else if (space instanceof Exterior<String>) {
                System.out.println("Is public: " + ((Exterior<?>)space).isPublic());
            }
        }
         */


        IteratorOverElements<Interior<String>> iteratorForB = adminset10.iteratorAll();
        while(iteratorForB.hasNext()) {
            Space<String> space = iteratorForB.next();
            System.out.println("Area: " + ((Interior<?>)space).area());
            adminset9.add(space);
        }

        IteratorOverElements<Exterior<String>> iteratorForC = adminset11.iteratorAll();
        while(iteratorForC.hasNext()) {
            Space<String> space = iteratorForC.next();
            System.out.println("Is public: " + ((Exterior<?>)space).isPublic());
            adminset9.add(space);
        }

        System.out.println("\n END OF TEST: Sub-exercise 2.: ");


        // 3.

        System.out.println("\n TEST: Sub-exercise 3.: ");

        /*
        Es bestehen keine Untertypbeziehungen zwischen AdminSet und ApSet. Begründung:
        Aufgrund der zusätzlichen Schranke T extends Admin<?, ?> in AdminSet ist AdminSet kein Untertyp von ApSet.
        Die Typparameter sind nicht kompatibel, da ApSet keine solche Einschränkung auf T hat.

        Es bestehen keine Untertypbeziehungen zwischen Counter und RCounter. Begründung:
        Counter<T> und RCounter stehen nicht in einer Untertypbeziehung.
        Counter hat einen Typparameter T, während RCounter keinen hat.
        Die Implementierungen der beiden Klassen sind unterschiedlich.

        */


        System.out.println("\n END OF TEST: Sub-exercise 3.: ");

        // 4.


        System.out.println("\n TEST: Sub-exercise 4.: ");


        System.out.println("Deletion, reinsertion, and querying of objects...");



        for (InteriorDescriptions interiorDesc : InteriorDescriptions.values()) {
            adminset10.add(new Interior<>(interiorDesc.getDescription(), interiorDesc.getArea()));
        }

        for (ExteriorDescriptions exteriorDesc : ExteriorDescriptions.values()) {
            adminset11.add(new Exterior<>(exteriorDesc.getDescription(), exteriorDesc.getPublic()));
        }

        for (Criterions criterion : Criterions.values()) {
            adminset10.addCriterion(criterion.getDescription());
            adminset11.addCriterion(criterion.getDescription());
        }

        System.out.println("Testing deletion in adminset10...");
        IteratorOverElements<Interior<String>> iterator10 = adminset10.iteratorAll();
        if (iterator10.hasNext()) {
            Interior<String> removed = iterator10.next();
            iterator10.remove();
            System.out.println("Removed: " + removed);
        }

        System.out.println("State of adminset10 after deletion:");
        IteratorOverElements<Interior<String>> iterator = adminset10.iteratorAll();
        while (iterator.hasNext()) {
            Interior<String> interior = iterator.next();
            System.out.println(interior);
        }


        System.out.println("Reinserting the removed object...");
        adminset10.add(new Interior<>("Reinserted Interior", 25.0));

        System.out.println("State of adminset10 after reinsertion:");
        IteratorOverElements<Interior<String>> iterator1 = adminset10.iteratorAll();
        while (iterator1.hasNext()) {
            Interior<String> interior = iterator1.next();
            System.out.println(interior);
        }

        System.out.println("Querying adminset10 with criterion 'Criterion1'...");
        IteratorOverElements<Interior<String>> iterator2 = adminset10.iterator("Criterion1");
        while (iterator2.hasNext()) {
            Interior<String> interior = iterator2.next();
            System.out.println("Approved: " + interior);
        }


        System.out.println("Querying adminset10 for entries NOT approved by 'Criterion1'...");
        IteratorOverElements<Interior<String>> iterator3 = adminset10.iteratorNot("Criterion1");
        while (iterator3.hasNext()) {
            Interior<String> interior = iterator3.next();
            System.out.println("Not Approved: " + interior);
        }

        System.out.println("Testing adminset11...");
        IteratorOverElements<Exterior<String>> iterator4 = adminset11.iteratorAll();
        while (iterator4.hasNext()) {
            Exterior<String> exterior = iterator4.next();
            System.out.println("AdminSet11 entry: " + exterior);
        }

        System.out.println("Testing extend and shorten in adminset10...");
        adminset10.extend();
        System.out.println("State of adminset10 after extend:");
        IteratorOverElements<Interior<String>> iterator5 = adminset10.iteratorAll();
        while (iterator5.hasNext()) {
            Interior<String> interior = iterator5.next();
            System.out.println(interior);
        }

        adminset10.shorten();
        System.out.println("State of adminset10 after shorten:");
        IteratorOverElements<Interior<String>> iterator6 = adminset10.iteratorAll();
        while (iterator6.hasNext()) {
            Interior<String> interior = iterator6.next();
            System.out.println(interior);
        }





        System.out.println("\n END OF TEST: Sub-exercise 4.: ");


    }


    public static <T extends Approvable<?, ?>> void fillInXs(List<T> source, ApprovableSet<T,?,?> target) {
        for (T element : source) {
            target.add(element);
        }
    }


    public static <T> void fillInPs(List<T> source, ApprovableSet<?,T,?> target) {
        for (T element : source) {
            target.addCriterion(element);
        }
    }




}
