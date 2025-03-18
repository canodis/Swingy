package org.data;

import java.io.Serializable;
import java.util.Objects;

public class Vector2 implements Serializable {
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2 add(Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    public void sub(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void mul(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void div(int scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2 vector2 = (Vector2) o;
        return x == vector2.x && y == vector2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static final Vector2 Up = new Vector2(0, -1);
    public static final Vector2 Down = new Vector2(0, 1);
    public static final Vector2 Left = new Vector2(-1, 0);
    public static final Vector2 Right = new Vector2(1, 0);
}
