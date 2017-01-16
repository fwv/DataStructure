package com.fw.graph;

/**
 * @Author fengwei
 * Created on 2017/1/6/0006.
 */
public class Digraph extends Graph{

    public Digraph(int V) {
        super(V);
    }

    @Override
    public void addEdge(int V, int W) {
        adj(V).add(W);
        this.E++;
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for(int i = 0; i < V(); i++) {
            for (int W : adj(i)) {
                R.addEdge(W, i);
            }
        }
        return R;
    }
}
