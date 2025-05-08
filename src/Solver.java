import UnionFind.*;

import java.util.*;

public class Solver {
    private List<Edge> edges;
    private int numOfLocations;

    public Solver (List<Edge> edges, int numOfLocations) {
        this.edges = sortEdges(edges);
        this.numOfLocations = numOfLocations;
    }

    public int[][] solve(int[][] operations) {
        Queue<Edge> minQueue = new LinkedList<>(edges);
        Edge[] mst = mstKruskal(minQueue);
        


        return null;
    }

    private Edge[] mstKruskal(Queue<Edge> minQueue) {
        UnionFind nodesPartition = new UnionFindInArray(numOfLocations);
        int mstFinalSize = numOfLocations - 1;

        Edge[] mst = new Edge[numOfLocations - 1];

        int mstSize = 0;

        while (mstSize < mstFinalSize) {
            Edge edge = minQueue.poll();
            int rep1 = nodesPartition.find(edge.first());
            int rep2 = nodesPartition.find(edge.second());
            if (rep1 != rep2) {
                mst[mstSize++] = edge;
                nodesPartition.union(rep1, rep2);
            }
        }
        return mst;
    }

    private List<Edge> sortEdges(List<Edge> edges) {
        Comparator<Edge> byHardness = Comparator.comparingInt(Edge::hardness);
        edges.sort(byHardness);
        return edges;
    }
}
