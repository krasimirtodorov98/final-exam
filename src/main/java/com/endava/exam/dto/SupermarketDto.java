package com.endava.exam.dto;

import com.endava.exam.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SupermarketDto {

    private String name;

    private String address;

    private String phoneNumber;

    private String workHours;

    private List<Item> items;
}
