package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.LayoutInflater.Filter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import edu.uark.uarkregisterapp.adapters.ProductListAdapter;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.services.ProductService;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;
import edu.uark.uarkregisterapp.models.api.Item;

public class ProductsListingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products_listing);
		setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

		EditText theFilter = (EditText) findViewById(R.id.filter);


		ActionBar actionBar = this.getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		this.products = new ArrayList<>();


		this.productListAdapter = new ProductListAdapter(this, this.products);
		this.getProductsListView().setAdapter(this.productListAdapter);

		this.getProductsListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), ItemViewActivity.class);

				intent.putExtra(
						getString(R.string.intent_extra_product),
						new ProductTransition((Product) getProductsListView().getItemAtPosition(position))
				);

				startActivity(intent);
			}
		});


		// Transition to ProductViewActivity
		/*this.getProductsListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), ProductViewActivity.class);

				intent.putExtra(
					getString(R.string.intent_extra_product),
					new ProductTransition((Product) getProductsListView().getItemAtPosition(position))
				);

				startActivity(intent);
			}
		});*/

		theFilter.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//(ProductsListingActivity.this).productListAdapter.getFilter().filter(s.toString());
				String input = s.toString().toLowerCase();
				List<Product> filteredList= new ArrayList<>();
				//(new RetrieveProductsTask()).execute();

				for (Product p : products) {
					if ((p.getLookupCode().toLowerCase()).startsWith(input.toLowerCase())) {

						Product result = new Product(p);
						filteredList.add(result);
					}
				}

				updateList(filteredList);

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		(new RetrieveProductsTask()).execute();
	}

	private ListView getProductsListView() {
		return (ListView) this.findViewById(R.id.list_view_products);
	}

	public void viewCartButtonOnClick(View view) {
		this.startActivity(new Intent(getApplicationContext(), ShoppingCartActivity.class));
	}


	// Filtering
	// **********************************************
	public void updateList(List<Product> newList) {
		if(newList.size() > 0) {
			products.clear();
			products.addAll(newList);
			(this.productListAdapter).notifyDataSetChanged();
		} else {
			(new RetrieveProductsTask()).execute();
		}
	}
/*
	@Override
	public boolean onQueryTextSubmit(String query) {
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {

		String input = newText.toLowerCase();
		List<Product> filteredList= new ArrayList<>();

		for(Product p : products) {
			if ((p.getLookupCode().toLowerCase()).contains(input.toLowerCase())) {

				Product productData = new Product(p);
				filteredList.add(productData);
			}
		}

		this.updateList(filteredList);

		return false;
	}*/
	// **********************************************

	private class RetrieveProductsTask extends AsyncTask<Void, Void, ApiResponse<List<Product>>> {
		@Override
		protected void onPreExecute() {
			this.loadingProductsAlert.show();
		}

		@Override
		protected ApiResponse<List<Product>> doInBackground(Void... params) {
			ApiResponse<List<Product>> apiResponse = (new ProductService()).getProducts();

			if (apiResponse.isValidResponse()) {
				products.clear();
				products.addAll(apiResponse.getData());
			}

			return apiResponse;
		}

		@Override
		protected void onPostExecute(ApiResponse<List<Product>> apiResponse) {
			if (apiResponse.isValidResponse()) {
				productListAdapter.notifyDataSetChanged();
			}

			this.loadingProductsAlert.dismiss();

			if (!apiResponse.isValidResponse()) {
				new AlertDialog.Builder(ProductsListingActivity.this).
					setMessage(R.string.alert_dialog_products_load_failure).
					setPositiveButton(
						R.string.button_dismiss,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.dismiss();
							}
						}
					).
					create().
					show();
			}
		}

		private AlertDialog loadingProductsAlert;

		private RetrieveProductsTask() {
			this.loadingProductsAlert = new AlertDialog.Builder(ProductsListingActivity.this).
				setMessage(R.string.alert_dialog_products_loading).
				create();
		}
	}



	private List<Product> products;
	//private List<Product> filterProducts;
	private ProductListAdapter productListAdapter;
}
