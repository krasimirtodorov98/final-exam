package com.endava.exam.model.enums;

public enum ItemType {
    FOOD, DRINK, TECHNOLOGY, HOUSEHOLD;

    public static boolean contains(String type) {
        for (ItemType it : ItemType.values()) {
            if (it.toString().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
