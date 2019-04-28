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

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import edu.uark.uarkregisterapp.adapters.CartListAdapter;
import edu.uark.uarkregisterapp.models.api.Item;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;
import edu.uark.uarkregisterapp.ShoppingCartActivity;


public class ItemViewActivity extends AppCompatActivity {

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

        this.productTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_product));

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
        this.getProductPriceTextView().setText("$" + String.format(Locale.getDefault(), "%d", this.productTransition.getCount()));

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

    // TODO: add error checking for no quantity input
    public void addToCart(View view) {
        Item item = new Item(productTransition);
        // Set quantity
        item.setQuantity(Integer.parseInt(getProductQuantityEditText().getText().toString()));
        // Calculate subtotal
        int subtotal = (item.getQuantity() * item.getPrice());
        item.setPrice(subtotal);

        if(CartListAdapter.selectedItems.indexOf(item) == -1) {
            CartListAdapter.selectedItems.add(item);
            Toast.makeText(ItemViewActivity.this,"Added To Cart!",Toast.LENGTH_LONG).show();
            //Toast toast = Toast.makeText(ItemViewActivity.this,"Added To Cart!",Toast.LENGTH_LONG);
            //toast.setMargin(20,50);
            //toast.show();
        }
    }
}
