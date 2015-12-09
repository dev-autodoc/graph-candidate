package ru.autodoc.autodocstore.model;

import android.graphics.Point;
import android.util.Pair;

/**
 * Created by kochetkov.a on 08.12.2015.
 */
public class Way {
    public Pair<Node, Node> way;
    public int weight = 0;

    public Way(Pair<Node, Node> way, int weight) {
        this.way = way;
        this.weight = weight;
    }

    public float getStartX() {
        return way.first.getX();
    }

    public float getStartY() {
        return way.first.getY();
    }

    public float getEndX() {
        return way.second.getX();
    }

    public float getEndY() {
        return way.second.getY();
    }

    public float getTextX() {
        return (getStartX() + getEndX()) / 2;
    }

    public float getTextY(){
        return (getStartY() + getEndY()) / 2;
    }

    public boolean contains(Point point) {
        return way.first.equals(point.x, point.y) || way.second.equals(point.x, point.y);
    }

    public boolean contains(Node node) {
        return way.first.equals(node) || way.second.equals(node);
    }

    @Override
    public String toString() {
        return String.format("%1$s-%2$s", way.first.text, way.second.text);
    }
}
