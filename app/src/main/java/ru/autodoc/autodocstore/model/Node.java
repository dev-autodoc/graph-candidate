package ru.autodoc.autodocstore.model;

import android.graphics.Point;

/**
 * Created by kochetkov.a on 08.12.2015.
 */
public class Node {
    public Point point;
    public String text;

    public Node(String text){
        this.text = text;
    }

    public Node(Point point, String text){
        this.point = point;
        this.text = text;
    }

    public float getX() {
        return point.x;
    }

    public float getY(){
        return point.y;
    }

    public void set(int x, int y) {
        point = new Point();
        point.set(x, y);
    }

    public boolean equals(int x, int y) {
        return point.equals(x, y);
    }

    @Override
    public String toString() {
        return text;
    }
}
