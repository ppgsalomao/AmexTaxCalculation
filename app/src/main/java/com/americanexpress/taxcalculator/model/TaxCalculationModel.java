package com.americanexpress.taxcalculator.model;

import com.americanexpress.taxcalculator.entities.Receipt;

public interface TaxCalculationModel {

    Receipt getReceipt();
    void addItem(String input);
    void addItem(int quantity, String name, double unitPrice);
    void clearReceipt();

}
