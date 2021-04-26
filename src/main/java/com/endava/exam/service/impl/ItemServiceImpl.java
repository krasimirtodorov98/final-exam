package com.endava.exam.service.impl;

import com.endava.exam.model.Item;
import com.endava.exam.model.enums.ItemType;
import com.endava.exam.repository.ItemRepository;
import com.endava.exam.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item createItem(String name, Double price, String type) {
        if (!ItemType.contains(type)) {
            throw new IllegalArgumentException("Invalid item type.");
        }
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setItemType(ItemType.valueOf(type));
        return itemRepository.save(item);
    }
}
