package com.endava.exam.controller;

import com.endava.exam.dto.ItemDto;
import com.endava.exam.dto.SupermarketDto;
import com.endava.exam.model.Item;
import com.endava.exam.model.Supermarket;
import com.endava.exam.model.enums.ItemType;
import com.endava.exam.service.ItemService;
import com.endava.exam.service.SupermarketService;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(value = "/v1/supermarket")
@RequiredArgsConstructor
public class SupermarketController {

    private final ModelMapper modelMapper;
    private final SupermarketService supermarketService;

    @PostMapping(value = "/create-supermarket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupermarketDto> createSupermarket(
                                              @Valid @Length(max = 64)
                                                @RequestParam(value = "name") String name,
                                              @Valid  @Pattern(regexp = "[A-Z]{1}[a-z]{0,49}\\s[,]{1}\\s[A-Z]{1}[a-z]{0,49}\\s[0-9]{0,28}", message = "Invalid address format.")
                                              @Length(max = 128)
                                                @RequestParam(value = "address") String address,
                                              @Valid @Pattern(regexp = "[0][8][7-9][0-9]{7}", message = "Invalid phone number.")
                                                @RequestParam(value = "phone") String phoneNumber,
                                              @Valid @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9][-]([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Working hours must be in format HH:mm-HH:mm")
                                                @RequestParam(value = "workHours") String workHours){
        Supermarket supermarket = supermarketService.createSupermarket(name, address, phoneNumber, workHours);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(supermarket, SupermarketDto.class));
    }

    @PostMapping(value = "/add-items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addItemsToSupermarket(
                                                @Valid @RequestParam(value = "supermarketId") String supermarketId,
                                                @Valid @RequestParam(value = "itemsIds") List<String> itemsIds){
        return ResponseEntity.ok(supermarketId +  supermarketService.addItemsToSupermarket(supermarketId, itemsIds));
    }

    @GetMapping(value = "/find-supermarket/{supermarketId}")
    public ResponseEntity<SupermarketDto> findSupermarketById(@Valid @PathVariable String supermarketId){
        Supermarket supermarket = supermarketService.findSupermarketById(supermarketId);
        return ResponseEntity.ok(modelMapper.map(supermarket, SupermarketDto.class));
    }
}
