package com.americanexpress.taxcalculator.entities;

import android.os.Parcel;

import com.americanexpress.taxcalculator.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class ReceiptTest {

    private BasketItem createItem(@ProductType int type, boolean international, int quantity) {
        return new BasketItem(new Product("Product", 13.87, type, international), quantity);
    }

    @Test
    public void testCreation() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);
    }

    @Test
    public void testReceiptParcelable() {
        Receipt receipt = new Receipt();
        receipt.addBasketItem(this.createItem(ProductType.OTHER, true, 13));
        receipt.addBasketItem(this.createItem(ProductType.BOOK, false, 57));

        Parcel parcel = Parcel.obtain();
        receipt.writeToParcel(parcel, 0);

        parcel.setDataPosition(0); // Reset the parcel for reading

        Receipt createdFromParcelReceipt = Receipt.CREATOR.createFromParcel(parcel);
        assertEquals(receipt, createdFromParcelReceipt);
    }

    @Test
    public void testClear() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.FOOD, false, 12);
        receipt.addBasketItem(item);
        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);

        receipt.clear();
        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingFoodBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.FOOD, false, 12);
        receipt.addBasketItem(item);

        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingMedicalBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.MEDICAL, false, 12);
        receipt.addBasketItem(item);

        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingBookBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.BOOK, false, 12);
        receipt.addBasketItem(item);

        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingOtherBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.OTHER, false, 12);
        receipt.addBasketItem(item);

        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingImportedFoodBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.FOOD, true, 12);
        receipt.addBasketItem(item);

        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingImportedMedicalBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.MEDICAL, true, 12);
        receipt.addBasketItem(item);

        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingImportedBookBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.BOOK, true, 12);
        receipt.addBasketItem(item);

        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingImportedOtherBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        BasketItem item = this.createItem(ProductType.OTHER, true, 12);
        receipt.addBasketItem(item);

        assertNotNull(receipt.getBasketItems());
        assertEquals(1, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(0));
        assertEquals(item.getTotalValue(), receipt.getTotal(), 0.001);
        assertEquals(item.getTaxValue(), receipt.getTaxes(), 0.001);
    }

    @Test
    public void testAddingMultipleBasketItems() {
        Receipt receipt = new Receipt();

        assertNotNull(receipt.getBasketItems());
        assertEquals(0, receipt.getBasketItems().size());
        assertEquals(0.0, receipt.getTotal(), 0.001);
        assertEquals(0.0, receipt.getTaxes(), 0.001);

        double totalPrice = 0.0;
        double totalTax = 0.0;
        int listIndex = 0;
        int listSize = 1;

        BasketItem item = this.createItem(ProductType.FOOD, false, 12);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize++, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex++));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);

        item = this.createItem(ProductType.BOOK, false, 4);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize++, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex++));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);

        item = this.createItem(ProductType.FOOD, false, 23);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize++, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex++));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);

        item = this.createItem(ProductType.MEDICAL, false, 54);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize++, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex++));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);

        item = this.createItem(ProductType.OTHER, false, 1);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize++, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex++));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);

        item = this.createItem(ProductType.BOOK, true, 6);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize++, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex++));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);

        item = this.createItem(ProductType.FOOD, true, 25);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize++, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex++));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);

        item = this.createItem(ProductType.MEDICAL, true, 15);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize++, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex++));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);

        item = this.createItem(ProductType.OTHER, true, 2);
        receipt.addBasketItem(item);
        totalPrice += item.getTotalValue();
        totalTax += item.getTaxValue();

        assertNotNull(receipt.getBasketItems());
        assertEquals(listSize, receipt.getBasketItems().size());
        assertEquals(item, receipt.getBasketItems().get(listIndex));
        assertEquals(totalPrice, receipt.getTotal(), 0.001);
        assertEquals(totalTax, receipt.getTaxes(), 0.001);
    }
}