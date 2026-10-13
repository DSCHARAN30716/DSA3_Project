package service;

import algorithms.KMP;
import model.Shipment;

import java.util.ArrayList;
import java.util.List;

public class ShipmentSearch {

    private final List<Shipment> shipments;

    public ShipmentSearch(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public List<Shipment> search(String keyword) {

        List<Shipment> result = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return result;
        }

        keyword = keyword.toLowerCase();

        for (Shipment shipment : shipments) {

            String searchableText =
                    shipment.getShipmentId() + " " +
                    shipment.getCustomerName() + " " +
                    shipment.getPackageCode() + " " +
                    shipment.getSource() + " " +
                    shipment.getDestination();

            searchableText = searchableText.toLowerCase();

            if (KMP.search(searchableText, keyword) != -1) {
                result.add(shipment);
            }
        }

        return result;
    }

    public void displayResults(String keyword) {

        List<Shipment> result = search(keyword);

        System.out.println();
        System.out.println("========== KMP SHIPMENT SEARCH ==========");

        if (result.isEmpty()) {
            System.out.println("No matching shipment found.");
            return;
        }

        System.out.println("Keyword: " + keyword);
        System.out.println("Matches found: " + result.size());
        System.out.println();

        for (Shipment shipment : result) {

            System.out.println(
                    shipment.getShipmentId()
                            + " | "
                            + shipment.getCustomerName()
                            + " | "
                            + shipment.getPackageCode()
                            + " | "
                            + shipment.getSource()
                            + " -> "
                            + shipment.getDestination()
            );
        }
    }
}