package org.example.common.domain;

import java.util.ArrayList;
import java.util.List;


public class TreeNode<T> {

    private String id;
    private String parentId;
    private List<TreeNode<T>> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public void add(TreeNode<T> node) {
        children.add(node);
    }
}
