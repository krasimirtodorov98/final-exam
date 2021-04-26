package com.endava.exam.service;

import com.endava.exam.model.Supermarket;
import java.util.List;
import java.util.Optional;

public interface SupermarketService {

    Supermarket createSupermarket(String name, String address, String phoneNumber, String workingHours);

    List<String> addItemsToSupermarket(String supermarketId, List<String> itemsIds);

    Supermarket findSupermarketById(String id);
}
