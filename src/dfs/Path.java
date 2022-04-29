package dfs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Path {

    private Graph G;
    private int source;
    private int target;

    private boolean[] visited;
    private int[] pre;

    public Path(Graph G, int source, int target) { // source是指定的单源, target是目标顶点

        G.validateVertex(source); // 对传进来的source进行一下合法性判断
        G.validateVertex(target); // 对传进来的target进行一下合法性判断

        this.G = G;
        this.source = source;
        this.target = target;

        visited = new boolean[G.getV()];
        pre = new int[G.getV()];

        dfs(source, source); // source其实没有parent，所以这里可以指定为它自己
    }

    public boolean isConnectedTo() {
        return visited[target];
    }

    public Iterable<Integer> path() {

        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnectedTo())
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

    private boolean dfs(int v, int parent) {

        visited[v] = true;
        pre[v] = parent;

        if (v == target)
            return true;

        for(int w: G.adj(v)) {
            if(!visited[w])
                if (dfs(w, v))
                    return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        Path path = new Path(g, 0, 6);
        System.out.println("0 -> 6: " + path.path());

        Path path2 = new Path(g, 0, 1);
        System.out.println("0 -> 1: " + path2.path());

        Path path3 = new Path(g, 0, 5);
        System.out.println("0 -> 5: " + path3.path());
    }
}
