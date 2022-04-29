package dfs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Graph {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj; // TreeSet数组[TreeSet1, TreeSet2,...]

    public Graph(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<Integer>();
            }

            E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("E must be non-negative!");
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a); // 判断顶点a是否有效
                int b = scanner.nextInt();
                validateVertex(b); // 判断顶点b是否有效

                if (a == b) throw new IllegalArgumentException("我们只考虑简单图，不考虑自环边！");
                if (adj[a].contains(b)) throw new IllegalArgumentException("我们只考虑简单图，不考虑平行边！");

                adj[a].add(b);
                adj[b].add(a);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试顶点v是否是有效的，假如 V = 5 ，但文件中某个 v 大于等于了5 （顶点序号从0开始） 就是不合法的
     * @param v 某个顶点的序号
     */
    public void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is invalid");
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    // 查一下是否存在顶点v到顶点w的边，即查看v和w是否相邻
    public boolean hasEdge(int v, int w) {
        // 记得进行合法性判断！
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    // 这里我们改成了Iterable接口是因为不需要让用户知道返回的是TreeSet还是LinkedList还是什么
    public Iterable<Integer> adj(int v) {
        validateVertex(v); // 不要忘记进行传入的顶点v的合法性判断
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v); // 这里我们要对顶点v的合法性进行判断，因为我们不像之前一样直接调用adj(v)了，现在用的是adj[v]
        return adj[v].size(); // 用adj[v]的原因是adj(v)里返回的是Iterable接口，没有size()方法
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E= %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d: ", v));
            for (int w: adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph adjSet = new Graph("g.txt");
        System.out.println(adjSet);
    }
}
