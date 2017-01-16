package com.fw.graph.edgeWightGraph;

import java.util.ArrayList;

/**
 * @Author fengwei
 * Created on 2017/1/16/0016.
 * 加权无向图
 */
public class EdgeWeightGraph {

    // count of vertex
    protected final int V;
    // count of edge
    protected int E;
    // adjacent diagram
    protected ArrayList<Edge>[] adj;

    public EdgeWeightGraph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList();
        }
    }

    public int V() {return V;}

    public int E() {return E;}

    public ArrayList<Edge> adj(int V) {
        return adj[V];
    }

    public void addEdge(int V, int W, float weight) {
        Edge edge = new Edge(V, W, weight);
        adj[V].add(edge);
        adj[W].add(edge);
        E++;
    }

    public void addEdge(Edge edge) {
        int V = edge.eitherPoint();
        int W = edge.otherPoint(V);
        adj[V].add(edge);
        adj[W].add(edge);
        E++;
    }


}
