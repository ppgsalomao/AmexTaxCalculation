package com.americanexpress.taxcalculator.presenter;

import android.support.annotation.VisibleForTesting;

import com.americanexpress.taxcalculator.EventBus;
import com.americanexpress.taxcalculator.entities.events.AddItemEvent;
import com.americanexpress.taxcalculator.entities.events.ClearReceiptEvent;
import com.americanexpress.taxcalculator.model.TaxCalculationModel;
import com.americanexpress.taxcalculator.model.TaxCalculationModelImpl;
import com.americanexpress.taxcalculator.ui.views.ReceiptView;
import com.squareup.otto.Subscribe;

public class ReceiptPresenterImpl implements ReceiptPresenter {

    private final TaxCalculationModel model;
    private ReceiptView view;

    public ReceiptPresenterImpl(ReceiptView view) {
        this(view, new TaxCalculationModelImpl());
        EventBus.getInstance().register(this);
    }

    @VisibleForTesting
    ReceiptPresenterImpl(ReceiptView view, TaxCalculationModel model) {
        this.view = view;
        this.model = model;
    }

    @Subscribe
    public void onAddBasketItem(AddItemEvent event) {
        if(event != null) {
            this.model.addItem(event.getInput());
            this.view.reloadData(this.model.getReceipt());
        }
    }

    @Subscribe
    public void onClearReceipt(ClearReceiptEvent event) {
        this.model.clearReceipt();
        this.view.reloadData(this.model.getReceipt());
    }

    @Override
    public void create() {
        this.view.reloadData(this.model.getReceipt());
    }

    @Override
    public void setView(ReceiptView view) {
        this.view = view;
    }

    @Override
    public void finish() {
        EventBus.getInstance().unregister(this);
    }
}
