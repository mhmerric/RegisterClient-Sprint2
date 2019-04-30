package edu.uark.uarkregisterapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.uark.uarkregisterapp.ItemViewActivity;
import edu.uark.uarkregisterapp.R;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class ReportProductsAdapter extends ArrayAdapter<Product> {

    public ReportProductsAdapter(Context context, List<Product> products) {
        super(context, R.layout.list_view_report_product, products);
        //this.mOriginalValues = products;
        //this.mDisplayedValues = products;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.list_view_report_product, parent, false);
        }

        Product product = this.getItem(position);
        //product = this.getItem(position);
        if (product != null) {
            TextView lookupCodeTextView = (TextView) view.findViewById(R.id.list_view_item_product_lookup_code);
            if (lookupCodeTextView != null) {
                lookupCodeTextView.setText(product.getLookupCode());
            }
            // TODO: add total number sold to product class
            TextView numberSoldTextView = (TextView) view.findViewById(R.id.list_view_number_sold);
            if (numberSoldTextView != null) {
                numberSoldTextView.setText("Qty: " + String.format(Locale.getDefault(), "%d", product.getCount()));
            }

            /*TextView priceTextView = (TextView) view.findViewById(R.id.list_view_item_product_price);
            if (priceTextView != null) {
                // If price format is provided
                //priceTextView.setText("$" + String.format(Locale.getDefault(), "%d", product.getPrice()));

                // No price format provided
                priceTextView.setText("$" + df.format(product.getPrice()));
            }*/
        }
        return view;
    }

    DecimalFormat df = new DecimalFormat("#,###.##");
}
