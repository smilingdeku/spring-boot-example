package org.example.common.util;

import org.example.common.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    public static <T extends TreeNode<T>> List<T> build(List<T> treeNodes, String root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (null == treeNode.getParentId() || treeNode.getParentId().equals(root)) {
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (null != it.getParentId() && it.getParentId().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }

}
