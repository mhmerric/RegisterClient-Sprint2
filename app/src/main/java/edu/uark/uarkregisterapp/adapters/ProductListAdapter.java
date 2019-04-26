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

public class ProductListAdapter extends ArrayAdapter<Product> implements Filterable {

	//private List<Product> mOriginalValues; // Original Values
	//private List<Product> mDisplayedValues;    // Values to be displayed
	//Product product;

	public ProductListAdapter(Context context, List<Product> products) {
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





/*
	private class ValueFilter extends Filter{
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {

			FilterResults results = new FilterResults();

			if (constraint != null && constraint.length() > 0) {

				ArrayList<Product> filterList = new ArrayList<Product>();

				for (int i = 0; i < mOriginalValues.size(); i++) {

					if ((mOriginalValues.get(i).getLookupCode().toUpperCase()).contains(constraint.toString().toUpperCase())) {

						Product productData = new Product(mOriginalValues.get(i));
						filterList.add(productData);
					}
				}

				results.count = filterList.size();
				results.values = filterList;

			} else {

				results.count = mOriginalValues.size();
				results.values = mOriginalValues;

			}
			return results;
		}
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mOriginalValues = (ArrayList<Product>) results.values;
			notifyDataSetChanged();
		}
	}
*/



	/*
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<Product>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<Product> FilteredArrList = new ArrayList<Product>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<Product>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                 ********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).name;
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new Product(mOriginalValues.get(i).name,mOriginalValues.get(i).price));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
*/

}
