import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class AdjList {
    private int V;
    private int E;
    private LinkedList<Integer>[] adj; // 链表数组[LinkedList1, LinkedList2,...]

    public AdjList(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("V must be non-negative!");
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<Integer>();
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
    private void validateVertex(int v) {
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

    /**
     * 返回顶点v的所有的neighbor，即邻边的另一个顶点
     * @param v 要查的顶点v
     * @return 顶点v所有neighbor的list
     */
    public LinkedList<Integer> adj(int v) {
        validateVertex(v); // 不要忘记进行传入的顶点v的合法性判断
        return adj[v];
    }

    /**
     * 计算顶点v的degree，非常简单直接调用上面方法返回的list的size()
     * @param v 要计算的顶点v
     * @return 顶点v的degree
     */
    public int degree(int v) {
        return adj(v).size();
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
        AdjList adjList = new AdjList("g.txt");
        System.out.println(adjList);
    }
}
