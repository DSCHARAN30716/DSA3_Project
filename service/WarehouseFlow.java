package service;

import algorithms.Dinic;
import model.Warehouse;

import java.util.List;

public class WarehouseFlow {

    public void calculateFlow(
            List<Warehouse> warehouses) {

        System.out.println();
        System.out.println(
                "========== WAREHOUSE FLOW =========="
        );

        if (warehouses.size() < 2) {

            System.out.println(
                    "At least 2 warehouses are required."
            );

            return;
        }

        int n = warehouses.size();

        Dinic dinic =
                new Dinic(n);

        /*
         * For this project model:
         *
         * Warehouse 1 → Warehouse 2
         * Warehouse 2 → Warehouse 3
         * ...
         *
         * Capacity is determined by the
         * smaller warehouse capacity.
         */

        for (int i = 0; i < n - 1; i++) {

            int capacity =
                    Math.min(
                            warehouses.get(i).getCapacity(),
                            warehouses.get(i + 1).getCapacity()
                    );

            dinic.addEdge(
                    i,
                    i + 1,
                    capacity
            );
        }

        int source = 0;
        int sink = n - 1;

        int maximumFlow =
                dinic.maxFlow(
                        source,
                        sink
                );

        System.out.println();

        System.out.println(
                "Source Warehouse : "
                        + warehouses.get(0)
                        .getName()
        );

        System.out.println(
                "Destination      : "
                        + warehouses.get(n - 1)
                        .getName()
        );

        System.out.println(
                "Maximum Flow     : "
                        + maximumFlow
                        + " packages"
        );

        System.out.println(
                "Algorithm Used   : Dinic's Algorithm"
        );
    }
}