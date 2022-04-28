package dfs;

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

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
        System.out.println(cc.isConnected(0,5));
    }
}
