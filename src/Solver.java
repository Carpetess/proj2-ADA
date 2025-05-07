import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private List<Edge> edges;
    private int numOfLocations;

    public Solver (List<Edge> edges, int numOfLocations) {
        this.edges = sortEdges(edges);
        this.numOfLocations = numOfLocations;
    }

    public int[][] solve(int[][] operations) {
        return null;
    }

    private List<Edge> sortEdges(List<Edge> edges) {
        Comparator<Edge> byHardness = Comparator.comparing(Edge::hardness);
        edges.sort(byHardness);
        return edges;
    }
}
