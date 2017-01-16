package com.fw.graph;

import com.fw.Tools.LogUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author fengwei
 * Created on 2017/1/6/0006.
 */
public class BFSPathSearch {
    private int S;
    private boolean[] marked;
    private int[] edgeTo;

    public BFSPathSearch(Graph graph, int S) {
        this.S = S;
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        bfs(graph, S);
    }

    private void bfs(Graph graph, int S) {
        Queue<Integer> queue = new ArrayDeque<>();
        marked[S] = true;
        queue.add(S);
        while(!queue.isEmpty()) {
            int V = queue.poll();
            for (int W : graph.adj(V)) {
                if (!marked[W]) {
                    edgeTo[W] = V;
                    marked[W] = true;
                    queue.add(W);
                }
            }
        }
    }

    public boolean havePathTo(int V) {
        return marked[V];
    }

    /**
     * 能够保证无向图单点最短路径
     * @param V
     * @return
     */
    public List<Integer> pathTo(int V) {
        if (!havePathTo(V)) return null;
        Stack<Integer> stack = new Stack<>();
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

        BFSPathSearch bfsPathSearch = new BFSPathSearch(graph, 0);
        List<Integer> path = bfsPathSearch.pathTo(3);
        LogUtils.log.info(path);
    }

}
