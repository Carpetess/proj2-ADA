import UnionFind.*;

import java.util.*;

public class Solver {
    private List<Edge>[] allEdges;
    private int numOfLocations;

    public Solver(List<Edge>[] allEdges, int numOfLocations) {
        this.allEdges = allEdges;
        this.numOfLocations = numOfLocations;
    }

    public int[] solve(int[][] operations) {

        List<Edge>[] mst = mstPrim(allEdges);
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

    private List<Edge>[] mstPrim(List<Edge>[] graph) {
        List<Edge>[] mst = new List[numOfLocations];
        boolean[] selected = new boolean[numOfLocations];
        Arrays.fill(selected, false);

        PriorityQueue<Edge> frontier = new PriorityQueue<>(numOfLocations, Comparator.comparingInt(Edge::hardness));

        /** graph[0] -> [ {Edge: first: 1, second: 2, hardness: x }, ... }*/
        List<Edge> origin = graph[0];
        frontier.addAll(origin);

        for (Edge e : origin)
            selected[e.first()] = true;

        int mstSize = 0;
        while (!frontier.isEmpty() || mstSize < numOfLocations - 1) {
            Edge edge = frontier.poll();
            if (!selected[edge.second()]) {
                selected[edge.second()] = true;

                if (mst[edge.first()] == null)
                    mst[edge.first()] = new ArrayList<>();
                mst[edge.first()].add(edge);

                if (mst[edge.second()] == null)
                    mst[edge.second()] = new ArrayList<>();
                mst[edge.second()].add(edge);

                for (Edge e : graph[edge.second()]) {
                    if (!selected[e.second()])
                        frontier.add(e);
                }
                mstSize++;
            }
        }
        return mst;
    }
}
