package com.americanexpress.taxcalculator.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class BasketItem implements Parcelable {

    private Product product;
    private int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTaxValue() {
        double taxMultiplier = 0.0;

        if(product.getType() == ProductType.OTHER)
            taxMultiplier += 0.1;
        if(product.isImported())
            taxMultiplier += 0.05;

        double taxValue = product.getUnitPrice() * this.quantity * taxMultiplier;
        return Math.ceil(taxValue * 20.0) / 20.0;
    }

    public double getTotalValue() {
        return ( product.getUnitPrice() * this.quantity ) + this.getTaxValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketItem)) return false;

        BasketItem that = (BasketItem) o;
        return quantity == that.quantity && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    /* Parcelable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.quantity);
        dest.writeParcelable(this.product, flags);
    }

    private BasketItem(Parcel in) {
        this.quantity = in.readInt();
        this.product = in.readParcelable(Product.class.getClassLoader());
    }

    public static final Creator<BasketItem> CREATOR = new Creator<BasketItem>() {
        public BasketItem createFromParcel(Parcel source) {
            return new BasketItem(source);
        }

        public BasketItem[] newArray(int size) {
            return new BasketItem[size];
        }
    };
}

