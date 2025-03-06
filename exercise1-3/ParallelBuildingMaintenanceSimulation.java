import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.List;

// STYLE: Parallel

/**
 * The ParallelBuildingMaintenanceSimulation class provides methods to simulate building maintenance and occupancy management in parallel.
 * It operates on a list of Building objects, managing their occupancy and maintenance over a period of time.
 */
public class ParallelBuildingMaintenanceSimulation {

    // паралельно починаємо симуляцію для будиночків
    /**
     * Starts the parallel simulation for a list of existing buildings.
     * @param buildings, the list of buildings to simulate.
     * Precondition: buildings != null.
     * Postcondition: each building in the list has its occupancy and maintenance managed over 5 years.
     */
    public static void runSimulation(List<Building> buildings) {
        buildings.forEach(building -> {
            CompletableFuture<Void> occupancyManagement = manageOccupancy(building);
            CompletableFuture<Void> maintenanceTask = manageMaintenance(building);

            CompletableFuture.allOf(occupancyManagement, maintenanceTask).join();
        });
    }

    // метод для праралельного заселення та виселення якшо мало людей живе
    /**
     * Parallel method to manage occupancy and demolish building if occupancy is low.
     * @param building, the building to manage.
     * @return a CompletableFuture representing the asynchronous operation.
     * Precondition: building != null.
     * Postcondition: manages the occupancy of the building over 5 years or until the building is destroyed.
     */
    private static CompletableFuture<Void> manageOccupancy(Building building) {
        return CompletableFuture.runAsync(() -> {
            for (int year = 1; year <= 5; year++) {
                double maxOccupancy = building.type.numberOfUnits * 5; // assuming that in each unit max 5 people can live
                if (building.type.getNumberOfResidents() < 0.9*maxOccupancy) {
                    System.out.println("Diese Building (" + building + "- " + building.years + " y.o) hat eine niedrige Belegung. Gebäude wird abgerissen.");
                    building.destroy();
                    break;
                } else {
                    int peopleLeaving = ThreadLocalRandom.current().nextInt(0, 10);
                    if (building.damage > 0.8) {
                        peopleLeaving += ThreadLocalRandom.current().nextInt(5, 15); // Mehr ziehen aus bei schlechtem Zustand
                    }
                    evacuateResidents(building, peopleLeaving);


                    System.out.println("Diese Building (" + building + "- "  + building.years + " y.o) | Current Occupancy: " + building.type.getNumberOfResidents()/(maxOccupancy) * 100 + "%");
                }

                try {
                    TimeUnit.SECONDS.sleep(2); // simulation für ein Jahr
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    /**
     * Evacuates residents from the building.
     * @param building, the building from which residents will leave.
     * @param peopleLeaving, the number of people to evacuate.
     * Precondition: building != null, peopleLeaving ≥ 0.
     * Postcondition: the specified number of residents are evacuated from the building.
     */
    private static void evacuateResidents(Building building, int peopleLeaving) {
        int peopleLeft = 0;
        while (peopleLeft < peopleLeaving) {
            for (Unit unit: building.type.listOfUnits) {
                if(peopleLeaving-peopleLeft < unit.quantityOfPeople) {
                    unit.quantityOfPeople -= peopleLeaving-peopleLeft;
                    peopleLeaving = peopleLeft;
                } else {
                    peopleLeft += unit.quantityOfPeople;
                    unit.quantityOfPeople = 0;
                }
            }
        }
    }

    /**
     * Parallel method to manage building maintenance.
     * @param building, the building to maintain.
     * @return a CompletableFuture representing the asynchronous operation.
     * Precondition: building != null.
     * Postcondition: manages the maintenance of the building over 5 years.
     */
    private static CompletableFuture<Void> manageMaintenance(Building building) {
        return CompletableFuture.runAsync(() -> {
            for (int year = 1; year <= 5; year++) {
                if (building.damage > 0.8) {
                    System.out.println("Diese Building (" + building + "- "  + building.years + " y.o) benötigt Wartung. Wartung wird durchgeführt...");
                    building.renovateBuilding(building.type.numberOfUnits);
                } else {
                    System.out.println("Diese Building (" + building + "- "  + building.years + " y.o) ist in gutem Zustand.");
                }
                building.oneYearPasses();
                try {
                    TimeUnit.SECONDS.sleep(2); // Simulation für ein Jahr
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
}

// GOOD: The methods are static and do not rely on shared global variables, making the code easier to test and maintain.
// GOOD: The use of CompletableFuture and asynchronous methods allows for parallel execution, improving performance and simulating concurrent processes.

// BAD: The use of ThreadLocalRandom and TimeUnit.sleep can make the simulation slow. For simulation purposes,
// it might be better to abstract time and randomness to allow for faster and repeatable simulations.

