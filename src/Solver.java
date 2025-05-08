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


    private Edge[] mstPrim( List<Edge> graph ) {
        Edge[] mst = new Edge[numOfLocations - 1];
        int mstSize = 0;
        boolean[] selected = new boolean[numOfLocations];
        int[] cost = new int[numOfLocations];
        Edge[] via = new Edge[numOfLocations];
        PriorityQueue<Edge> connected= new PriorityQueue<>(numOfLocations,Comparator.comparingInt(Edge::hardness));

        for(int i= 0 ; i < numOfLocations ; i++) {
            selected[i] = false;
            cost[i] = -1;
        }
        Edge origin = graph.getFirst();
        cost[origin.first()] = 0;
        connected.add(origin);

        do {
            Edge edge = connected.poll();
            selected[edge.first()] = true;
            if ( node != origin )
                mst[ mstSize++ ] = via[node];
            exploreNode(graph, node, selected, cost, via, connected);
        }
        while ( !connected.isEmpty() );
        return mst;
    }

    private void exploreNode( UndiGraph<L> graph, Node source,
                      boolean[] selected, L[] cost, Edge<L>[] via,
                      AdaptMinPriQueue<L, Node> connected ) {
        for every Edge<L> e in graph.incidentEdges(source) {
            Node node = e.oppositeNode(source);
            if ( !selected[node] && e.label() < cost[node] ) {
                boolean nodeIsInQueue = cost[node] < +∞;
                cost[node] = e.label();
                via[node] = e;
                if ( nodeIsInQueue )
                    connected.decreaseKey(node, cost[node]);
                else
                    connected.insert(cost[node], node);
            }
        }
    }


}
