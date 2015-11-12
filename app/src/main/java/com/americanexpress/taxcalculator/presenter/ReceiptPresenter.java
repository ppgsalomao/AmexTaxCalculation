package com.americanexpress.taxcalculator.presenter;

import com.americanexpress.taxcalculator.ui.views.ReceiptView;

public interface ReceiptPresenter extends Presenter {
    void create();
    void setView(ReceiptView view);
    void finish();
}
