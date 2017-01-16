package com.fw.graph.edgeWightGraph;

import java.util.Comparator;

/**
 * @Author fengwei
 * Created on 2017/1/16/0016.
 */
public class Edge implements Comparator<Edge> {

    private int v;
    private int w;
    private float weight;

    public Edge(){}

    public Edge(int v, int w, float weight) {
        this.w = w;
        this.v = v;
        this.weight = weight;
    }

    public float getWeight() {return weight;}

    public int eitherPoint() {
        return v;
    }

    public int otherPoint(int occupy) {
        if (occupy == v)return w;
        if (occupy == w)return v;
        return -1;

    }

    @Override
    public int compare(Edge o1, Edge o2) {
        return -(int) (o1.getWeight()*100 - o2.getWeight()*100);
    }

    @Override
    public String toString() {
        return "( " + this.v +"-" + this.w +" : " + this.weight +" )";
    }
}
