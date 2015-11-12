package com.americanexpress.taxcalculator.model;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.americanexpress.taxcalculator.entities.BasketItem;
import com.americanexpress.taxcalculator.entities.Product;
import com.americanexpress.taxcalculator.entities.ProductType;
import com.americanexpress.taxcalculator.entities.Receipt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaxCalculationModelImpl implements TaxCalculationModel {

    private Receipt receipt;

    public TaxCalculationModelImpl() {
        this.receipt = new Receipt();
    }

    @VisibleForTesting
    public TaxCalculationModelImpl(Receipt receipt) {
        this.receipt = receipt;
    }

    @Override
    public Receipt getReceipt() {
        return this.receipt;
    }

    @Override
    public void addItem(String input) {
        Pattern pattern = Pattern.compile("^(\\d+) (.+) at (\\d+\\.\\d{2})$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            int quantity = Integer.parseInt(matcher.group(1));
            String name = matcher.group(2);
            double unitPrice = Double.parseDouble(matcher.group(3));

            this.addItem(quantity, name, unitPrice);
        } else {
            throw new IllegalArgumentException("The line does not conform`s to input pattern.");
        }
    }

    @Override
    public void addItem(int quantity, String name, double unitPrice) {
        boolean isImported = name.contains("imported");

        @ProductType int productType = ProductType.OTHER;
        if(isBook(name)) productType = ProductType.BOOK;
        else if(isFood(name)) productType = ProductType.FOOD;
        else if(isMedical(name)) productType = ProductType.MEDICAL;

        this.receipt.addBasketItem(
                new BasketItem(new Product(name, unitPrice, productType, isImported), quantity));
    }

    @Override
    public void clearReceipt() {
        this.getReceipt().clear();
    }

    private boolean isBook(String name) {
        return name.contains("book");
    }

    private boolean isFood(String name) {
        return name.contains("chocolate");
    }

    private boolean isMedical(String name) {
        return name.contains("pill");
    }
}
