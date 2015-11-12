package com.americanexpress.taxcalculator.model;

import com.americanexpress.taxcalculator.BuildConfig;
import com.americanexpress.taxcalculator.entities.BasketItem;
import com.americanexpress.taxcalculator.entities.Product;
import com.americanexpress.taxcalculator.entities.ProductType;
import com.americanexpress.taxcalculator.entities.Receipt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class TaxCalculationModelImplTest {

    @Mock
    private Receipt receipt;
    @Captor
    private ArgumentCaptor<BasketItem> basketItemArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private String generateInputForBasketItem(BasketItem basketItem) {
        return String.format("%d %s at %.2f", basketItem.getQuantity(),
                basketItem.getProduct().getName(), basketItem.getProduct().getUnitPrice());
    }

    @Test
    public void testCreation() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));
    }

    @Test
    public void testClear() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        taxCalculationModel.clearReceipt();
        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt()).clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithEmptyInput() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        taxCalculationModel.addItem("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithWrongFormatQuantityInput() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        taxCalculationModel.addItem("as book at 12.80");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithEmptyNameQuantityInput() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        taxCalculationModel.addItem("1 at 12.80");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithEmptyPriceInput() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        taxCalculationModel.addItem("1 book at");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithWrongFormatPriceInput() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        taxCalculationModel.addItem("1 book at 1b2.83");
    }

    @Test
    public void testAddBookItem() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        BasketItem item = new BasketItem(
                new Product("book", 13.87, ProductType.BOOK, false), 1);
        taxCalculationModel.addItem(this.generateInputForBasketItem(item));

        verify(taxCalculationModel.getReceipt()).addBasketItem(basketItemArgumentCaptor.capture());
        assertEquals(item, basketItemArgumentCaptor.getValue());
    }

    @Test
    public void testAddFoodItem() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        BasketItem item = new BasketItem(
                new Product("chocolate bar", 13.87, ProductType.FOOD, false), 1);
        taxCalculationModel.addItem(this.generateInputForBasketItem(item));

        verify(taxCalculationModel.getReceipt()).addBasketItem(basketItemArgumentCaptor.capture());
        assertEquals(item, basketItemArgumentCaptor.getValue());
    }

    @Test
    public void testAddMedicalItem() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        BasketItem item = new BasketItem(
                new Product("packet of headache pills", 13.87, ProductType.MEDICAL, false), 1);
        taxCalculationModel.addItem(this.generateInputForBasketItem(item));

        verify(taxCalculationModel.getReceipt()).addBasketItem(basketItemArgumentCaptor.capture());
        assertEquals(item, basketItemArgumentCaptor.getValue());
    }

    @Test
    public void testAddOtherItem() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        BasketItem item = new BasketItem(
                new Product("music CD", 13.87, ProductType.OTHER, false), 1);
        taxCalculationModel.addItem(this.generateInputForBasketItem(item));

        verify(taxCalculationModel.getReceipt()).addBasketItem(basketItemArgumentCaptor.capture());
        assertEquals(item, basketItemArgumentCaptor.getValue());
    }

    @Test
    public void testAddImportedBookItem() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        BasketItem item = new BasketItem(
                new Product("imported book", 13.87, ProductType.BOOK, true), 1);
        taxCalculationModel.addItem(this.generateInputForBasketItem(item));

        verify(taxCalculationModel.getReceipt()).addBasketItem(basketItemArgumentCaptor.capture());
        assertEquals(item, basketItemArgumentCaptor.getValue());
    }

    @Test
    public void testAddImportedFoodItem() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        BasketItem item = new BasketItem(
                new Product("box of imported chocolates", 13.87, ProductType.FOOD, true), 1);
        taxCalculationModel.addItem(this.generateInputForBasketItem(item));

        verify(taxCalculationModel.getReceipt()).addBasketItem(basketItemArgumentCaptor.capture());
        assertEquals(item, basketItemArgumentCaptor.getValue());
    }

    @Test
    public void testAddImportedMedicalItem() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        BasketItem item = new BasketItem(
                new Product("imported packet of headache pills", 13.87, ProductType.MEDICAL, true), 1);
        taxCalculationModel.addItem(this.generateInputForBasketItem(item));

        verify(taxCalculationModel.getReceipt()).addBasketItem(basketItemArgumentCaptor.capture());
        assertEquals(item, basketItemArgumentCaptor.getValue());
    }

    @Test
    public void testAddImportedOtherItem() {
        TaxCalculationModelImpl taxCalculationModel = new TaxCalculationModelImpl(this.receipt);

        assertNotNull(taxCalculationModel.getReceipt());
        verify(taxCalculationModel.getReceipt(), never()).addBasketItem(any(BasketItem.class));

        BasketItem item = new BasketItem(
                new Product("imported bottles of perfume", 13.87, ProductType.OTHER, true), 1);
        taxCalculationModel.addItem(this.generateInputForBasketItem(item));

        verify(taxCalculationModel.getReceipt()).addBasketItem(basketItemArgumentCaptor.capture());
        assertEquals(item, basketItemArgumentCaptor.getValue());
    }
}