package org.example.common.util;

import org.example.common.domain.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 树工具类
 */
public class TreeUtil {

    /**
     * 构建树
     *
     * @param treeNodes 树节点
     * @param root 根标志
     * @return 树
     */
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
