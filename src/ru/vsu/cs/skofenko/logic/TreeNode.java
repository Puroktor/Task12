package ru.vsu.cs.skofenko.logic;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private List<TreeNode> children = new ArrayList<>();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
