package com.fw.graph;

import com.fw.Tools.LogUtils;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author fengwei
 * Created on 2017/1/6/0006.
 */
public class DFSPathSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private int S;

    public DFSPathSearch(Graph graph, int S) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.S = S;
        dfs(graph, S);
    }

    public void dfs(Graph graph, int V) {
        marked[V] = true;
        for (Integer W : graph.adj(V)) {
            if (!marked[W]) {
                edgeTo[W] = V;
                dfs(graph, W);
            }
        }
    }

    public boolean havePathTo(int V) {
        return marked[V];
    }

    public List<Integer> pathTo(int V) {
        if (!havePathTo(V))return null;
        Stack<Integer> stack = new Stack();
        for (int X = V; X != S; X = edgeTo[X]) {
            stack.push(X);
        }
        stack.push(S);
        List<Integer> path = new ArrayList<>();
        path.addAll(stack);
        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);

        DFSPathSearch dfsPathSearch = new DFSPathSearch(graph, 0);
        List<Integer> path = dfsPathSearch.pathTo(3);
        LogUtils.log.info(path);
    }
}
