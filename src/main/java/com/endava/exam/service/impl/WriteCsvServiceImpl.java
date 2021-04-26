package com.endava.exam.service.impl;

import com.endava.exam.dto.PurchaseDto;
import com.endava.exam.model.Item;
import com.endava.exam.service.PurchaseService;
import com.endava.exam.service.WriteCsvService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WriteCsvServiceImpl implements WriteCsvService {

    private final PurchaseService purchaseService;
    private final ModelMapper modelMapper;


    @Override
    public void writePurchases(Writer writer) throws IOException {
        String[] csvHeader = {"Id", "PaymentType", "CashAmount", "TotalPrice", "ReturnedChange", "PurchasedOn"};
        String[] nameMapping = {"id", "paymentType", "cashAmount", "totalPrice", "returnedChange", "purchasedOn"};
        List<PurchaseDto> purchases = purchaseService.viewPurchases()
                .stream()
                .map(purchase -> {
                    PurchaseDto purchaseDto = modelMapper.map(purchase, PurchaseDto.class);
                    List<String> items = purchase.getItemsIds()
                            .stream()
                            .map(Item::getName)
                            .collect(Collectors.toList());
                    return purchaseDto;
                })
                .collect(Collectors.toList());
        ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        beanWriter.writeHeader(csvHeader);

        for (PurchaseDto purchaseDto : purchases) {
            beanWriter.write(purchaseDto, nameMapping);
        }
        beanWriter.close();
    }
}
