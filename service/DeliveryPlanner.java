package service;

import algorithms.BitmaskDP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryPlanner {

    private final Scanner sc = new Scanner(System.in);

    public void planRoute(String start,
                           List<String> locations) {

        System.out.println();
        System.out.println("========== DELIVERY ROUTE PLANNER ==========");

        if (locations == null || locations.isEmpty()) {

            System.out.println(
                    "No delivery locations available."
            );

            return;
        }

        List<String> places =
                new ArrayList<>();

        places.add(start);

        for (String location : locations) {

            if (!location.equalsIgnoreCase(start)
                    && !places.contains(location)) {

                places.add(location);
            }
        }

        int n = places.size();

        if (n > 10) {

            System.out.println(
                    "Bitmask DP is intended for small-scale routes."
            );

            System.out.println(
                    "Maximum supported locations: 10"
            );

            return;
        }

        System.out.println();
        System.out.println("Locations:");

        for (int i = 0; i < n; i++) {

            System.out.println(
                    i + ". " + places.get(i)
            );
        }

        int[][] distance =
                new int[n][n];

        System.out.println();
        System.out.println(
                "Enter road distances in kilometres."
        );

        System.out.println(
                "Enter 0 for the same location."
        );

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int d;

                while (true) {

                    System.out.print(
                            "Distance "
                                    + places.get(i)
                                    + " -> "
                                    + places.get(j)
                                    + ": "
                    );

                    try {

                        d = Integer.parseInt(
                                sc.nextLine().trim()
                        );

                        if (d <= 0) {

                            System.out.println(
                                    "Distance must be greater than 0."
                            );

                            continue;
                        }

                        break;

                    } catch (Exception e) {

                        System.out.println(
                                "Enter a valid number."
                        );
                    }
                }

                distance[i][j] = d;
                distance[j][i] = d;
            }
        }

        int[] route =
                BitmaskDP.solve(distance);

        System.out.println();
        System.out.println(
                "========== OPTIMIZED ROUTE =========="
        );

        int totalDistance = 0;

        for (int i = 0; i < route.length; i++) {

            System.out.print(
                    places.get(route[i])
            );

            if (i < route.length - 1) {

                int from = route[i];
                int to = route[i + 1];

                totalDistance +=
                        distance[from][to];

                System.out.print(" -> ");
            }
        }

        System.out.println();

        System.out.println(
                "Total Distance: "
                        + totalDistance
                        + " km"
        );

        System.out.println(
                "Algorithm Used: Bitmask DP"
        );
    }
}