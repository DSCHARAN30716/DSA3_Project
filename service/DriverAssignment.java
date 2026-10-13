package service;

import algorithms.BipartiteMatching;
import model.Driver;
import model.Shipment;

import java.util.ArrayList;
import java.util.List;

public class DriverAssignment {

    public void assignDrivers(
            List<Driver> drivers,
            List<Shipment> shipments) {

        System.out.println();
        System.out.println(
                "========== DRIVER ASSIGNMENT =========="
        );

        List<Driver> availableDrivers =
                new ArrayList<>();

        List<Shipment> pendingShipments =
                new ArrayList<>();

        for (Driver driver : drivers) {

            if (driver.getStatus()
                    .equalsIgnoreCase("Available")) {

                availableDrivers.add(driver);
            }
        }

        for (Shipment shipment : shipments) {

            if (!shipment.getStatus()
                    .equalsIgnoreCase("Delivered")
                    &&
                    !shipment.getStatus()
                            .equalsIgnoreCase("Cancelled")) {

                pendingShipments.add(shipment);
            }
        }

        if (availableDrivers.isEmpty()) {

            System.out.println(
                    "No available drivers."
            );

            return;
        }

        if (pendingShipments.isEmpty()) {

            System.out.println(
                    "No pending shipments."
            );

            return;
        }

        int[][] graph =
                new int[
                        availableDrivers.size()
                ][
                        pendingShipments.size()
                ];

        /*
         * Driver is compatible when:
         *
         * Driver's current location
         * =
         * Shipment's source
         */

        for (int i = 0;
             i < availableDrivers.size();
             i++) {

            Driver driver =
                    availableDrivers.get(i);

            for (int j = 0;
                 j < pendingShipments.size();
                 j++) {

                Shipment shipment =
                        pendingShipments.get(j);

                if (driver.getLocation()
                        .equalsIgnoreCase(
                                shipment.getSource())) {

                    graph[i][j] = 1;
                }
            }
        }

        int[] matching =
                BipartiteMatching.maxMatching(graph);

        int count = 0;

        for (int i = 0;
             i < matching.length;
             i++) {

            int shipmentIndex =
                    matching[i];

            if (shipmentIndex != -1) {

                Driver driver =
                        availableDrivers.get(i);

                Shipment shipment =
                        pendingShipments.get(
                                shipmentIndex
                        );

                driver.setStatus("Assigned");

                shipment.setStatus("Assigned");

                System.out.println(
                        driver.getDriverId()
                                + " - "
                                + driver.getName()
                                + " -> "
                                + shipment.getShipmentId()
                                + " ("
                                + shipment.getSource()
                                + " -> "
                                + shipment.getDestination()
                                + ")"
                );

                count++;
            }
        }

        System.out.println();
        System.out.println(
                "Successful Assignments: "
                        + count
        );

        System.out.println(
                "Algorithm Used: Bipartite Matching"
        );
    }
}