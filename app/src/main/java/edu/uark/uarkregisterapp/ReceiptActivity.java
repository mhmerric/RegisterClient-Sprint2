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
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class ReceiptActivity extends AppCompatActivity {
    private ProductTransition productTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        this.productTransition = this.getIntent().getParcelableExtra("transaction");

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

        this.getProductLookupCodeTextView().setText(this.productTransition.getLookupCode());
        this.getProductPriceTextView().setText("$" + String.format(Locale.getDefault(), "%d", this.productTransition.getPrice()));

    }

    private TextView getProductLookupCodeTextView() {
        return (TextView) this.findViewById(R.id.text_view_lookup_code);
    }

    private EditText getProductQuantityEditText() {
        return (EditText) this.findViewById(R.id.edit_text_select_quantity);
    }

    private TextView getProductPriceTextView() {
        return (TextView) this.findViewById(R.id.text_view_subtotal);
    }

}
