/**
 * @author André Filipe 65371, Guilherme Fialho 65581
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int locations = Integer.parseInt(firstLine[0]);
        int roads = Integer.parseInt(firstLine[1]);

        List<Edge>[] edges = new List[locations];

        for (int i = 0; i < roads; i++) {
            String[] line = br.readLine().split(" ");
            int first = Integer.parseInt(line[0]);
            int second = Integer.parseInt(line[1]);
            int hardness = Integer.parseInt(line[2]);
            if (edges[first] == null)
                edges[first] = new LinkedList<>();
            if (edges[second] == null)
                edges[second] = new LinkedList<>();
            edges[first].add(new Edge(first, second, hardness));
            edges[second].add(new Edge(second, first, hardness));
        }

        Solver solver = new Solver(edges, locations);

        int numberOfOperations = Integer.parseInt(br.readLine());
        int[][] operations = new int[numberOfOperations][2];
        for (int i = 0; i < numberOfOperations; i++) {
            String[] line = br.readLine().split(" ");
            operations [i][0] = Integer.parseInt(line[0]);
            operations[i][1] = Integer.parseInt(line[1]);
        }

        int[] results = solver.solve(operations);

        for (int result : results) {
            System.out.println(result);
        }


    }
}