package com.endava.exam.model;

import com.endava.exam.model.enums.ItemType;
import com.endava.exam.model.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Entity
@Getter
@Setter
@Table(name = "purchases")
public class Purchase extends BaseEntity{

    @NotBlank
    @ManyToMany
    private List<Item> itemsIds;

    @NotBlank
    @Enumerated
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "cash_amount")
    private Double cashAmount;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "returned_change")
    private Double returnedChange;

    @Column(name = "purchased_on")
    private LocalDateTime purchasedOn;

    @ManyToOne
    @JoinColumn(name = "supermarket_id")
    private Supermarket supermarket;

}
