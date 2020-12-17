package ru.vsu.cs.skofenko.logic;

import java.util.List;

class NodesBorderIndexes {
    private final List<Integer> listIndex1;
    private final List<Integer> listIndex2;

    public NodesBorderIndexes(List<Integer> listIndex1, List<Integer> listIndex2) {
        this.listIndex1 = listIndex1;
        this.listIndex2 = listIndex2;
    }

    public List<Integer> getListIndex1() {
        return listIndex1;
    }

    public List<Integer> getListIndex2() {
        return listIndex2;
    }

}
