package com.badlogic.androidgames.framework.math;

public class Rectangle {
    public final Vector2 lowerLeft;
    public float widht, height;

    public Rectangle(float x, float y, float widht, float height) {
        this.lowerLeft = new Vector2(x, y);
        this.widht = widht;
        this.height = height;
    }
}
