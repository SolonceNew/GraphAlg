import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<Integer>[] verticesInfo;



    public Graph(int v) {
        this.verticesInfo = new LinkedList[v];

        for (int i = 0; i < v; i++) {

            verticesInfo[i] = new LinkedList<>();
        }
    }

    public void addEdge(int a, int b) {
        verticesInfo[a].add(b);
        verticesInfo[b].add(a);
    }

    public List<Integer> adjacent(int v) {
        return verticesInfo[v];
    }

    public int size() {
        return verticesInfo.length;
    }






}
