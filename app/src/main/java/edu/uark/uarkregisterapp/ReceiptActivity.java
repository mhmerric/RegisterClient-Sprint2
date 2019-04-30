package edu.uark.uarkregisterapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import edu.uark.uarkregisterapp.adapters.CartListAdapter;
import edu.uark.uarkregisterapp.models.api.Item;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class ReceiptActivity extends AppCompatActivity {
    private Transaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_view);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //this.transaction = this.getIntent().getParcelableExtra("transaction");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  // Respond to the action bar's Up/Home button
                this.finish();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        if (!this.productTransition.getId().equals(new UUID(0, 0))) {
            this.getDeleteImageButton().setVisibility(View.VISIBLE);
        } else {
            this.getDeleteImageButton().setVisibility(View.INVISIBLE);
        }*/


        //this.getSaleIdTextView().setText("$" + String.format(Locale.getDefault(), "%d", this.transaction.saleId));
        /*this.getTotalTextView().setText(this.transaction.total);
        this.getSaleIdTextView().setText(this.transaction.saleId);
        this.getCashierIdTextView().setText(this.transaction.employeeId);
        this.getPaymentMethodTextView().setText(this.transaction.paymentMethod);*/

    }

    private TextView getTotalTextView() {
        return (TextView) this.findViewById(R.id.tv_total);
    }

    private TextView getSaleIdTextView() {
        return (TextView) this.findViewById(R.id.saleId);
    }

    private TextView getCashierIdTextView() {
        return (TextView) this.findViewById(R.id.employeeId);
    }

    private TextView getPaymentMethodTextView() {
        return (TextView) this.findViewById(R.id.paymentMethod);
    }

}
