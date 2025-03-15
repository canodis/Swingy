package org.model.Items;

public abstract class AItem {
    protected int id;
    protected String Name;
    protected int Level;

    public int GetId() { return id; }

    public void SetId(int id) { this.id = id; }

    public String GetName() { return Name; }

    public void SetName(String name) { Name = name; }

    public int GetLevel() { return Level; }

    public void SetLevel(int level) { Level = level; }
}

