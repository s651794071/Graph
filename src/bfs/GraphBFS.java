package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> res = new ArrayList<>();

    public GraphBFS(Graph G){
        this.G = G;
        visited = new boolean[G.getV()];

        for (int v = 0; v < G.getV(); v++) {
            if (!visited[v])
                bfs(v);
        }
    }

    public void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            int v = q.remove();
            res.add(v);

            for(int w: G.adj(v)) {
                if (!visited[w]) {
                    q.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> res(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println("BFS: " + graphBFS.res());
    }


}
