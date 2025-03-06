package Aufgabe8;

/*
 Arbeitsverteilung Aufgabe 5:


         Wir haben immer zusammen (offline) gearbeitet und das Beispiel so ausgearbeitet,
         meistens hat eine Person aktiv programmiert (die Verteilung können Sie unten sehen), während die anderen zugesehen und Ideen beigesteuert haben.
         Insofern wurde ein guter Teil des Programmes wirklich zusammen entwickelt.

         Varvara Grebenetska (12126394): CubeManipulations, Coordinates
         Sofiia Deiak (12240248): OwnRating, StandardRating, RatingFunction
         Iryna Hontsovska (12235752): RecursiveAlgorithmus, Test, Output

 */

public class Test {

    public static void main(String[] args) {

        runOptimization1();
        runOptimization2();
        runOptimization3();

    }

    private static void runOptimization1() {
        int n = 8;
        int m = 3;
        int k = 4;

        System.out.println("-------------------Standard-Rating # 1-------------------");
        var solutions = CubeArrangementSearcher.searchArrangements(n, m, k, new StandartRating());
        OutputUtils.printResults(n, m, k, solutions, new StandartRating());
    }

    private static void runOptimization2() {
        int n = 12;
        int m = 4;
        int k = 5;

        System.out.println("-------------------Standard-Rating # 2-------------------");
        var solutions = CubeArrangementSearcher.searchArrangements(n, m, k, new StandartRating());
        OutputUtils.printResults(n, m, k, solutions, new StandartRating());
    }

    private static void runOptimization3() {
        int n = 10;
        int m = 4;
        int k = 6;

        System.out.println("-------------------Our own Rating # 3-------------------");
        var solutions = CubeArrangementSearcher.searchArrangements(n, m, k, new OwnRating());
        OutputUtils.printResults(n, m, k, solutions, new OwnRating());
    }

}
