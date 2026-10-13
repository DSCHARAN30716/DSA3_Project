package algorithms;

import java.util.Arrays;

public class BipartiteMatching {

    private static int[] matchShipment;
    private static boolean[] visited;
    private static int[][] graph;

    public static int[] maxMatching(int[][] inputGraph) {

        if (inputGraph == null ||
                inputGraph.length == 0) {

            return new int[0];
        }

        graph = inputGraph;

        int drivers = graph.length;

        int shipments = graph[0].length;

        matchShipment =
                new int[shipments];

        Arrays.fill(matchShipment, -1);

        int[] driverMatch =
                new int[drivers];

        Arrays.fill(driverMatch, -1);

        for (int driver = 0;
             driver < drivers;
             driver++) {

            visited =
                    new boolean[shipments];

            tryMatch(driver);
        }

        for (int shipment = 0;
             shipment < shipments;
             shipment++) {

            if (matchShipment[shipment] != -1) {

                int driver =
                        matchShipment[shipment];

                driverMatch[driver] =
                        shipment;
            }
        }

        return driverMatch;
    }

    private static boolean tryMatch(int driver) {

        for (int shipment = 0;
             shipment < graph[driver].length;
             shipment++) {

            if (graph[driver][shipment] == 0) {
                continue;
            }

            if (visited[shipment]) {
                continue;
            }

            visited[shipment] = true;

            if (matchShipment[shipment] == -1 ||
                    tryMatch(matchShipment[shipment])) {

                matchShipment[shipment] =
                        driver;

                return true;
            }
        }

        return false;
    }
}