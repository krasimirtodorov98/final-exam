package com.endava.exam.service.impl;

import com.endava.exam.model.Item;
import com.endava.exam.model.Purchase;
import com.endava.exam.model.enums.PaymentType;
import com.endava.exam.repository.ItemRepository;
import com.endava.exam.repository.PurchaseRepository;
import com.endava.exam.repository.SupermarketRepository;
import com.endava.exam.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final SupermarketRepository supermarketRepository;
    private final ItemRepository itemRepository;

    @Override
    public Purchase makePurchase(String supermarketId, List<String> itemIds, PaymentType paymentType, Double cashAmount) {
        if (!PaymentType.contains(paymentType.toString())) {
            throw new IllegalArgumentException("Invalid type of payment.");
        }
        if (!supermarketRepository.existsById(supermarketId)) {
            throw new IllegalArgumentException("Supermarket with the provided id not found");
        }
        Purchase purchase = new Purchase();
        List<Item> newItems = new ArrayList<>();
        for (String itemId : itemIds) {
            newItems.add(itemRepository.findById(itemId)
                    .orElseThrow(() -> new EntityNotFoundException("Item with id " + itemId + " unavailable.")));
        }
        purchase.setItemsIds(newItems);
        Double totalBill = 0.0;
        for (Item i : newItems) {
            totalBill += i.getPrice();
        }
        purchase.setTotalPrice(totalBill);
        purchase.setPurchasedOn(LocalDateTime.now());
        purchase.setPaymentType(paymentType);

        if (!paymentType.equals(PaymentType.CARD)) {
            purchase.setCashAmount(cashAmount);
            purchase.setReturnedChange(purchase.getCashAmount() - purchase.getTotalPrice());
        }
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> viewPurchases() {
        return purchaseRepository.findAll();
    }

}
