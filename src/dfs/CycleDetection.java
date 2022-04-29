package dfs;

import java.util.ArrayList;

public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.getV()];
        for (int v = 0; v < G.getV(); v++) {
            if(!visited[v])
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
        }
    }

    private boolean dfs(int v, int parent) {

        visited[v] = true;
        for(int w: G.adj(v)) {
            if(!visited[w]) {
                if (dfs(w, v))
                    return true;
            }
            else if (w != parent)
                return true;
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }


    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
