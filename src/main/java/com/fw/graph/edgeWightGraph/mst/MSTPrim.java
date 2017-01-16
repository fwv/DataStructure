package com.fw.graph.edgeWightGraph.mst;

import com.fw.Tools.LogUtils;
import com.fw.graph.edgeWightGraph.Edge;
import com.fw.graph.edgeWightGraph.EdgeWeightGraph;
import com.fw.queue.PriorityQueue;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

/**
 * @Author fengwei
 * Created on 2017/1/16/0016.
 * 使用prim算法生成最小生成树 广度优先取最小取最小权重切割边
 */
public class MSTPrim {

    private EdgeWeightGraph graph;
    private boolean[] marked;
    private PriorityQueue<Edge> pq;
    public Queue<Edge> mst;

    public MSTPrim(EdgeWeightGraph graph) {
        this.graph = graph;
        marked = new boolean[graph.V()];
        pq = new PriorityQueue(50, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return -(int) (o1.getWeight()*100 - o2.getWeight()*100);
            }

        });
        mst = new ArrayDeque<>();
    }

    public void prim(int s) {
        visit(s);
        Edge edge = (Edge) pq.poll();
        for (; null != edge; edge = (Edge)pq.poll()) {
            int v = edge.eitherPoint();
            int w = edge.otherPoint(v);
            if (marked[v] && marked[w])continue;
            int next = !marked[v] ? v : w;
            visit(next);
            mst.add(edge);
        }
    }

    public void visit(int E) {
        marked[E] = true;
        for (Edge e : graph.adj(E)) {
            int w = e.otherPoint(E);
            if (!marked[w]) {
                pq.add(e);
            }
        }
    }

    public Queue edges() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightGraph graph = new EdgeWeightGraph(8);
        graph.addEdge( 4, 5, 0.35f);
        graph.addEdge( 4, 7, 0.37f);
        graph.addEdge( 5, 7, 0.28f);
        graph.addEdge( 0, 7, 0.16f);
        graph.addEdge( 1, 5, 0.32f);
        graph.addEdge( 0, 4, 0.38f);
        graph.addEdge( 2, 3, 0.17f);
        graph.addEdge( 1, 7, 0.19f);
        graph.addEdge( 0, 2, 0.26f);
        graph.addEdge( 1, 2, 0.36f);
        graph.addEdge( 1, 3, 0.29f);
        graph.addEdge( 2, 7, 0.34f);
        graph.addEdge( 6, 2, 0.40f);
        graph.addEdge( 3, 6, 0.52f);
        graph.addEdge( 6, 0, 0.58f);
        graph.addEdge( 6, 4, 0.93f);
        MSTPrim mstPrim = new MSTPrim(graph);
        mstPrim.prim(0);
        //mstPrim.mst.add(new Edge(1, 2, 0.23F));
        LogUtils.log.info(mstPrim.edges());
    }

}
