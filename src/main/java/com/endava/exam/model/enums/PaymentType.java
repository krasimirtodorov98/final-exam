package com.endava.exam.model.enums;

public enum PaymentType {
    CASH, CARD;

    public static boolean contains(String paymentType) {
        for (PaymentType pt : PaymentType.values()) {
            if (pt.toString().equals(paymentType)) {
                return true;
            }
        }
        return false;
    }
}
