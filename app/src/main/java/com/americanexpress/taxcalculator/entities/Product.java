package com.americanexpress.taxcalculator.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private String name;
    private double unitPrice;
    @ProductType
    private int type;
    private boolean imported;

    public Product(String name, double unitPrice, @ProductType int type, boolean imported) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.type = type;
        this.imported = imported;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getType() {
        return type;
    }

    public boolean isImported() {
        return imported;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (Double.compare(product.getUnitPrice(), getUnitPrice()) != 0) return false;
        if (getType() != product.getType()) return false;
        if (isImported() != product.isImported()) return false;

        return getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName().hashCode();
        temp = Double.doubleToLongBits(getUnitPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getType();
        result = 31 * result + (isImported() ? 1 : 0);
        return result;
    }

    /* Parcelable */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeDouble(getUnitPrice());
        dest.writeInt(getType());
        dest.writeByte((byte) (isImported() ? 1 : 0));
    }

    protected Product(Parcel in) {
        this.name = in.readString();
        this.unitPrice = in.readDouble();
        this.type = this.convertToProductType(in.readInt());
        this.imported = (in.readByte() != 0);
    }

    private @ProductType int convertToProductType(int type) {
        switch (type) {
            case ProductType.BOOK:
                return ProductType.BOOK;
            case ProductType.FOOD:
                return ProductType.FOOD;
            case ProductType.MEDICAL:
                return ProductType.MEDICAL;
            default:
                return ProductType.OTHER;
        }
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
