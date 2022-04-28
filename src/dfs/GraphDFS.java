package dfs;

import java.util.ArrayList;

public class GraphDFS {

    private Graph G;
    private boolean[] visited;
    private ArrayList<Integer> res = new ArrayList<>();

    public GraphDFS(Graph G) {
        this.G = G;
        visited = new boolean[G.getV()];
//        这里有bug，如果不是连通图的话，单独的顶点不会被访问到
//        dfs(0);
        for (int v = 0; v < G.getV(); v++) {
            if(!visited[v])
                dfs(v);
        }
    }

    private void dfs(int v) {

        visited[v] = true;
        res.add(v);

        for(int w: G.adj(v)) {
            if(!visited[w])
                dfs(w);
        }
    }

    public Iterable<Integer> res(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.res());;
    }
}
