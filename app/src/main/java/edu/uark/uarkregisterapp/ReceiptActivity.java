package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class ReceiptActivity extends AppCompatActivity {
    public static TransactionTransition transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_view);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // TODO: find why values are not getting stored in TransactionTransition
        this.transaction = this.getIntent().getParcelableExtra(this.getString(R.string.intent_transaction));

        /*this.getTotalTextView().setText(this.transaction.getTotal());
        this.getSaleIdTextView().setText(this.transaction.getSaleId());
        this.getCashierIdTextView().setText(this.transaction.getEmployeeId());
        this.getPaymentMethodTextView().setText(this.transaction.getPaymentMethod());*/

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
    protected void onStart() {
        super.onStart();

        this.getTotalTextView().setText("$" + Integer.toString(this.transaction.getTotal()));
        this.getSaleIdTextView().setText(Integer.toString(this.transaction.getSaleId()));
        this.getCashierIdTextView().setText(Integer.toString(this.transaction.getEmployeeId()));
        this.getPaymentMethodTextView().setText(this.transaction.getPaymentMethod());
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.getTotalTextView().setText("$" + Integer.toString(this.transaction.getTotal()));
        this.getSaleIdTextView().setText(Integer.toString(this.transaction.getSaleId()));
        this.getCashierIdTextView().setText(Integer.toString(this.transaction.getEmployeeId()));
        this.getPaymentMethodTextView().setText(this.transaction.getPaymentMethod());

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

    public void exitButton(View view) {
        this.startActivity(new Intent(getApplicationContext(),LandingActivity.class));
    }

}
