package com.endava.exam.service;

import com.endava.exam.model.Item;

public interface ItemService {
    Item createItem(String name, Double price, String type);
}
