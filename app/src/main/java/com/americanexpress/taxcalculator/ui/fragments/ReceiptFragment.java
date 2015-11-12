package com.americanexpress.taxcalculator.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.americanexpress.taxcalculator.PresenterHolder;
import com.americanexpress.taxcalculator.R;
import com.americanexpress.taxcalculator.entities.BasketItem;
import com.americanexpress.taxcalculator.entities.Receipt;
import com.americanexpress.taxcalculator.presenter.ReceiptPresenter;
import com.americanexpress.taxcalculator.presenter.ReceiptPresenterImpl;
import com.americanexpress.taxcalculator.ui.views.ReceiptView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReceiptFragment extends Fragment implements ReceiptView {

    private ReceiptPresenter presenter;

    @Bind(R.id.receipt_text)
    TextView receiptTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_receipt, container, false);
        ButterKnife.bind(this, view);

        this.reloadData(null);

        presenter = createPresenter();
        presenter.create();

        return view;
    }

    public ReceiptPresenter createPresenter() {
        ReceiptPresenter presenter =
                PresenterHolder.getInstance().getPresenter(ReceiptPresenter.class);

        if (presenter != null) {
            presenter.setView(this);
        } else {
            presenter = new ReceiptPresenterImpl(this);
        }
        return presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        presenter.setView(null);
        PresenterHolder.getInstance().putPresenter(ReceiptPresenter.class, presenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.getActivity().isFinishing()) {
            PresenterHolder.getInstance().remove(ReceiptPresenter.class);
        }
    }

    /* ReceiptView */

    @Override
    public void reloadData(Receipt receipt) {
        if(receipt == null
                || receipt.getBasketItems() == null
                || receipt.getBasketItems().size() == 0) {

            this.receiptTextView.setText(R.string.empty_receipt);
        } else {
            StringBuilder receiptText = new StringBuilder();
            for (BasketItem item : receipt.getBasketItems()) {
                receiptText.append(
                        String.format(
                                this.getString(R.string.receipt_item_format),
                                item.getQuantity(),
                                item.getProduct().getName(),
                                item.getTotalValue()));
            }

            receiptText.append(String.format(
                    this.getString(R.string.receipt_taxes_format), receipt.getTaxes()));
            receiptText.append(String.format(
                    this.getString(R.string.receipt_total_format), receipt.getTotal()));

            this.receiptTextView.setText(receiptText.toString());
        }
    }
}
