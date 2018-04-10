package com.badlogic.androidgames.framework.math;

public class Vector2 {
    public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
    public static float TO_DEGREES = (1 / (float) Math.PI) * 180;

    public float x, y;

    public Vector2() {

    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2 cpy() {
        return new Vector2(x, y);
    }

    public Vector2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2 set(Vector2 other) {
        x = other.x;
        y = other.y;
        return this;
    }

    public Vector2 add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 add(Vector2 other) {
        x += other.x;
        y += other.y;
        return this;
    }

    public Vector2 sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 sub(Vector2 other) {
        x -= other.x;
        y -= other.y;
        return this;
    }

    public Vector2 mul(float scalar) {
        x *= scalar;
        y *= scalar;
        return this;
    }

    public float len() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2 nor() {
        float len = len();
        if (len != 0) {
            x /= len;
            y /= len;
        }
        return this;
    }

    public float angle() {
        float angle = (float) Math.atan2(y, x) * TO_DEGREES;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public Vector2 rotate(float angle) {
        float rad = angle * TO_RADIANS;
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);

        float newX = x * cos - y * sin;
        float newY = x * sin + y * cos;

        set(newX, newY);
        return this;
    }

    public float dist(float x, float y) {
        float distX = this.x - x;
        float distY = this.y - y;
        return (float) Math.sqrt(distX * distX + distY * distY);
    }

    public float dist(Vector2 other) {
        float distX = x - other.x;
        float distY = y - other.y;
        return (float) Math.sqrt(distX * distX + distY * distY);
    }

    public float distSquared(float x, float y) {
        float distX = this.x - x;
        float distY = this.y - y;
        return distX * distX + distY * distY;
    }

    public float distSquared(Vector2 other) {
        float distX = x - other.x;
        float distY = y - other.y;
        return distX * distX + distY * distY;
    }
}
