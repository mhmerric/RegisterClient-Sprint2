package edu.uark.uarkregisterapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.MenuItem;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import edu.uark.uarkregisterapp.adapters.CartListAdapter;
import edu.uark.uarkregisterapp.adapters.ProductListAdapter;
import edu.uark.uarkregisterapp.models.api.Product;


public class ShoppingCartActivity extends AppCompatActivity {

    public static TextView tv_total;
    public static int total=0;
    String jsonCartList;

    private List<Item> items;
    private CartListAdapter cartListAdapter;

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

        this.items = new ArrayList<>();
        this.cartListAdapter = new CartListAdapter(this, this.items);

        this.getItemsView().setAdapter(this.cartListAdapter);

        tv_total = findViewById(R.id.tv_total);


        //recyclerView = findViewById(R.id.list_view_items);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        //recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);

        //cartListAdapter = new CartListAdapter(ShoppingCartActivity.this,Item.recycler_itemlist.setAdapter(cartListAdapter);

        //getIntentData();

        //calculateTotal();

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

    public static void calculateTotal(){
        int i=0;
        total=0;
        while(i<ItemListAdapter.selecteditems.size()){
            total=total + ( Integer.valueOf(ItemListAdapter.selecteditems.get(i).getRate()) * Integer.valueOf(ItemListAdapter.selecteditems.get(i).getQuantity()) );
            i++;
        }
        tv_total.setText(""+total);
    }

    public void insertOrder(View view){

        if(total>0){

            Gson gson = new Gson();
            jsonCartList = gson.toJson(ItemListAdapter.selecteditems);

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            placeOrderRequest();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
            builder.setMessage("Do you want to place Order ?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();

        }else{
            Toast.makeText(ShoppingCartActivity.this,"No items in Cart !",Toast.LENGTH_LONG).show();
        }


    }


    private void placeOrderRequest(){
        //Send Request to Server with required Parameters

   //jsonCartList - Consists of Objects of all product selected.


    }
    */
}

