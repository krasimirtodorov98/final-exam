package com.endava.exam.dto;

import com.endava.exam.model.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PurchaseDto {

    private List<Long> itemIds;

    private PaymentType paymentType;

    private Double cashAmount;

    private Double totalPrice;

    private Double returnedChange;

    private LocalDateTime purchasedOn;
}
