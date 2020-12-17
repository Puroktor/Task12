package ru.vsu.cs.skofenko.logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ParserAndPainter {

    public static BufferedImage parseAndPaint(String text) {
        List<String> tokenList = stringToTokenList(text);
        TreeNode root = buildTree(tokenList.listIterator());

        MyBufferedImage img = new MyBufferedImage();
        img.drawStringAndResize(root.getName(), 0, 16);

        paintTree(img, root, 32, "");
        return img.getBufferedImage();
    }

    private static TreeNode buildTree(ListIterator<String> iterator) {
        String str = iterator.next();
        if (str.equals("(")) {
            TreeNode node = new TreeNode();
            node.setName(iterator.next());
            List<TreeNode> children = new ArrayList<>();
            while (iterator.hasNext()) {
                String s = iterator.next();
                if (s.equals("(")) {
                    iterator.previous();
                    children.add(buildTree(iterator));
                } else if (s.equals(")")) {
                    break;
                } else if (!s.equals(",")) {
                    TreeNode child = new TreeNode();
                    child.setName(s);
                    children.add(child);
                }
            }
            node.setChildren(children);
            return node;
        }
        return null;
    }

    private static List<String> stringToTokenList(String text) {
        StringBuilder sb = new StringBuilder(text);
        List<String> tokenList = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < sb.length(); i++) {
            char ch = sb.charAt(i);
            if (ch == ')' || ch == '(' || ch == ',') {
                String temp = sb.substring(startIndex, i).trim();
                if (temp.length() != 0) {
                    tokenList.add(temp);
                }
                tokenList.add(sb.substring(i, i + 1));
                startIndex = i + 1;
            }
        }
        return tokenList;
    }

    private static int paintTree(MyBufferedImage img, TreeNode root, int y, String prefix) {
        List<TreeNode> list = root.getChildren();
        ListIterator<TreeNode> iterator = list.listIterator();
        while (iterator.hasNext()) {
            TreeNode current = iterator.next();
            img.drawStringAndResize(prefix + "+-" + current.getName(), 0, y);
            y += 16;
            if (current.getChildren().size() != 0) {
                StringBuilder sb = new StringBuilder(prefix);
                if (iterator.hasNext()) {
                    sb.append("| ");
                } else {
                    sb.append("  ");
                }
                y = paintTree(img, current, y, sb.toString());
            }
        }
        return y;
    }
}