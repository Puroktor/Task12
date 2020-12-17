package ru.vsu.cs.skofenko.logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ParserAndPainter {
    private static int y;

    public static BufferedImage parseAndPaint(String text) {
        text = text.trim();
        if (text.length() != 0) {
            text = text.replace(",", "");
            TreeNode root = buildTree(text);

            MyBufferedImage img = new MyBufferedImage();
            img.drawStringAndResize(root.getName(), 0, 16);
            y = 32;

            List<Boolean> hasNext = new ArrayList<>();
            hasNext.add(false);
            paintTree(img, root, 0, hasNext);
            return img.getBufferedImage();
        }
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }

    private static void paintTree(MyBufferedImage img, TreeNode root, int x, List<Boolean> hasNextNode) {
        int width = img.getCharWidth();
        List<TreeNode> list = root.getChildren();
        for (int i = 0; i < list.size(); i++) {
            img.drawStringAndResize("+-" + list.get(i).getName(), x, y);
            for (int j = hasNextNode.size() - 1; j >= 0; j--) {
                if (hasNextNode.get(j)) {
                    img.drawStringAndResize("|", x - width * 2 * (hasNextNode.size() - j), y);
                }
            }
            y += 16;
            if (list.get(i).getChildren().size() != 0) {
                List<Boolean> hasNext = new ArrayList<>(hasNextNode);
                hasNext.add(i != list.size() - 1);
                paintTree(img, list.get(i), x + width * 2, hasNext);
            }
        }
    }

    private static TreeNode buildTree(String str) {
        TreeNode node = new TreeNode();
        str = str.substring(1, str.length() - 1);
        str = str.trim();
        int index = str.indexOf(" ");
        if (index == -1) {
            node.setName(str);
            return node;
        }
        node.setName(str.substring(0, index));

        String children = str.substring(index + 1);
        List<TreeNode> childrenList = new ArrayList<>();

        NodesBorderIndexes indexes = findBordersOfCurrentNodes(children);
        List<Integer> list1 = indexes.getListIndex1();
        List<Integer> list2 = indexes.getListIndex2();

        if (list1.size() == 0) {
            childrenList.addAll(noChildrenStringToList(children));
        } else {
            if (list1.get(0) != 0) {
                childrenList.addAll(noChildrenStringToList(children.substring(0, list1.get(0))));
            }
            for (int i = 0; i < list1.size() - 1; i++) {
                childrenList.add(buildTree(children.substring(list1.get(i), list2.get(i) + 1)));
                childrenList.addAll(noChildrenStringToList(children.substring(list2.get(i) + 1, list1.get(i + 1))));
            }
            childrenList.add(buildTree(children.substring(list1.get(list1.size() - 1), list2.get(list1.size() - 1) + 1)));
            if (list2.get(list2.size() - 1) != children.length() - 1) {
                childrenList.addAll(noChildrenStringToList(children.substring(list2.get(list2.size() - 1) + 1)));
            }
        }
        node.setChildren(childrenList);
        return node;
    }

    private static NodesBorderIndexes findBordersOfCurrentNodes(String nodes) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        int opened = 0;
        for (int i = 0; i < nodes.length(); i++) {
            if (nodes.charAt(i) == '(') {
                if (opened == 0) {
                    list1.add(i);
                }
                opened++;
            } else if (nodes.charAt(i) == ')') {
                if (opened == 1) {
                    list2.add(i);
                }
                opened--;
            }
        }
        return new NodesBorderIndexes(list1, list2);
    }

    private static List<TreeNode> noChildrenStringToList(String str) {
        List<TreeNode> list = new ArrayList<>();
        str = str.trim();
        String[] childrenArr = str.split(" ");
        for (String tmp : childrenArr) {
            TreeNode child = new TreeNode();
            child.setName(tmp);
            list.add(child);
        }
        return list;
    }
}