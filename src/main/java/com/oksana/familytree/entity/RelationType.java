package com.oksana.familytree.entity;

public enum RelationType {
    PARENT_CHILD(0),
    MARRIAGE(1);

    private final int code;

    RelationType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
