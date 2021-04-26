package com.endava.exam.dto;

import com.endava.exam.model.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private String name;

    private Double price;

    private ItemType itemType;
}
