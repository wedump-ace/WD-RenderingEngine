package com.badlogic.androidgames.framework.math;

public class OverlapTester {
    public static boolean overlapCircles(Circle c1, Circle c2) {
        float distance = c1.center.distSquared(c2.center);
        float radiusSum = c1.radius + c2.radius;
        return distance <= radiusSum * radiusSum;
    }

    public static boolean overlapRectangle(Rectangle r1, Rectangle r2) {
        if (r1.lowerLeft.x < r2.lowerLeft.x + r2.widht &&
                r1.lowerLeft.x + r1.widht > r2.lowerLeft.x &&
                r1.lowerLeft.y < r2.lowerLeft.y + r2.height &&
                r1.lowerLeft.y + r1.height > r2.lowerLeft.y) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
        float closestX = c.center.x;
        float closestY = c.center.y;

        if (c.center.x < r.lowerLeft.x) {
            closestX = r.lowerLeft.x;
        } else if (c.center.x > r.lowerLeft.x + r.widht) {
            closestX = r.lowerLeft.x + r.widht;
        }
        if (c.center.y < r.lowerLeft.y) {
            closestY = r.lowerLeft.y;
        } else if (c.center.y > r.lowerLeft.y + r.height) {
            closestY = r.lowerLeft.y + r.height;
        }

        return c.center.distSquared(closestX, closestY) < c.radius * c.radius;
    }

    public static boolean overlapSpheres(Sphere s1, Sphere s2) {
        float distance = s1.center.distSquared(s2.center);
        float radiusSum = s1.radius + s2.radius;
        return distance <= radiusSum * radiusSum;
    }

    public static boolean pointInCircle(Circle c, Vector2 p) {
        return c.center.distSquared(p) < c.radius * c.radius;
    }

    public static boolean pointInCircle(Circle c, float x, float y) {
        return c.center.distSquared(x, y) < c.radius * c.radius;
    }

    public static boolean pointInRectangle(Rectangle r, Vector2 p) {
        return r.lowerLeft.x <= p.x && r.lowerLeft.x + r.widht >= p.x &&
               r.lowerLeft.y <= p.y && r.lowerLeft.y + r.height >= p.y;
    }

    public static boolean pointInRectangle(Rectangle r, float x, float y) {
        return r.lowerLeft.x <= x && r.lowerLeft.x + r.widht >= x &&
               r.lowerLeft.y <= y && r.lowerLeft.y + r.height >= y;
    }

    public static boolean pointInSphere(Sphere s, Vector3 p) {
        return s.center.distSquared(p) < s.radius * s.radius;
    }

    public static boolean pointInSphere(Sphere s, float x, float y, float z) {
        return s.center.distSquared(x, y, z) < s.radius * s.radius;
    }
}
