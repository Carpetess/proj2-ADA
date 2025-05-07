import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int locations = Integer.parseInt(firstLine[0]);
        int roads = Integer.parseInt(firstLine[1]);

        Edge[] edges = new Edge[roads];

        for (int i = 0; i < roads; i++) {
            String[] line = br.readLine().split(" ");
            int first = Integer.parseInt(line[0]);
            int second = Integer.parseInt(line[1]);
            int hardness = Integer.parseInt(line[2]);
            edges[i] = new Edge(first, second, hardness);
        }

        int numberOfOperations = Integer.parseInt(br.readLine());

        int[][] operations = new int[numberOfOperations][2];
        for (int i = 0; i < numberOfOperations; i++) {
            String[] line = br.readLine().split(" ");
            operations [i][0] = Integer.parseInt(line[0]);
            operations[i][1] = Integer.parseInt(line[1]);
        }

        }
    }