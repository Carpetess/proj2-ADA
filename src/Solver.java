import UnionFind.*;

import java.util.*;

public class Solver {
    private List<Edge>[] allEdges;
    private int numOfLocations;

    public Solver (List<Edge>[] allEdges, int numOfLocations) {
        this.allEdges = allEdges;
        this.numOfLocations = numOfLocations;
    }

    public int[][] solve(int[][] operations) {
//        Queue<Edge> minQueue = new LinkedList<>(edges);
//        Edge[] mst = mstKruskal(minQueue);
        


        return null;
    }
/**
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
**/

    private Edge[] mstPrim( List<Edge>[] graph ) {
        List<Edge>[] mst = new List[numOfLocations];
        boolean[] selected = new boolean[numOfLocations];
        Arrays.fill(selected, false);

        PriorityQueue<Edge> frontier = new PriorityQueue<>(numOfLocations,Comparator.comparingInt(Edge::hardness));

        /** graph[0] -> [ {Edge: first: 1, second: 2, hardness: x }, ... }*/
        List<Edge> origin = graph[0];
        frontier.addAll(origin);

        for (Edge e : origin)
            selected[e.first()] = true;

        int mstSize = 0;
        while (!frontier.isEmpty() || mstSize < numOfLocations - 1) {
            Edge edge = frontier.poll();
            if(!selected[edge.second()]) {
            selected[edge.second()] = true;

            if(mst[edge.first()] == null)
                mst[edge.first()] = new ArrayList<>();
            mst[edge.first()].add(edge);

            if(mst[edge.second()] == null)
                mst[edge.second()] = new ArrayList<>();
            mst[edge.second()].add(edge);

            for(Edge e : graph[edge.second()]) {
                if(!selected[e.second()])
                    frontier.add(e);
            }
                mstSize++;
                }
        }
        return null;
        }
}
