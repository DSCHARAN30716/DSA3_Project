package algorithms;

import java.util.*;

public class Dinic {

    private static class Edge {

        int to;
        int capacity;
        int reverse;

        Edge(int to,
             int capacity,
             int reverse) {

            this.to = to;
            this.capacity = capacity;
            this.reverse = reverse;
        }
    }

    private List<Edge>[] graph;

    private int[] level;
    private int[] pointer;

    @SuppressWarnings("unchecked")
    public Dinic(int vertices) {

        graph =
                new ArrayList[vertices];

        for (int i = 0;
             i < vertices;
             i++) {

            graph[i] =
                    new ArrayList<>();
        }

        level =
                new int[vertices];

        pointer =
                new int[vertices];
    }

    public void addEdge(
            int from,
            int to,
            int capacity) {

        Edge forward =
                new Edge(
                        to,
                        capacity,
                        graph[to].size()
                );

        Edge backward =
                new Edge(
                        from,
                        0,
                        graph[from].size()
                );

        graph[from].add(forward);
        graph[to].add(backward);
    }

    private boolean bfs(
            int source,
            int sink) {

        Arrays.fill(level, -1);

        Queue<Integer> queue =
                new LinkedList<>();

        queue.add(source);

        level[source] = 0;

        while (!queue.isEmpty()) {

            int current =
                    queue.poll();

            for (Edge edge :
                    graph[current]) {

                if (edge.capacity > 0 &&
                        level[edge.to] == -1) {

                    level[edge.to] =
                            level[current] + 1;

                    queue.add(edge.to);
                }
            }
        }

        return level[sink] != -1;
    }

    private int dfs(
            int current,
            int sink,
            int pushed) {

        if (pushed == 0) {
            return 0;
        }

        if (current == sink) {
            return pushed;
        }

        for (; pointer[current]
                < graph[current].size();
             pointer[current]++) {

            Edge edge =
                    graph[current]
                            .get(pointer[current]);

            if (level[edge.to] !=
                    level[current] + 1) {

                continue;
            }

            if (edge.capacity <= 0) {
                continue;
            }

            int flow =
                    dfs(
                            edge.to,
                            sink,
                            Math.min(
                                    pushed,
                                    edge.capacity
                            )
                    );

            if (flow == 0) {
                continue;
            }

            edge.capacity -= flow;

            graph[edge.to]
                    .get(edge.reverse)
                    .capacity += flow;

            return flow;
        }

        return 0;
    }

    public int maxFlow(
            int source,
            int sink) {

        int flow = 0;

        int infinity =
                Integer.MAX_VALUE;

        while (bfs(source, sink)) {

            Arrays.fill(pointer, 0);

            int pushed;

            while ((pushed =
                    dfs(
                            source,
                            sink,
                            infinity
                    )) != 0) {

                flow += pushed;
            }
        }

        return flow;
    }
}