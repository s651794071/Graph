package dfs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SingleSourcePath {

    private Graph G;
    private int source;

    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(Graph G, int source) { // source是指定的单源

        G.validateVertex(source); // 对传进来的source进行一下合法性判断

        this.G = G;
        this.source = source;

        visited = new boolean[G.getV()];
        pre = new int[G.getV()];

        dfs(source, source); // source其实没有parent，所以这里可以指定为它自己
    }

    public boolean isConnectedTo(int target) {
        G.validateVertex(target);
        return visited[target];
    }

    public Iterable<Integer> path(int target) {

        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo(target))
            return res;

        int curr = target;
        while (curr != source) {
            res.add(curr);
            curr = pre[curr];
        }
        res.add(source);

        Collections.reverse(res);
        return res;
    }

    private void dfs(int v, int parent) {

        visited[v] = true;
        pre[v] = parent;
        for(int w: G.adj(v)) {
            if(!visited[w])
                dfs(w, v);
        }
    }


    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
//        GraphDFS graphDFS = new GraphDFS(g);
//        System.out.println(graphDFS.res());;
        SingleSourcePath ssPath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6: " + ssPath.path(6));
        System.out.println("0 -> 5: " + ssPath.path(5));
    }
}
