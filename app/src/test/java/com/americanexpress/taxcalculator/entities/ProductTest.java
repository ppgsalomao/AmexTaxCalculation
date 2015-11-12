package com.americanexpress.taxcalculator.entities;

import android.os.Parcel;

import com.americanexpress.taxcalculator.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class ProductTest {

    @Test
    public void testCreation() {
        Product product = new Product("Product", 98.321, ProductType.OTHER, false);

        assertEquals("Product", product.getName());
        assertEquals(98.321, product.getUnitPrice(), 0.001);
        assertEquals(ProductType.OTHER, product.getType());
        assertEquals(false, product.isImported());
    }

    @Test
    public void testProductParcelable() {
        Product product = new Product("Product", 12.53, ProductType.BOOK, true);

        Parcel parcel = Parcel.obtain();
        product.writeToParcel(parcel, 0);

        parcel.setDataPosition(0); // Reset the parcel for reading

        Product createdFromParcelProduct = Product.CREATOR.createFromParcel(parcel);
        assertEquals(product, createdFromParcelProduct);
    }
}