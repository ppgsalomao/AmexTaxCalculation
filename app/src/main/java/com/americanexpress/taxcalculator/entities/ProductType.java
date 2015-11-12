package com.americanexpress.taxcalculator.entities;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({ ProductType.MEDICAL, ProductType.BOOK, ProductType.FOOD, ProductType.OTHER })
@Retention(RetentionPolicy.SOURCE)
public @interface ProductType {
    int OTHER = 0;
    int MEDICAL = 1;
    int BOOK = 2;
    int FOOD = 3;
}
