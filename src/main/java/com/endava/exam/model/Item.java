package com.endava.exam.model;

import com.endava.exam.model.enums.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item extends BaseEntity{

    @NotBlank
    @Column
    private String name;

    @NotNull
    @Column
    private Double price;

    @NotNull
    @Enumerated
    @Column(name = "item_type")
    private ItemType itemType;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Supermarket supermarket;
}
