package Aufgabe6;

/*
Main Test
        Arbeitsverteilung Aufgabe 5:


        Wir haben immer zusammen (offline) gearbeitet und das Beispiel so ausgearbeitet,
        meistens hat eine Person aktiv programmiert (die Verteilung können Sie unten sehen), während die anderen zugesehen und Ideen beigesteuert haben.
        Insofern wurde ein guter Teil des Programmes wirklich zusammen entwickelt.

        Varvara Grebenetska (12126394): Room/RoomGen, WithWithoutWindows/WithWithoutWindowsGen, RoomEnum, Test
        Sofiia Deiak (12240248): Building/BuildingGen, Company/CompanyGen, BuildingEnum, Node/NodeGen, NonUsableRoom
        Iryna Hontsovska (12235752): Office/OfficeGen, Purpose/PurposeGen, BureauRoom/BureauRoomGen, StockRoom/StockRoomGen, OfficeEnum, List/ListGen, Iterators, Iterable + ect.

 */

public class Test<T> {

    public static void main(String[] args) throws IllegalAccessException {


        System.out.println("\n+++++++++++++++++++++TEST GENERIC BEGIN+++++++++++++++++++++\n");


        System.out.println("\n==================TEST 1: test beginn ==================\n");

        System.out.println("creating a company...");
        CompanyGen companyGen = new CompanyGen("company one");

        BuildingGen buildingGen1 = new BuildingGen(BuildingNames.B1.getName());
        BuildingGen buildingGen2 = new BuildingGen(BuildingNames.B2.getName());
        companyGen.addBuilding(buildingGen1);
        companyGen.addBuilding(buildingGen2);


        OfficeGen officeGen1 = new OfficeGen(OfficeIDs.OFFICE1.getId(), 50.0);
        OfficeGen officeGen2 = new OfficeGen(OfficeIDs.OFFICE2.getId(), 55.0);
        OfficeGen officeGen3 = new OfficeGen(OfficeIDs.OFFICE3.getId(), 30.0);


        buildingGen1.addOffice(officeGen1);
        buildingGen1.addOffice(officeGen2);
        buildingGen2.addOffice(officeGen3);


        RoomGen roomGen1 = new WithWindowGen(RoomNames.R1.getName(), 10, 10, new BureauRoomGen(3), 5.0);
        RoomGen roomGen2 = new WithWindowGen(RoomNames.R2.getName(), 15, 15, new StockRoomGen(5), 3.0);
        RoomGen roomGen3 = new WithoutWindowGen(RoomNames.R3.getName(), 8, 12, new StockRoomGen(7), 80.0);
        RoomGen roomGen4 = new WithoutWindowGen(RoomNames.R4.getName(), 15, 5, new BureauRoomGen(4), 150.0);


        officeGen1.addRoom(roomGen1);
        officeGen1.addRoom(roomGen3);
        officeGen2.addRoom(roomGen2);
        officeGen3.addRoom(roomGen4);

        System.out.println("quantity of the buildings in company: " + companyGen.getBuildingsNumber());
        System.out.println("quantity of offices in " + buildingGen1.getName() + ": " + buildingGen1.getOfficesNumber());
        System.out.println("quantity of offices in " + buildingGen2.getName() + ": " + buildingGen2.getOfficesNumber());

        System.out.println("\nrooms in offices via iterator: ");
        officeGen1.printRooms();
        officeGen2.printRooms();
        officeGen3.printRooms();

        System.out.println("company overview now:");
        System.out.println(companyGen);

        System.out.println("\nmodifying rooms...");
        roomGen1.setPurpose(new StockRoomGen(3));
        roomGen2.changePurpose();

        System.out.println("removing a room...");
        officeGen1.removeRoom(RoomNames.R3.getName());

        System.out.println("\nrooms in offices via iterator after modifications: ");
        officeGen1.printRooms();
        officeGen2.printRooms();
        officeGen3.printRooms();

        System.out.println("company overview now:");
        System.out.println(companyGen);

        System.out.println("\n==================TEST 1: test end ==================\n");

        System.out.println("\n==================TEST 2: test beginn ==================\n");

        OfficeGen officeGen4 = new OfficeGen(OfficeIDs.OFFICE4.getId(), 10.0);
        OfficeGen officeGen5 = new OfficeGen(OfficeIDs.OFFICE5.getId(), 12.0);
        OfficeGen officeGen6 = new OfficeGen(OfficeIDs.OFFICE6.getId(), 33.0);
        OfficeGen officeGen7 = new OfficeGen(OfficeIDs.OFFICE7.getId(), 22);        // test case with the same name
        OfficeGen officeGen8 = new OfficeGen(OfficeIDs.OFFICE8.getId(), 21);        // test case with the same name

        System.out.println("\nour building is now contained only from 2 office");
        buildingGen1.printOffices();
        System.out.println("adding 4 new offices but one is with the same numberID: ");
        buildingGen1.addOffice(officeGen4);
        buildingGen1.addOffice(officeGen5);
        try{
            buildingGen1.addOffice(officeGen6); // this add should succeed because the number is unique
            buildingGen1.addOffice(officeGen7); //this add should throw an exception because the number is already contained
        }catch(UnsupportedOperationException e){
            System.out.println("error adding office: " + e.getMessage());
        }
        buildingGen1.printOffices();
        System.out.println("expected value of offices 5");
        System.out.println("quantity of offices in " + buildingGen1.getName() + ": " + buildingGen1.getOfficesNumber());


        System.out.println("\nremoving 2 offices but one is with the same numberID: ");
        try{
            buildingGen1.removeOffice(officeGen5); // this remove should succeed because the number is unique
            buildingGen1.removeOffice(officeGen8); //this remove should throw an exception because the number is not contained
        }catch(UnsupportedOperationException e){
            System.out.println("error removing office: " + e.getMessage());
        }
        System.out.println("expected value of offices 4");
        System.out.println("quantity of offices in " + buildingGen1.getName() + ": " + buildingGen1.getOfficesNumber());
        buildingGen1.printOffices();

        System.out.println("\n==================TEST 2: test end ==================\n");

        System.out.println("\n==================TEST 3: test beginn ==================\n");


        RoomGen roomGen5 = new WithWindowGen(RoomNames.R5.getName(), 24, 31, new BureauRoomGen(15), 22);
        RoomGen roomGen6 = new WithoutWindowGen(RoomNames.R6.getName(), 31, 18, new BureauRoomGen(4), 200);
        RoomGen roomGen7 = new WithWindowGen(RoomNames.R7.getName(), 21, 21, new StockRoomGen(15), 5);
        RoomGen roomGen8 = new WithoutWindowGen(RoomNames.R8.getName(), 13, 13, new StockRoomGen(12), 150);
        RoomGen roomGen9 = new WithoutWindowGen(RoomNames.R9.getName(), 33, 33, new StockRoomGen(14), 100);

        System.out.println("\nour office is now contained only from 1 room");
        officeGen1.printRooms();
        System.out.println("adding 4 new rooms but one is with the same name: ");
        try{
            officeGen1.addRoom(roomGen5);   // this add should succeed because the name is unique
            officeGen1.addRoom(roomGen6);   // this add should succeed because the name is unique
            officeGen1.addRoom(roomGen7);   // this add should succeed because the name is unique
            officeGen1.addRoom(roomGen8);   //this add should throw an exception because the name is already contained
        }catch(UnsupportedOperationException e){
            System.out.println("error adding room: " + e.getMessage());
        }
        officeGen1.printRooms();
        System.out.println("expected value of rooms 4");
        System.out.println("quantity of rooms in " + officeGen1.getNumberID() + ": " + officeGen1.getUsableRoomsNumber());

        System.out.println("\n\ninfo about office now: \n" + officeGen1);

        System.out.println("\nChanging some info...");
        roomGen6.changePurpose();
        roomGen5.addWorkplace();
        roomGen6.addShelf();

        System.out.println("\nnew info about office now: \n" + officeGen1);

        System.out.println("\n\nremoving 2 rooms but one of them is with the same name: ");
        try{
            officeGen1.removeRoom(roomGen5.getName()); // this remove should succeed because the name is unique
            officeGen1.removeRoom(roomGen6.getName());  // this remove should succeed because the name is unique
            officeGen1.removeRoom(roomGen9.getName()); //this remove should throw an exception because the name is not contained
        }catch(UnsupportedOperationException e){
            System.out.println("error removing room: " + e.getMessage());
        }
        officeGen1.printRooms();
        System.out.println("expected value of rooms 2");
        System.out.println("quantity of rooms in " + officeGen1.getNumberID() + ": " + officeGen1.getUsableRoomsNumber());

        System.out.println("\n==================TEST 3: test end ==================\n");


        System.out.println("\n==================TEST 4: test beginn ==================\n");

        RoomGen roomGen10 = new WithWindowGen(RoomNames.R10.getName(), 25, 25, new BureauRoomGen(12), 20);
        RoomGen roomGen11 = new WithoutWindowGen(RoomNames.R11.getName(), 30, 20, new BureauRoomGen(6), 180);
        RoomGen roomGen12 = new WithWindowGen(RoomNames.R12.getName(), 22, 22, new StockRoomGen(18), 6);
        RoomGen roomGen13 = new WithoutWindowGen(RoomNames.R13.getName(), 15, 15, new StockRoomGen(10), 120);
        RoomGen roomGen14 = new WithoutWindowGen(RoomNames.R14.getName(), 34, 34, new StockRoomGen(16), 110);
        RoomGen roomGen15 = new WithWindowGen(RoomNames.R15.getName(), 28, 28, new BureauRoomGen(8), 25);
        RoomGen roomGen16 = new WithoutWindowGen(RoomNames.R16.getName(), 19, 19, new BureauRoomGen(3), 140);
        RoomGen roomGen17 = new WithWindowGen(RoomNames.R17.getName(), 20, 20, new StockRoomGen(14), 5);
        RoomGen roomGen18 = new WithoutWindowGen(RoomNames.R18.getName(), 16, 16, new StockRoomGen(9), 160);
        RoomGen roomGen19 = new WithoutWindowGen(RoomNames.R19.getName(), 35, 35, new StockRoomGen(17), 95);
        RoomGen roomGen20 = new WithWindowGen(RoomNames.R20.getName(), 27, 27, new BureauRoomGen(11), 21);
        RoomGen roomGen21 = new WithoutWindowGen(RoomNames.R21.getName(), 29, 19, new BureauRoomGen(5), 175);
        RoomGen roomGen22 = new WithWindowGen(RoomNames.R22.getName(), 23, 23, new StockRoomGen(13), 7);
        RoomGen roomGen23 = new WithoutWindowGen(RoomNames.R23.getName(), 17, 17, new StockRoomGen(11), 130);
        RoomGen roomGen24 = new WithoutWindowGen(RoomNames.R24.getName(), 32, 32, new StockRoomGen(15), 105);
        RoomGen roomGen25 = new WithWindowGen(RoomNames.R25.getName(), 26, 26, new BureauRoomGen(10), 23);

        RoomGen[] rooms = {
                roomGen10, roomGen11, roomGen12, roomGen13, roomGen14,
                roomGen15, roomGen16, roomGen17, roomGen18, roomGen19,
                roomGen20, roomGen21, roomGen22, roomGen23, roomGen24, roomGen25
        };
        for (RoomGen room : rooms) {
            officeGen1.addRoom(room);
        }


        System.out.println("\nstatistical values: \n");
        System.out.println("average area of all rooms " +
                officeGen1.averageAreaOfAllRooms());
        System.out.println("average area of rooms with windows " +
                officeGen1.averageAreaOfRoomsWithWindows());
        System.out.println("average area of rooms without windows " +
                officeGen1.averageAreaOfRoomsWithoutWindows());
        System.out.println("average stock volume of stock rooms " +
                officeGen1.averageStockVolumeOfStockRooms());
        System.out.println("average number of workplaces of bureau rooms " +
                officeGen1.averageNumberOfWorkplacesOfBureauRooms());
        System.out.println("average ratio of window area to room area of all rooms with windows " +
                officeGen1.averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindows());
        System.out.println("average ratio of window area to room area of all rooms with windows used as bureaus " +
                officeGen1.averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindowsUsedAsBureaus());
        System.out.println("average ratio of window area to room area of all rooms with windows used as stock rooms " +
                officeGen1.averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindowsUsedAsStockRooms());
        System.out.println("average ratio of luminous flux to room area of all rooms without windows " +
                officeGen1.averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindows());
        System.out.println("average ratio of luminous flux to room area of all rooms without windows used as bureaus " +
                officeGen1.averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindowsUsedAsBureaus());
        System.out.println("average ratio of luminous flux to room area of all rooms without windows used as stock rooms " +
                officeGen1.averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindowsUsedAsStockRooms());
        System.out.println("\n==================TEST 4: test end ==================\n");

        System.out.println("\n+++++++++++++++++++++TEST GENERIC END+++++++++++++++++++++\n");


        System.out.println("\n+++++++++++++++++++++TEST NON-GENERIC BEGIN+++++++++++++++++++++\n");
        System.out.println("\n==================TEST 1: test beginn ==================\n");

        System.out.println("creating a company...");
        Company company = new Company("company one");

        Building building1 = new Building(BuildingNames.B1.getName());
        Building building2 = new Building(BuildingNames.B2.getName());
        company.addBuilding(building1);
        company.addBuilding(building2);


        Office office1 = new Office(OfficeIDs.OFFICE1.getId(), 50.0);
        Office office2 = new Office(OfficeIDs.OFFICE2.getId(), 55.0);
        Office office3 = new Office(OfficeIDs.OFFICE3.getId(), 30.0);


        building1.addOffice(office1);
        building1.addOffice(office2);
        building2.addOffice(office3);


        Room room1 = new WithWindow(RoomNames.R1.getName(), 10, 10, new BureauRoom(3), 5.0);
        Room room2 = new WithWindow(RoomNames.R2.getName(), 15, 15, new StockRoom(5), 3.0);
        Room room3 = new WithoutWindow(RoomNames.R3.getName(), 8, 12, new StockRoom(7), 80.0);
        Room room4 = new WithoutWindow(RoomNames.R4.getName(), 15, 5, new BureauRoom(4), 150.0);


        office1.addRoom(room1);
        office1.addRoom(room3);
        office2.addRoom(room2);
        office3.addRoom(room4);

        System.out.println("quantity of the buildings in company: " + company.getBuildingsNumber());
        System.out.println("quantity of offices in " + building1.getName() + ": " + building1.getOfficesNumber());
        System.out.println("quantity of offices in " + building2.getName() + ": " + building2.getOfficesNumber());

        System.out.println("\nrooms in offices via iterator: ");
        office1.printRooms();
        office2.printRooms();
        office3.printRooms();

        System.out.println("company overview now:");
        System.out.println(company);

        System.out.println("\nmodifying rooms...");
        room1.setPurpose(new StockRoom(3));
        room2.changePurpose();

        System.out.println("removing a room...");
        office1.removeRoom(RoomNames.R3.getName());

        System.out.println("\nrooms in offices via iterator after modifications: ");
        office1.printRooms();
        office2.printRooms();
        office3.printRooms();

        System.out.println("company overview now:");
        System.out.println(company);

        System.out.println("\n==================TEST 1: test end ==================\n");


        // weitere Tests wie im ursprünglichen Code...

        System.out.println("\n==================TEST 2: test beginn ==================\n");

        Office office4 = new Office(OfficeIDs.OFFICE4.getId(), 10.0);
        Office office5 = new Office(OfficeIDs.OFFICE5.getId(), 12.0);
        Office office6 = new Office(OfficeIDs.OFFICE6.getId(), 33.0);
        Office office7 = new Office(OfficeIDs.OFFICE7.getId(), 22);
        Office office8 = new Office(OfficeIDs.OFFICE8.getId(), 21);

        System.out.println("\nour building is now contained only from 2 office");
        building1.printOffices();
        System.out.println("adding 4 new offices but one is with the same numberID: ");
        building1.addOffice(office4);
        building1.addOffice(office5);
        try{
            building1.addOffice(office6);
            building1.addOffice(office7);
        }catch(UnsupportedOperationException e){
            System.out.println("error adding office: " + e.getMessage());
        }
        building1.printOffices();
        System.out.println("expected value of offices 5");
        System.out.println("quantity of offices in " + building1.getName() + ": " + building1.getOfficesNumber());


        System.out.println("\nremoving 2 offices but one is with the same numberID: ");
        try{
            building1.removeOffice(office5);
            building1.removeOffice(office8);
        }catch(UnsupportedOperationException e){
            System.out.println("error removing office: " + e.getMessage());
        }
        System.out.println("expected value of offices 4");
        System.out.println("quantity of offices in " + building1.getName() + ": " + building1.getOfficesNumber());
        building1.printOffices();

        System.out.println("\n==================TEST 2: test end ==================\n");


        System.out.println("\n==================TEST 3: test beginn ==================\n");


        Room room5 = new WithWindow(RoomNames.R5.getName(), 24, 31, new BureauRoom(15), 22);
        Room room6 = new WithoutWindow(RoomNames.R6.getName(), 31, 18, new BureauRoom(4), 200);
        Room room7 = new WithWindow(RoomNames.R7.getName(), 21, 21, new StockRoom(15), 5);
        Room room8 = new WithoutWindow(RoomNames.R8.getName(), 13, 13, new StockRoom(12), 150);
        Room room9 = new WithoutWindow(RoomNames.R9.getName(), 33, 33, new StockRoom(14), 100);

        System.out.println("\nour office is now contained only from 1 room");
        office1.printRooms();
        System.out.println("adding 4 new rooms but one is with the same name: ");
        try{
            office1.addRoom(room5);
            office1.addRoom(room6);
            office1.addRoom(room7);
            office1.addRoom(room8);
        }catch(UnsupportedOperationException e){
            System.out.println("error adding room: " + e.getMessage());
        }
        office1.printRooms();
        System.out.println("expected value of rooms 4");
        System.out.println("quantity of rooms in " + office1.getNumberID() + ": " + office1.getUsableRoomsNumber());

        System.out.println("\n\ninfo about office now: \n" + office1);

        System.out.println("\nChanging some info...");
        room6.changePurpose();
        room5.addWorkplace();
        room6.addShelf();

        System.out.println("\nnew info about office now: \n" + office1);

        System.out.println("\n\nremoving 2 rooms but one of them is with the same name: ");
        try{
            office1.removeRoom(room5.getName());
            office1.removeRoom(room6.getName());
            office1.removeRoom(room9.getName());
        }catch(UnsupportedOperationException e){
            System.out.println("error removing room: " + e.getMessage());
        }
        office1.printRooms();
        System.out.println("expected value of rooms 2");
        System.out.println("quantity of rooms in " + office1.getNumberID() + ": " + office1.getUsableRoomsNumber());

        System.out.println("\n==================TEST 3: test end ==================\n");


        System.out.println("\n==================TEST 4: test beginn ==================\n");

        Room room10 = new WithWindow(RoomNames.R10.getName(), 25, 25, new BureauRoom(12), 20);
        Room room11 = new WithoutWindow(RoomNames.R11.getName(), 30, 20, new BureauRoom(6), 180);
        Room room12 = new WithWindow(RoomNames.R12.getName(), 22, 22, new StockRoom(18), 6);
        Room room13 = new WithoutWindow(RoomNames.R13.getName(), 15, 15, new StockRoom(10),120);
        Room room14 = new WithoutWindow(RoomNames.R14.getName(), 34, 34, new StockRoom(16),110);
        Room room15 = new WithWindow(RoomNames.R15.getName(), 28, 28, new BureauRoom(8),25);
        Room room16 = new WithoutWindow(RoomNames.R16.getName(), 19,19,new BureauRoom(3),140);
        Room room17 = new WithWindow(RoomNames.R17.getName(),20,20,new StockRoom(14),5);
        Room room18 = new WithoutWindow(RoomNames.R18.getName(),16,16,new StockRoom(9),160);
        Room room19 = new WithoutWindow(RoomNames.R19.getName(),35,35,new StockRoom(17),95);
        Room room20 = new WithWindow(RoomNames.R20.getName(),27,27,new BureauRoom(11),21);
        Room room21 = new WithoutWindow(RoomNames.R21.getName(),29,19,new BureauRoom(5),175);
        Room room22 = new WithWindow(RoomNames.R22.getName(),23,23,new StockRoom(13),7);
        Room room23 = new WithoutWindow(RoomNames.R23.getName(),17,17,new StockRoom(11),130);
        Room room24 = new WithoutWindow(RoomNames.R24.getName(),32,32,new StockRoom(15),105);
        Room room25 = new WithWindow(RoomNames.R25.getName(),26,26,new BureauRoom(10),23);

        Room[] roomsNonGen = {
                room10, room11, room12, room13, room14,
                room15, room16, room17, room18, room19,
                room20, room21, room22, room23, room24, room25
        };

        // Hinweis: In Test 4 wurden im Original-code officeGen1 verwendet, hier ist es office1
        for (Room room : roomsNonGen) {
            office1.addRoom(room);
        }

        System.out.println("\nstatistical values: \n");
        System.out.println("average area of all rooms " +
                office1.averageAreaOfAllRooms());
        System.out.println("average area of rooms with windows " +
                office1.averageAreaOfRoomsWithWindows());
        System.out.println("average area of rooms without windows " +
                office1.averageAreaOfRoomsWithoutWindows());
        System.out.println("average stock volume of stock rooms " +
                office1.averageStockVolumeOfStockRooms());
        System.out.println("average number of workplaces of bureau rooms " +
                office1.averageNumberOfWorkplacesOfBureauRooms());
        System.out.println("average ratio of window area to room area of all rooms with windows " +
                office1.averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindows());
        System.out.println("average ratio of window area to room area of all rooms with windows used as bureaus " +
                office1.averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindowsUsedAsBureaus());
        System.out.println("average ratio of window area to room area of all rooms with windows used as stock rooms " +
                office1.averageRatioOfWindowAreaToRoomAreaOfAllRoomsWithWindowsUsedAsStockRooms());
        System.out.println("average ratio of luminous flux to room area of all rooms without windows " +
                office1.averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindows());
        System.out.println("average ratio of luminous flux to room area of all rooms without windows used as bureaus " +
                office1.averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindowsUsedAsBureaus());
        System.out.println("average ratio of luminous flux to room area of all rooms without windows used as stock rooms " +
                office1.averageRatioOfLuminousFluxToRoomAreaOfAllRoomsWithoutWindowsUsedAsStockRooms());


        System.out.println("\n==================TEST 4: test end ==================\n");

        System.out.println("\n+++++++++++++++++++++TEST NON-GENERIC END+++++++++++++++++++++\n");

    }
}
