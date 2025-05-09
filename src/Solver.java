import UnionFind.*;

import java.util.*;

public class Solver {
    private List<Edge> edges;
    private int numOfLocations;

    public Solver (List<Edge> edges, int numOfLocations) {
        this.edges = edges;
        this.numOfLocations = numOfLocations;
    }
    @SuppressWarnings("unchecked")
    public int[] solve(int[][] operations) {
        Queue<Edge> minQueue = new PriorityQueue<>(Comparator.comparingInt(Edge::hardness));
        minQueue.addAll(edges);
        List<Edge>[] mst = mstKruskal(minQueue);

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
    private List<Edge>[] mstKruskal(Queue<Edge> minQueue) {
        UnionFind nodesPartition = new UnionFindInArray(numOfLocations);
        int mstFinalSize = numOfLocations - 1;

        List<Edge>[] mst = new List[numOfLocations];

        int mstSize = 0;

        while (!minQueue.isEmpty()) {
            Edge edge = minQueue.poll();
            int rep1 = nodesPartition.find(edge.first());
            int rep2 = nodesPartition.find(edge.second());
            if (rep1 != rep2) {
                if(mst[edge.first()] == null)
                    mst[edge.first()] = new LinkedList<>();
                if(mst[edge.second()] == null)
                    mst[edge.second()] = new LinkedList<>();
                mst[edge.first()].add(edge);
                mst[edge.second()].add(edge);
                nodesPartition.union(rep1, rep2);
            }
        }
        return mst;
    }

}
