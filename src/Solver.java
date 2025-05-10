/**
 * @author André Filipe 65371, Guilherme Fialho 65581
 */
import java.util.*;


public class Solver {
    private static final int FIRST_NODE_STATS = 0;
    private static final int HARDNESS_INITIAL_VALUE = -1;
    private static final int COST_INITIAL_VALUE = 0;

    private final List<Edge>[] edges;
    private final int numOfLocations;

    public Solver (List<Edge>[] edges, int numOfLocations) {
        this.edges = edges;
        this.numOfLocations = numOfLocations;
    }

    public int[] solve(int[][] operations) {

        List<Edge>[] mst = mstPrim(edges);
        int[] solution = new int[operations.length];
        for (int i = 0; i < operations.length; i++) {
            int start = operations[i][0];
            int end = operations[i][1];
            int[] hardness = new int[numOfLocations];
            boolean[] visited = new boolean[numOfLocations];
            Arrays.fill(hardness, HARDNESS_INITIAL_VALUE);
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

    //In this method, we used ChatGPT to understand better the use of some data structure and the reasons why
    @SuppressWarnings("unchecked")
    private List<Edge>[] mstPrim(List<Edge>[] graph) {
        List<Edge>[] mst = new List[numOfLocations];
        for (int i = 0; i < numOfLocations; i++) {
            mst[i] = new LinkedList<>();
        }

        boolean[] selected = new boolean[numOfLocations];
        int[] cost = new int[numOfLocations];
        Edge[] via = new Edge[numOfLocations];
        PriorityQueue<Node> connected = new PriorityQueue<>(Comparator.comparingInt(Node::cost));

        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = COST_INITIAL_VALUE;
        connected.add(new Node(FIRST_NODE_STATS, FIRST_NODE_STATS));

        while (!connected.isEmpty()) {
            Node node = connected.poll();
            int u = node.node();

            if (!selected[u]) {
                selected[u] = true;
                if (u != COST_INITIAL_VALUE) {
                    int v = (via[u].first() == u) ? via[u].second() : via[u].first();
                    mst[u].add(via[u]);
                    mst[v].add(via[u]);
                }
                //This section of the code was made with the help of ChatGPT,
                // more precisely the use of the method add() everytime (redundancy) instead of using remove and then add in some cases
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
