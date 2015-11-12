package com.americanexpress.taxcalculator.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Receipt implements Parcelable {

    private List<BasketItem> basketItems;
    private double taxes;
    private double total;

    public Receipt() {
        this.basketItems = new ArrayList<>();
        this.taxes = 0.0;
        this.total = 0.0;
    }

    public void addBasketItem(BasketItem item) {
        this.taxes += item.getTaxValue();
        this.total += item.getTotalValue();
        this.basketItems.add(item);
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public double getTaxes() {
        return taxes;
    }

    public double getTotal() {
        return total;
    }

    public void clear() {
        this.basketItems.clear();
        this.taxes = 0.0;
        this.total = 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt)) return false;

        Receipt receipt = (Receipt) o;

        if (Double.compare(receipt.taxes, taxes) != 0) return false;
        if (Double.compare(receipt.total, total) != 0) return false;
        return basketItems.equals(receipt.basketItems);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = basketItems.hashCode();
        temp = Double.doubleToLongBits(taxes);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /* Parcelable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(basketItems);
        dest.writeDouble(this.taxes);
        dest.writeDouble(this.total);
    }

    private Receipt(Parcel in) {
        this.basketItems = new ArrayList<>();
        in.readTypedList(this.basketItems, BasketItem.CREATOR);
        this.taxes = in.readDouble();
        this.total = in.readDouble();
    }

    public static final Creator<Receipt> CREATOR = new Creator<Receipt>() {
        public Receipt createFromParcel(Parcel source) {
            return new Receipt(source);
        }

        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };
}
