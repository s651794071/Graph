package dfs;

import java.util.ArrayList;

// Connected Component
public class CC {

    private Graph G;
    private int[] visited;
    private int cccount = 0; // connected components count

    public CC(Graph G) {
        this.G = G;
        visited = new int[G.getV()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        for (int v = 0; v < G.getV(); v++) {
            if(visited[v] == -1){
                dfs(v, cccount);
                cccount++;
            }
        }
    }

    private void dfs(int v, int ccid) {

        visited[v] = ccid;
        for(int w: G.adj(v)) {
            if(visited[w] == -1)
                dfs(w, ccid);
        }
    }

    public int count(){
        return cccount;
    }

    public boolean isConnected(int v, int w) {
        return visited[v] == visited[w];
    }

    // 返回图中所有的连通分量
    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++)
            res[i] = new ArrayList<>();

        for (int v = 0; v < G.getV(); v++)
            res[visited[v]].add(v); // visited数组中v的范围在[0, ccount-1]

        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
        System.out.println(cc.isConnected(0,5));
        System.out.println("----------");
        ArrayList<Integer>[] comp = cc.components();
        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + ": ");
            for (int w: comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
