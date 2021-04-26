package com.endava.exam.service;

import com.endava.exam.model.Purchase;
import com.endava.exam.model.enums.PaymentType;

import java.util.List;

public interface PurchaseService {
    Purchase makePurchase(String supermarketId, List<String> itemIds, PaymentType paymentType, Double cashAmount);

    List<Purchase> viewPurchases();

}
