package com.americanexpress.taxcalculator.ui.fragments;

import com.americanexpress.taxcalculator.BuildConfig;
import com.americanexpress.taxcalculator.R;
import com.americanexpress.taxcalculator.entities.BasketItem;
import com.americanexpress.taxcalculator.entities.Product;
import com.americanexpress.taxcalculator.entities.ProductType;
import com.americanexpress.taxcalculator.entities.Receipt;
import com.americanexpress.taxcalculator.presenter.ReceiptPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentController;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class ReceiptFragmentTest {

    @Test
    public void testOnCreate() throws Exception {
        final ReceiptPresenter presenter = mock(ReceiptPresenter.class);
        ReceiptFragment fragment = new ReceiptFragment() {
            @Override
            public ReceiptPresenter createPresenter() {
                return presenter;
            }
        };
        FragmentController<ReceiptFragment> fragmentController = FragmentController.of(fragment);
        fragmentController.create();
        verify(presenter).create();

        assertEquals(fragment.getString(R.string.empty_receipt), fragment.receiptTextView.getText());
    }

    @Test
    public void testReloadWithEmptyReceipt() throws Exception {
        final ReceiptPresenter presenter = mock(ReceiptPresenter.class);
        ReceiptFragment fragment = new ReceiptFragment() {
            @Override
            public ReceiptPresenter createPresenter() {
                return presenter;
            }
        };
        FragmentController<ReceiptFragment> fragmentController = FragmentController.of(fragment);
        fragmentController.create();

        Receipt receipt = new Receipt();
        fragment.reloadData(receipt);
        assertEquals(fragment.getString(R.string.empty_receipt), fragment.receiptTextView.getText());

        receipt.addBasketItem(new BasketItem(new Product("book", 12.0, ProductType.BOOK, false), 1));
        fragment.reloadData(receipt);
        assertEquals("1 book: 12.00\\nSales Taxes: 0.00\\nTotal: 12.00", fragment.receiptTextView.getText());
    }
}
