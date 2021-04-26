package com.endava.exam.controller;

import com.endava.exam.dto.PurchaseDto;
import com.endava.exam.model.Purchase;
import com.endava.exam.model.enums.PaymentType;
import com.endava.exam.service.PurchaseService;
import com.endava.exam.service.WriteCsvService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final ModelMapper modelMapper;
    private final WriteCsvService writeCsvService;

    @PostMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaseDto> makePurchase(@Valid @RequestParam(value = "supermarketId") String supermarketId,
                                                    @Valid @RequestParam(value = "itemsIds") List<String> itemIds,
                                                    @Valid @RequestParam(value = "paymentType") PaymentType paymentType,
                                                    @Valid @Positive
                                                        @RequestParam(value = "cashAmount") Double cashAmount) {
        Purchase purchase = purchaseService.makePurchase(supermarketId, itemIds, paymentType, cashAmount);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(purchase, PurchaseDto.class));
    }

    @GetMapping(value = "/list-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PurchaseDto>> viewPurchases() {
        return ResponseEntity.ok(purchaseService.viewPurchases()
                .stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/export-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public void exportPurchases(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=exported_all_purchases.csv";
        response.setHeader(headerKey, headerValue);
        writeCsvService.writePurchases(response.getWriter());
    }
}
