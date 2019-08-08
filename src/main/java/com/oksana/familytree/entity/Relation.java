package com.oksana.familytree.entity;

public enum Relation {
    PARENT_CHILD(1),
    MARRIAGE(2);

    private final int code;

    Relation(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
