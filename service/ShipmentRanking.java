package service;

import algorithms.RandomizedQuickSort;
import model.Shipment;

import java.util.ArrayList;
import java.util.List;

public class ShipmentRanking {

    public void rankShipments(
            List<Shipment> shipments) {

        System.out.println();
        System.out.println(
                "========== SHIPMENT RANKING =========="
        );

        if (shipments.isEmpty()) {

            System.out.println(
                    "No shipments available."
            );

            return;
        }

        /*
         * Rank based on shipment weight.
         *
         * Heavier shipments are processed first
         * in this demonstration.
         */

        double[] weights =
                new double[shipments.size()];

        for (int i = 0;
             i < shipments.size();
             i++) {

            weights[i] =
                    shipments.get(i).getWeight();
        }

        RandomizedQuickSort.sort(weights);

        System.out.println();
        System.out.println(
                "Shipments ranked by weight:"
        );

        boolean[] used =
                new boolean[shipments.size()];

        int rank = 1;

        /*
         * Display from highest weight
         * to lowest weight.
         */

        for (int i = weights.length - 1;
             i >= 0;
             i--) {

            for (int j = 0;
                 j < shipments.size();
                 j++) {

                if (!used[j]
                        &&
                        shipments.get(j)
                                .getWeight()
                                == weights[i]) {

                    Shipment shipment =
                            shipments.get(j);

                    System.out.println(
                            rank
                                    + ". "
                                    + shipment.getShipmentId()
                                    + " | "
                                    + shipment.getCustomerName()
                                    + " | "
                                    + shipment.getWeight()
                                    + " kg | "
                                    + shipment.getSource()
                                    + " -> "
                                    + shipment.getDestination()
                    );

                    used[j] = true;
                    rank++;

                    break;
                }
            }
        }

        System.out.println();
        System.out.println(
                "Algorithm Used: Randomized QuickSort"
        );
    }
}