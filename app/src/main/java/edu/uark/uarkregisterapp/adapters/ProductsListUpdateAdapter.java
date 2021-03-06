package edu.uark.uarkregisterapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.uark.uarkregisterapp.ProductsListingActivity;
import edu.uark.uarkregisterapp.R;
import edu.uark.uarkregisterapp.models.api.Product;

public class ProductsListUpdateAdapter extends ArrayAdapter<Product> implements Filterable {

    //private List<Product> mOriginalValues; // Original Values
    //private List<Product> mDisplayedValues;    // Values to be displayed
    //Product product;

    public ProductsListUpdateAdapter(Context context, List<Product> products) {
        super(context, R.layout.list_view_item_product, products);
        //this.mOriginalValues = products;
        //this.mDisplayedValues = products;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.list_view_item_product, parent, false);
        }

        Product product = this.getItem(position);
        //product = this.getItem(position);
        if (product != null) {
            TextView lookupCodeTextView = (TextView) view.findViewById(R.id.list_view_item_product_lookup_code);
            if (lookupCodeTextView != null) {
                lookupCodeTextView.setText(product.getLookupCode());
            }

            TextView countTextView = (TextView) view.findViewById(R.id.list_view_item_product_count);
            if (countTextView != null) {
                countTextView.setText("Qty: " + String.format(Locale.getDefault(), "%d", product.getCount()));
            }

            TextView priceTextView = (TextView) view.findViewById(R.id.list_view_item_product_price);
            if (priceTextView != null) {
                // If price format is provided
                //priceTextView.setText("$" + String.format(Locale.getDefault(), "%d", product.getPrice()));

                // No price format provided
                priceTextView.setText("$" + df.format(product.getPrice()));
            }
        }

        return view;
    }

    DecimalFormat df = new DecimalFormat("#,###.##");
}