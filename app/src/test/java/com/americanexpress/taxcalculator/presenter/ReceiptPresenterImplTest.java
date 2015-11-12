package com.americanexpress.taxcalculator.presenter;

import com.americanexpress.taxcalculator.BuildConfig;
import com.americanexpress.taxcalculator.entities.Receipt;
import com.americanexpress.taxcalculator.entities.events.AddItemEvent;
import com.americanexpress.taxcalculator.entities.events.ClearReceiptEvent;
import com.americanexpress.taxcalculator.model.TaxCalculationModel;
import com.americanexpress.taxcalculator.ui.views.ReceiptView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class ReceiptPresenterImplTest {

    @Mock
    private ReceiptView view;
    @Mock
    private TaxCalculationModel model;
    @Captor
    private ArgumentCaptor<Receipt> receiptArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreation() {
        ReceiptPresenterImpl presenter = new ReceiptPresenterImpl(view, model);
        presenter.create();
        verify(view).reloadData(receiptArgumentCaptor.capture());
        assertEquals(this.model.getReceipt(), receiptArgumentCaptor.getValue());
    }

    @Test
    public void testOnAddItemEvent() {
        ReceiptPresenterImpl presenter = new ReceiptPresenterImpl(view, model);
        presenter.create();

        presenter.onAddBasketItem(null);
        verify(this.model, never()).addItem(anyString());
        verify(this.view, times(1)).reloadData(any(Receipt.class));

        presenter.onAddBasketItem(mock(AddItemEvent.class));
        verify(this.model).addItem(anyString());
        verify(this.view, times(2)).reloadData(any(Receipt.class));
    }

    @Test
    public void testOnClearReceiptEvent() {
        ReceiptPresenterImpl presenter = new ReceiptPresenterImpl(view, model);
        presenter.create();
        presenter.onClearReceipt(null);
        verify(this.model).clearReceipt();
        verify(this.view, times(2)).reloadData(any(Receipt.class));

        presenter.onClearReceipt(mock(ClearReceiptEvent.class));
        verify(this.model, times(2)).clearReceipt();
        verify(this.view, times(3)).reloadData(any(Receipt.class));
    }
}