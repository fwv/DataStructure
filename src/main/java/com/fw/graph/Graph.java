package com.fw.graph;

import java.util.ArrayList;

/**
 * @Author fengwei
 * Created on 2017/1/6/0006.
 */
public class Graph {
    // count of vertex
    private final int V;
    // count of edge
    private int E;
    // adjacent diagram
    private ArrayList<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public int V() {return V;}

    public int E() {return E;}

    public ArrayList<Integer> adj(int V) {
        return adj[V];
    }

    public void addEdge(int V, int W) {
        adj[V].add(W);
        adj[W].add(V);
        E++;
    }

}
