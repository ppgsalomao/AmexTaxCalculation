package com.americanexpress.taxcalculator.entities;

import android.os.Parcel;

import com.americanexpress.taxcalculator.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class BasketItemTest {

    @Test
    public void testCreation() {
        Product product = new Product("Product", 12.63, ProductType.FOOD, true);
        BasketItem basketItem = new BasketItem(product, 19);

        assertSame(product, basketItem.getProduct());
        assertEquals(19, basketItem.getQuantity());
    }

    private double roundToTheNearestFiveCents(double value) {
        return Math.ceil(value * 20.0) / 20.0;
    }

    @Test
    public void testBasketItemParcelable() {
        Product product = new Product("Product", 13.33, ProductType.MEDICAL, true);
        BasketItem basketItem = new BasketItem(product, 19);

        Parcel parcel = Parcel.obtain();
        basketItem.writeToParcel(parcel, 0);

        parcel.setDataPosition(0); // Reset the parcel for reading

        BasketItem createdFromParcelBasketItem = BasketItem.CREATOR.createFromParcel(parcel);
        assertTrue(basketItem.equals(createdFromParcelBasketItem));
    }

    @Test
    public void testFoodBasketItem() {
        double price = 19.53;
        int quantity = 1;
        Product product = new Product("Product", price, ProductType.FOOD, false);
        BasketItem basketItem = new BasketItem(product, quantity);

        assertEquals((price * quantity), basketItem.getTotalValue(), 0.01);
        assertEquals(0.0, basketItem.getTaxValue(), 0.01);
    }

    @Test
    public void testBookBasketItem() {
        double price = 59.13;
        int quantity = 5;
        Product product = new Product("Product", price, ProductType.BOOK, false);
        BasketItem basketItem = new BasketItem(product, quantity);

        assertEquals((price * quantity), basketItem.getTotalValue(), 0.01);
        assertEquals(0.0, basketItem.getTaxValue(), 0.01);
    }

    @Test
    public void testMedicalBasketItem() {
        double price = 78.13;
        int quantity = 4;
        Product product = new Product("Product", price, ProductType.MEDICAL, false);
        BasketItem basketItem = new BasketItem(product, quantity);

        assertEquals((price * quantity), basketItem.getTotalValue(), 0.01);
        assertEquals(0.0, basketItem.getTaxValue(), 0.01);
    }

    @Test
    public void testOtherBasketItem() {
        double price = 78.13;
        int quantity = 4;
        Product product = new Product("Product", price, ProductType.OTHER, false);
        BasketItem basketItem = new BasketItem(product, quantity);

        double taxValue = roundToTheNearestFiveCents(price * quantity * 0.1);
        assertEquals((price * quantity) + taxValue, basketItem.getTotalValue(), 0.01);
        assertEquals(taxValue, basketItem.getTaxValue(), 0.01);
    }

    @Test
    public void testImportedFoodBasketItem() {
        double price = 11.25;
        int quantity = 1;
        Product product = new Product("box of imported chocolates", price, ProductType.FOOD, true);
        BasketItem basketItem = new BasketItem(product, quantity);

        double taxValue = roundToTheNearestFiveCents(price * quantity * 0.05);
        assertEquals((price * quantity) + taxValue, basketItem.getTotalValue(), 0.01);
        assertEquals(taxValue, basketItem.getTaxValue(), 0.01);
    }

    @Test
    public void testImportedBookBasketItem() {
        double price = 19.53;
        int quantity = 1;
        Product product = new Product("Product", price, ProductType.BOOK, true);
        BasketItem basketItem = new BasketItem(product, quantity);

        double taxValue = roundToTheNearestFiveCents(price * quantity * 0.05);
        assertEquals((price * quantity) + taxValue, basketItem.getTotalValue(), 0.01);
        assertEquals(taxValue, basketItem.getTaxValue(), 0.01);
    }

    @Test
    public void testImportedMedicalBasketItem() {
        double price = 84.63;
        int quantity = 82;
        Product product = new Product("Product", price, ProductType.MEDICAL, true);
        BasketItem basketItem = new BasketItem(product, quantity);

        double taxValue = roundToTheNearestFiveCents(price * quantity * 0.05);
        assertEquals((price * quantity) + taxValue, basketItem.getTotalValue(), 0.01);
        assertEquals(taxValue, basketItem.getTaxValue(), 0.01);
    }

    @Test
    public void testImportedOtherBasketItem() {
        double price = 635.17;
        int quantity = 2;
        Product product = new Product("Product", price, ProductType.OTHER, true);
        BasketItem basketItem = new BasketItem(product, quantity);

        double taxValue = roundToTheNearestFiveCents(price * quantity * 0.15);
        assertEquals((price * quantity) + taxValue, basketItem.getTotalValue(), 0.01);
        assertEquals(taxValue, basketItem.getTaxValue(), 0.01);
    }
}