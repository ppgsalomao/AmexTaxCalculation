package com.americanexpress.taxcalculator.ui.activities;

import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.americanexpress.taxcalculator.EventBus;
import com.americanexpress.taxcalculator.R;
import com.americanexpress.taxcalculator.entities.events.AddItemEvent;
import com.americanexpress.taxcalculator.entities.events.ClearReceiptEvent;
import com.americanexpress.taxcalculator.ui.fragments.ReceiptFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.fab)
    protected FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(this.toolbar);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.main_content_container, new ReceiptFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reload) {
            EventBus.getInstance().post(new ClearReceiptEvent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onFloatingActionButtonClick() {

        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_add_button_text,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    /*EditText basketItemEditText = ButterKnife.findById(dialog, R.id.dialog_input_edit_text);
                                    EventBus.getInstance().post(new AddItemEvent(
                                            basketItemEditText.getText().toString()));*/
                                } catch(Exception e) {
                                    Snackbar.make(MainActivity.this.floatingActionButton,
                                                    "Invalid input.",
                                                    Snackbar.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        })
                .setView(R.layout.view_dialog_input)
                .show();
    }
}
