import UnionFind.*;

import java.util.*;

public class Solver {
    private List<Edge>[] edges;
    private int numOfLocations;

    public Solver (List<Edge>[] edges, int numOfLocations) {
        this.edges = edges;
        this.numOfLocations = numOfLocations;
    }

    @SuppressWarnings("unchecked")
    public int[] solve(int[][] operations) {

        List<Edge>[] mst = mstPrim(edges);
        int[] solution = new int[operations.length];
        for (int i = 0; i < operations.length; i++) {
            int start = operations[i][0];
            int end = operations[i][1];
            int[] hardness = new int[numOfLocations];
            boolean[] visited = new boolean[numOfLocations];
            Arrays.fill(hardness, -1);
            boolean found = false;

            Queue<Integer> frontier = new LinkedList<>();
            frontier.add(start);

            while (!frontier.isEmpty() && !found) {
                int node = frontier.poll();
                for (Edge edge : mst[node]) {
                    int neighbor = (edge.first() == node) ? edge.second() : edge.first();
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        hardness[neighbor] = Math.max(hardness[node], edge.hardness());
                        if(neighbor == end){
                            found = true;
                            solution[i] = hardness[end];
                        }
                        else
                            frontier.add(neighbor);
                    }
                }
            }
        }

        return solution;
    }

    @SuppressWarnings("unchecked")
    private List<Edge>[] mstPrim(List<Edge>[] graph) {
        // Inicializa a lista de adjacência da MST
        List<Edge>[] mst = new ArrayList[numOfLocations];
        for (int i = 0; i < numOfLocations; i++) {
            mst[i] = new ArrayList<>();
        }

        boolean[] selected = new boolean[numOfLocations];
        int[] cost = new int[numOfLocations];
        Edge[] via = new Edge[numOfLocations];
        PriorityQueue<Node> connected = new PriorityQueue<>(Comparator.comparingInt(Node::cost));

        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;
        connected.add(new Node(0, 0));

        while (!connected.isEmpty()) {
            Node node = connected.poll();
            int u = node.node();

            if (!selected[u]) {
                selected[u] = true;
                if (u != 0) {
                    // Adiciona a aresta à lista de adjacência (em ambas as direções, pois o grafo é não direcionado)
                    int v = (via[u].first() == u) ? via[u].second() : via[u].first();
                    mst[u].add(via[u]);
                    mst[v].add(via[u]);
                }

                for (Edge edge : graph[u]) {
                    int v = (edge.first() == u) ? edge.second() : edge.first();
                    int weight = edge.hardness();
                    if (!selected[v] && weight < cost[v]) {
                        cost[v] = weight;
                        via[v] = edge;
                        connected.add(new Node(v, cost[v]));
                    }
                }
            }
        }
        return mst;
    }


}
