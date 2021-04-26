package com.endava.exam.controller;

import com.endava.exam.dto.ItemDto;
import com.endava.exam.model.Item;
import com.endava.exam.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@RestController
@RequestMapping(value = "/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ModelMapper modelMapper;
    private final ItemService itemService;

    @PostMapping(value = "/create-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDto> createItem(@Valid @RequestParam(value = "name") String name,
                                              @Valid
                                                    @DecimalMin(value = "0.01", message = "Price must be greater than 0.01.")
                                                    @DecimalMax(value = "10000", inclusive = false, message = "Price must be less than 10 000.")
                                                    @RequestParam(value = "price") Double price,
                                              @Valid @RequestParam(value = "type") String type) {
        Item item = itemService.createItem(name, price, type);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(item, ItemDto.class));
    }
}
