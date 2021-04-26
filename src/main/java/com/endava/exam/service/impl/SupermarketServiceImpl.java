package com.endava.exam.service.impl;

import com.endava.exam.model.Item;
import com.endava.exam.model.Supermarket;
import com.endava.exam.repository.ItemRepository;
import com.endava.exam.repository.SupermarketRepository;
import com.endava.exam.service.SupermarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupermarketServiceImpl implements SupermarketService {

    private final SupermarketRepository supermarketRepository;
    private final ItemRepository itemRepository;

    @Override
    public Supermarket createSupermarket(String name, String address, String phoneNumber, String workingHours) {
        if (supermarketRepository.existsByName(name)) {
            throw new IllegalArgumentException("Supermarket with this name already exists.");
        }
        Supermarket supermarket = new Supermarket();
        supermarket.setName(name);
        supermarket.setAddress(address);
        supermarket.setPhoneNumber(phoneNumber);
        supermarket.setWorkHours(workingHours);
        return supermarketRepository.save(supermarket);
    }

    @Override
    public List<String> addItemsToSupermarket(String supermarketId, List<String> itemsIds) {
        if (!supermarketRepository.existsById(supermarketId)) {
            throw new IllegalArgumentException("Supermarket with the provided id not found");
        }
        List<Item> newItems = new ArrayList<>();
        for (String itemId : itemsIds) {
            newItems.add(itemRepository.findById(itemId)
                    .orElseThrow(() -> new EntityNotFoundException("Item with id " + itemId + " unavailable.")));
        }
        Supermarket supermarket = supermarketRepository.findSupermarketById(supermarketId);
        supermarket.setItems(newItems);
        supermarketRepository.save(supermarket);
        List<String> addedItemsIds = newItems
                .stream()
                .map(Item -> Item.getId())
                .collect(Collectors.toList());
        return addedItemsIds;
    }

    @Override
    public Supermarket findSupermarketById(String id) {
        if (!supermarketRepository.existsById(id)) {
            throw new IllegalArgumentException("Supermarket with the provided id not found");
        }
        return supermarketRepository.findSupermarketById(id);
    }

}
