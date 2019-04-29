package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.app.AlertDialog;

import android.widget.TextView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import edu.uark.uarkregisterapp.adapters.CartListAdapter;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.Item;
import edu.uark.uarkregisterapp.models.api.Receipt;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.services.EmployeeService;
import edu.uark.uarkregisterapp.models.api.services.TransactionService;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;
import edu.uark.uarkregisterapp.models.api.fields.TransactionFieldName;

import com.google.gson.*;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;



public class ShoppingCartActivity extends AppCompatActivity {

    public static TextView tv_total;
    public static int total = 0;
    public int itemPosition = 0;
    public static CartListAdapter cartListAdapter;
    //public static JsonArray jsonCartList = new JsonArray();

    public static int getTotal() {
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        // Set back button
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        cartListAdapter = new CartListAdapter(this, CartListAdapter.selectedItems);

        this.getItemsView().setAdapter(cartListAdapter);

        tv_total = findViewById(R.id.tv_total);

        //getIntentData();

        calculateTotal();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ListView getItemsView() {
        return (ListView) this.findViewById(R.id.list_view_items);
    }

    /*
    private void getIntentData(){
        if(getIntent()!=null && getIntent().getExtras()!=null){
            // Get the Required Parameters for sending Order to server.
        }
    }
    */

    public static void calculateTotal(){
        int i=0;
        total=0;
        while(i<CartListAdapter.selectedItems.size()){
            total = total + (Integer.valueOf(CartListAdapter.selectedItems.get(i).getPrice()));
            i++;
        }
        tv_total.setText(" $"+total);
    }


    public void insertOrder(View view) {

        if(CartListAdapter.selectedItems.size() > 0){

            /*Gson gson = new Gson();

            jsonCartList.add(gson.toJson(CartListAdapter.selectedItems));

            final JSONObject orderInfo = new JSONObject();
            try {
                orderInfo.put(TransactionFieldName.ITEMS.getFieldName(), jsonCartList);
                orderInfo.put(TransactionFieldName.EMPLOYEE_ID.getFieldName(), MainActivity.employeeTransition.getEmployeeId());
                orderInfo.put(TransactionFieldName.PAYMENT_METHOD.getFieldName(), "cash");
                orderInfo.put(TransactionFieldName.TOTAL_PRICE.getFieldName(), this.total);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            //final Transaction transaction = new Transaction(CartListAdapter.selectedItems, MainActivity.employeeTransition.getId(), this.total);



            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            //placeOrderRequest(orderInfo);
                            //startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                            //CartListAdapter.selectedItems.clear();
                            new CreateTransactionTask().execute(new Transaction(CartListAdapter.selectedItems, MainActivity.employeeTransition.getEmployeeId(), ShoppingCartActivity.getTotal()));
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
            builder.setMessage("Do you want to place Order?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        } else {
            Toast.makeText(ShoppingCartActivity.this,"No items in Cart!",Toast.LENGTH_LONG).show();
        }


    }

    private void placeOrderRequest(JSONObject order) {
        //Send Request to Server with required Parameters
        //(new TransactionService()).createTransaction(order);


    }


    private class CreateTransactionTask extends AsyncTask<Transaction, Void, ApiResponse<Transaction>> {
        // Creating popup message
        @Override
        protected void onPreExecute() {
            this.createTransactionAlert = new android.support.v7.app.AlertDialog.Builder(ShoppingCartActivity.this)
                    .setMessage(R.string.alert_dialog_create_transaction)
                    .create();
            this.createTransactionAlert.show();
        }

        // Create Transaction
        @Override
        protected ApiResponse<Transaction> doInBackground(Transaction... transactions) {
            if (transactions.length > 0) {
                return (new TransactionService()).createTransaction(transactions[0]);
            } else {
                return (new ApiResponse<Transaction>()).setValidResponse(false);
            }
        }

        // Check response
        @Override
        protected void onPostExecute(ApiResponse<Transaction> apiResponse) {
            this.createTransactionAlert.dismiss();

            if (!apiResponse.isValidResponse()) {
                new android.support.v7.app.AlertDialog.Builder(ShoppingCartActivity.this)
                        .setMessage(R.string.alert_dialog_create_transaction_failed)
                        .create()
                        .show();
                return;
            }

            Intent intent = new Intent(getApplicationContext(), ReceiptActivity.class);
            Transaction t = new Transaction(CartListAdapter.selectedItems, MainActivity.employeeTransition.getEmployeeId(), ShoppingCartActivity.getTotal());
            //t.setSaleId(apiResponse.saleId);
            // TODO: create receipt screen
            //intent.putExtra("transaction", (Parcelable) new Transaction(CartListAdapter.selectedItems, MainActivity.employeeTransition.getEmployeeId(), ShoppingCartActivity.getTotal()));
            // TODO: add saleId to intent
            //intent.putExtra("saleId", )
            Receipt receipt = new Receipt();
            //intent.putExtra("receipt", receipt.addAll(apiResponse.getData()));

            startActivity(intent);
        }

        private android.support.v7.app.AlertDialog createTransactionAlert;
    }

}

