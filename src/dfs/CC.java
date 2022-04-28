package dfs;

// Connected Component
public class CC {

    private Graph G;
    private boolean[] visited;
    private int cccount = 0; // connected components count

    public CC(Graph G) {
        this.G = G;
        visited = new boolean[G.getV()];
        for (int v = 0; v < G.getV(); v++) {
            if(!visited[v]){
                dfs(v);
                cccount++;
            }
        }
    }

    private void dfs(int v) {

        visited[v] = true;
        for(int w: G.adj(v)) {
            if(!visited[w])
                dfs(w);
        }
    }

    public int count(){
        return cccount;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
    }
}
