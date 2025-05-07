public class Edge {
    private int first;
    private int second;
    private int hardness;

    public Edge(int first, int second, int hardness) {
        this.first = first;
        this.second = second;
        this.hardness = hardness;
    }

    public int getFirst() {return first;}
    public int getSecond() {return second;}
    public int getHardness() {return hardness;}
}
