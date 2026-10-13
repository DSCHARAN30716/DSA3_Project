import model.Driver;
import model.Shipment;
import model.Vehicle;
import model.Warehouse;

import service.AddressCorrection;
import service.DeliveryPlanner;
import service.DriverAssignment;
import service.ShipmentRanking;
import service.ShipmentSearch;
import service.WarehouseFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static List<Vehicle> vehicles =
            new ArrayList<>();

    static List<Driver> drivers =
            new ArrayList<>();

    static List<Shipment> shipments =
            new ArrayList<>();

    static List<Warehouse> warehouses =
            new ArrayList<>();

    static ShipmentSearch shipmentSearch;
    static AddressCorrection addressCorrection;
    static DeliveryPlanner deliveryPlanner;
    static DriverAssignment driverAssignment;
    static WarehouseFlow warehouseFlow;
    static ShipmentRanking shipmentRanking;

    public static void main(String[] args) {

        shipmentSearch =
                new ShipmentSearch(shipments);

        addressCorrection =
                new AddressCorrection();

        deliveryPlanner =
                new DeliveryPlanner();

        driverAssignment =
                new DriverAssignment();

        warehouseFlow =
                new WarehouseFlow();

        shipmentRanking =
                new ShipmentRanking();

        while (true) {

            showMainMenu();

            int choice =
                    readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    vehicleManagement();
                    break;

                case 2:
                    driverManagement();
                    break;

                case 3:
                    shipmentManagement();
                    break;

                case 4:
                    warehouseManagement();
                    break;

                case 5:
                    shipmentSearch();
                    break;

                case 6:
                    addressCorrection();
                    break;

                case 7:
                    deliveryPlanning();
                    break;

                case 8:
                    driverAssignment();
                    break;

                case 9:
                    warehouseFlow();
                    break;

                case 10:
                    shipmentRanking();
                    break;

                case 11:
                    completeDemo();
                    break;

                case 0:
                    System.out.println(
                            "\nThank you for using FleetFlow!"
                    );
                    return;

                default:
                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }

    // =====================================================
    // MAIN MENU
    // =====================================================

    static void showMainMenu() {

        System.out.println();
        System.out.println(
                "================================================"
        );

        System.out.println(
                "                  FLEETFLOW"
        );

        System.out.println(
                "       INTELLIGENT LOGISTICS PLATFORM"
        );

        System.out.println(
                "================================================"
        );

        System.out.println("1. Vehicle Management");
        System.out.println("2. Driver Management");
        System.out.println("3. Shipment Management");
        System.out.println("4. Warehouse Management");

        System.out.println();

        System.out.println("5. Search Shipment (KMP)");
        System.out.println("6. Correct Address (Levenshtein)");
        System.out.println("7. Plan Delivery Route (Bitmask DP)");
        System.out.println("8. Assign Drivers (Bipartite Matching)");
        System.out.println("9. Warehouse Flow (Dinic)");
        System.out.println("10. Rank Shipments (Randomized QuickSort)");

        System.out.println();

        System.out.println("11. Complete FleetFlow Demo");
        System.out.println("0. Exit");

        System.out.println(
                "================================================"
        );
    }

    // =====================================================
    // VEHICLES
    // =====================================================

    static void vehicleManagement() {

        while (true) {

            System.out.println();
            System.out.println(
                    "========== VEHICLE MANAGEMENT =========="
            );

            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicle");
            System.out.println("3. View All Vehicles");
            System.out.println("0. Back");

            int choice =
                    readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    addVehicle();
                    break;

                case 2:
                    viewVehicle();
                    break;

                case 3:
                    viewAllVehicles();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }

    static void addVehicle() {

        System.out.println();
        System.out.println(
                "---------- ADD VEHICLE ----------"
        );

        String id =
                readString("Enter Vehicle ID: ");

        if (findVehicle(id) != null) {

            System.out.println(
                    "Vehicle already exists!"
            );

            return;
        }

        String type =
                readString("Enter Vehicle Type: ");

        int capacity =
                readInt("Enter Capacity: ");

        String location =
                readString("Enter Current Location: ");

        Vehicle vehicle =
                new Vehicle(
                        id,
                        type,
                        capacity,
                        location,
                        "Available"
                );

        vehicles.add(vehicle);

        System.out.println(
                "\nVehicle registered successfully!"
        );

        displayVehicle(vehicle);
    }

    static void viewVehicle() {

        String id =
                readString("Enter Vehicle ID: ");

        Vehicle vehicle =
                findVehicle(id);

        if (vehicle == null) {

            System.out.println(
                    "Vehicle not found."
            );

            return;
        }

        displayVehicle(vehicle);
    }

    static void viewAllVehicles() {

        if (vehicles.isEmpty()) {

            System.out.println(
                    "No vehicles registered."
            );

            return;
        }

        for (Vehicle vehicle : vehicles) {

            displayVehicle(vehicle);

            System.out.println(
                    "--------------------------------"
            );
        }
    }

    static Vehicle findVehicle(String id) {

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleId()
                    .equalsIgnoreCase(id)) {

                return vehicle;
            }
        }

        return null;
    }

    static void displayVehicle(
            Vehicle vehicle) {

        System.out.println();
        System.out.println(
                "Vehicle ID       : "
                        + vehicle.getVehicleId()
        );

        System.out.println(
                "Vehicle Type     : "
                        + vehicle.getVehicleType()
        );

        System.out.println(
                "Capacity         : "
                        + vehicle.getCapacity()
        );

        System.out.println(
                "Current Location : "
                        + vehicle.getCurrentLocation()
        );

        System.out.println(
                "Status           : "
                        + vehicle.getStatus()
        );
    }

    // =====================================================
    // DRIVERS
    // =====================================================

    static void driverManagement() {

        while (true) {

            System.out.println();
            System.out.println(
                    "========== DRIVER MANAGEMENT =========="
            );

            System.out.println("1. Add Driver");
            System.out.println("2. View Driver");
            System.out.println("3. View Available Drivers");
            System.out.println("0. Back");

            int choice =
                    readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    addDriver();
                    break;

                case 2:
                    viewDriver();
                    break;

                case 3:
                    viewAvailableDrivers();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }

    static void addDriver() {

        System.out.println();
        System.out.println(
                "---------- ADD DRIVER ----------"
        );

        String id =
                readString("Enter Driver ID: ");

        if (findDriver(id) != null) {

            System.out.println(
                    "Driver already exists!"
            );

            return;
        }

        String name =
                readString("Enter Driver Name: ");

        String location =
                readString("Enter Driver Location: ");

        Driver driver =
                new Driver(
                        id,
                        name,
                        location,
                        "Available"
                );

        drivers.add(driver);

        System.out.println(
                "\nDriver registered successfully!"
        );

        displayDriver(driver);
    }

    static void viewDriver() {

        String id =
                readString("Enter Driver ID: ");

        Driver driver =
                findDriver(id);

        if (driver == null) {

            System.out.println(
                    "Driver not found."
            );

            return;
        }

        displayDriver(driver);
    }

    static void viewAvailableDrivers() {

        boolean found = false;

        System.out.println();
        System.out.println(
                "========== AVAILABLE DRIVERS =========="
        );

        for (Driver driver : drivers) {

            if (driver.getStatus()
                    .equalsIgnoreCase("Available")) {

                System.out.println(
                        driver.getDriverId()
                                + " - "
                                + driver.getName()
                                + " - "
                                + driver.getLocation()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No available drivers."
            );
        }
    }

    static Driver findDriver(String id) {

        for (Driver driver : drivers) {

            if (driver.getDriverId()
                    .equalsIgnoreCase(id)) {

                return driver;
            }
        }

        return null;
    }

    static void displayDriver(
            Driver driver) {

        System.out.println();
        System.out.println(
                "Driver ID : "
                        + driver.getDriverId()
        );

        System.out.println(
                "Name      : "
                        + driver.getName()
        );

        System.out.println(
                "Location  : "
                        + driver.getLocation()
        );

        System.out.println(
                "Status    : "
                        + driver.getStatus()
        );
    }

    // =====================================================
    // SHIPMENTS
    // =====================================================

    static void shipmentManagement() {

        while (true) {

            System.out.println();
            System.out.println(
                    "========== SHIPMENT MANAGEMENT =========="
            );

            System.out.println("1. Add Shipment");
            System.out.println("2. View Shipment");
            System.out.println("3. View All Shipments");
            System.out.println("4. Update Shipment Status");
            System.out.println("0. Back");

            int choice =
                    readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    addShipment();
                    break;

                case 2:
                    viewShipment();
                    break;

                case 3:
                    viewAllShipments();
                    break;

                case 4:
                    updateShipmentStatus();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }

    static void addShipment() {

        System.out.println();
        System.out.println(
                "---------- ADD SHIPMENT ----------"
        );

        String id =
                readString("Enter Shipment ID: ");

        if (findShipment(id) != null) {

            System.out.println(
                    "Shipment already exists!"
            );

            return;
        }

        String customer =
                readString("Enter Customer Name: ");

        String packageCode =
                readString("Enter Package Code: ");

        String source =
                readString("Enter Source: ");

        String destination =
                readString("Enter Destination: ");

        double weight =
                readDouble("Enter Weight (kg): ");

        Shipment shipment =
                new Shipment(
                        id,
                        customer,
                        packageCode,
                        source,
                        destination,
                        weight
                );

        shipments.add(shipment);

        System.out.println(
                "\nShipment created successfully!"
        );

        displayShipment(shipment);
    }

    static void viewShipment() {

        String id =
                readString("Enter Shipment ID: ");

        Shipment shipment =
                findShipment(id);

        if (shipment == null) {

            System.out.println(
                    "Shipment not found."
            );

            return;
        }

        displayShipment(shipment);
    }

    static void viewAllShipments() {

        if (shipments.isEmpty()) {

            System.out.println(
                    "No shipments available."
            );

            return;
        }

        for (Shipment shipment : shipments) {

            displayShipment(shipment);

            System.out.println(
                    "--------------------------------"
            );
        }
    }

    static void updateShipmentStatus() {

        String id =
                readString("Enter Shipment ID: ");

        Shipment shipment =
                findShipment(id);

        if (shipment == null) {

            System.out.println(
                    "Shipment not found."
            );

            return;
        }

        String status =
                readString(
                        "Enter new status: "
                );

        shipment.setStatus(status);

        System.out.println(
                "Shipment status updated."
        );
    }

    static Shipment findShipment(String id) {

        for (Shipment shipment : shipments) {

            if (shipment.getShipmentId()
                    .equalsIgnoreCase(id)) {

                return shipment;
            }
        }

        return null;
    }

    static void displayShipment(
            Shipment shipment) {

        System.out.println();
        System.out.println(
                "Shipment ID  : "
                        + shipment.getShipmentId()
        );

        System.out.println(
                "Customer     : "
                        + shipment.getCustomerName()
        );

        System.out.println(
                "Package Code : "
                        + shipment.getPackageCode()
        );

        System.out.println(
                "Source       : "
                        + shipment.getSource()
        );

        System.out.println(
                "Destination  : "
                        + shipment.getDestination()
        );

        System.out.println(
                "Weight       : "
                        + shipment.getWeight()
                        + " kg"
        );

        System.out.println(
                "Status       : "
                        + shipment.getStatus()
        );
    }

    // =====================================================
    // WAREHOUSE
    // =====================================================

    static void warehouseManagement() {

        while (true) {

            System.out.println();
            System.out.println(
                    "========== WAREHOUSE MANAGEMENT =========="
            );

            System.out.println("1. Add Warehouse");
            System.out.println("2. View Warehouse");
            System.out.println("3. View All Warehouses");
            System.out.println("0. Back");

            int choice =
                    readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    addWarehouse();
                    break;

                case 2:
                    viewWarehouse();
                    break;

                case 3:
                    viewAllWarehouses();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(
                            "Invalid choice!"
                    );
            }
        }
    }

    static void addWarehouse() {

        System.out.println();
        System.out.println(
                "---------- ADD WAREHOUSE ----------"
        );

        String id =
                readString("Enter Warehouse ID: ");

        if (findWarehouse(id) != null) {

            System.out.println(
                    "Warehouse already exists!"
            );

            return;
        }

        String name =
                readString("Enter Warehouse Name: ");

        String location =
                readString("Enter Location: ");

        int capacity =
                readInt("Enter Capacity: ");

        Warehouse warehouse =
                new Warehouse(
                        id,
                        name,
                        location,
                        capacity
                );

        warehouses.add(warehouse);

        System.out.println(
                "\nWarehouse added successfully!"
        );

        displayWarehouse(warehouse);
    }

    static void viewWarehouse() {

        String id =
                readString("Enter Warehouse ID: ");

        Warehouse warehouse =
                findWarehouse(id);

        if (warehouse == null) {

            System.out.println(
                    "Warehouse not found."
            );

            return;
        }

        displayWarehouse(warehouse);
    }

    static void viewAllWarehouses() {

        if (warehouses.isEmpty()) {

            System.out.println(
                    "No warehouses available."
            );

            return;
        }

        for (Warehouse warehouse : warehouses) {

            displayWarehouse(warehouse);

            System.out.println(
                    "--------------------------------"
            );
        }
    }

    static Warehouse findWarehouse(
            String id) {

        for (Warehouse warehouse : warehouses) {

            if (warehouse.getWarehouseId()
                    .equalsIgnoreCase(id)) {

                return warehouse;
            }
        }

        return null;
    }

    static void displayWarehouse(
            Warehouse warehouse) {

        System.out.println();
        System.out.println(
                "Warehouse ID : "
                        + warehouse.getWarehouseId()
        );

        System.out.println(
                "Name         : "
                        + warehouse.getName()
        );

        System.out.println(
                "Location     : "
                        + warehouse.getLocation()
        );

        System.out.println(
                "Capacity     : "
                        + warehouse.getCapacity()
        );
    }

    // =====================================================
    // KMP
    // =====================================================

    static void shipmentSearch() {

        String keyword =
                readString(
                        "Enter shipment ID, customer, package code or location: "
                );

        shipmentSearch.displayResults(
                keyword
        );
    }

    // =====================================================
    // LEVENSHTEIN
    // =====================================================

    static void addressCorrection() {

        String address =
                readString(
                        "Enter delivery address: "
                );

        addressCorrection.displayCorrection(
                address
        );
    }

    // =====================================================
    // BITMASK DP
    // =====================================================

    static void deliveryPlanning() {

        if (shipments.isEmpty()) {

            System.out.println(
                    "No shipments available."
            );

            return;
        }

        String start =
                readString(
                        "Enter starting location: "
                );

        List<String> destinations =
                new ArrayList<>();

        for (Shipment shipment : shipments) {

            if (!destinations.contains(
                    shipment.getDestination())) {

                destinations.add(
                        shipment.getDestination()
                );
            }
        }

        deliveryPlanner.planRoute(
                start,
                destinations
        );
    }

    // =====================================================
    // BIPARTITE MATCHING
    // =====================================================

    static void driverAssignment() {

        driverAssignment.assignDrivers(
                drivers,
                shipments
        );
    }

    // =====================================================
    // DINIC
    // =====================================================

    static void warehouseFlow() {

        warehouseFlow.calculateFlow(
                warehouses
        );
    }

    // =====================================================
    // RANDOMIZED QUICKSORT
    // =====================================================

    static void shipmentRanking() {

        shipmentRanking.rankShipments(
                shipments
        );
    }

    // =====================================================
    // COMPLETE DEMO
    // =====================================================

    static void completeDemo() {

        System.out.println();
        System.out.println(
                "================================================"
        );

        System.out.println(
                "           FLEETFLOW LIVE ANALYTICS"
        );

        System.out.println(
                "================================================"
        );

        System.out.println(
                "Vehicles   : " + vehicles.size()
        );

        System.out.println(
                "Drivers    : " + drivers.size()
        );

        System.out.println(
                "Shipments  : " + shipments.size()
        );

        System.out.println(
                "Warehouses : " + warehouses.size()
        );

        if (!shipments.isEmpty()) {

            System.out.println();
            System.out.println(
                    "--- Shipment Search ---"
            );

            shipmentSearch.displayResults(
                    shipments.get(0).getShipmentId()
            );

            System.out.println();
            System.out.println(
                    "--- Shipment Ranking ---"
            );

            shipmentRanking.rankShipments(
                    shipments
            );
        }

        if (!warehouses.isEmpty()) {

            System.out.println();
            System.out.println(
                    "--- Warehouse Flow ---"
            );

            warehouseFlow.calculateFlow(
                    warehouses
            );
        }

        System.out.println();
        System.out.println(
                "================================================"
        );

        System.out.println(
                "             DEMO COMPLETED"
        );

        System.out.println(
                "================================================"
        );
    }

    // =====================================================
    // INPUT
    // =====================================================

    static String readString(
            String message) {

        System.out.print(message);

        return sc.nextLine().trim();
    }

    static int readInt(
            String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        sc.nextLine().trim()
                );

            } catch (Exception e) {

                System.out.println(
                        "Enter a valid integer."
                );
            }
        }
    }

    static double readDouble(
            String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        sc.nextLine().trim()
                );

            } catch (Exception e) {

                System.out.println(
                        "Enter a valid number."
                );
            }
        }
    }
}