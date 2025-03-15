package org.model;

import org.data.Vector2;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    public GameObject() {
        position = new Vector2();
        Icon = ".";
        isVisible = false;
    }
    public Vector2 position;
    public String Icon;
    public boolean isVisible;
}
