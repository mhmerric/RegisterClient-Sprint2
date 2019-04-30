package edu.uark.uarkregisterapp.models.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.json.JSONArray;

import edu.uark.uarkregisterapp.adapters.CartListAdapter;
import edu.uark.uarkregisterapp.models.api.fields.ProductFieldName;
import edu.uark.uarkregisterapp.models.api.fields.TransactionFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;

public class Transaction implements ConvertToJsonInterface, LoadFromJsonInterface<Transaction> {
    public List<Item> selectedItems;
    public String employeeId;
    public int total;
    public String paymentMethod;
    public int saleId;


    public Transaction(List<Item> selectedItems, String employeeId, int total) {
        this.selectedItems = new ArrayList<>();
        this.selectedItems = selectedItems;
        this.employeeId = employeeId;
        this.total = total;
        this.paymentMethod = "cash";
    }

    public Transaction() {
        this.selectedItems = new ArrayList<>();
    }

    public Transaction(Transaction t) {
        //this.selectedItems = new ArrayList<>();
        this.employeeId = t.employeeId;
        this.total = t.total;
        this.paymentMethod = t.paymentMethod;
        this.saleId = t.saleId;
    }

    public void setSaleId(int id) {
        this.saleId = id;
    }


    @Override
    public JSONObject convertToJson() {
        JSONObject transaction = new JSONObject();

        try {
            // convert items to json array
            //Gson gson = new Gson();
            //String items = new Gson().toJson(this.selectedItems);
            //items = this.selectedItems.
            JSONArray items = new JSONArray();
            for(Item i: CartListAdapter.selectedItems) {
                JSONObject item = new JSONObject();
                item.put("productId", i.getItemId());
                item.put("quantityPurchased", i.getQuantity());
                item.put("subtotal", i.getPrice());
                items.put(item);
            }

            // put attribute in JSONObject
            transaction.put(TransactionFieldName.ITEMS.getFieldName(), items);
            transaction.put(TransactionFieldName.EMPLOYEE_ID.getFieldName(), Integer.parseInt(this.employeeId));
            transaction.put(TransactionFieldName.TOTAL_PRICE.getFieldName(), this.total);
            transaction.put(TransactionFieldName.PAYMENT_METHOD.getFieldName(), this.paymentMethod);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    @Override
    public Transaction loadFromJson(JSONObject rawJsonObject) {
        /*String value = rawJsonObject.optString(ProductFieldName.ID.getFieldName());
        if (!StringUtils.isBlank(value)) {
            this.id = UUID.fromString(value);
        }*/

        this.employeeId = rawJsonObject.optString(TransactionFieldName.EMPLOYEE_ID.getFieldName());
        this.total = rawJsonObject.optInt(TransactionFieldName.TOTAL_PRICE.getFieldName());
        this.saleId = rawJsonObject.optInt(TransactionFieldName.SALE_ID.getFieldName());
        this.paymentMethod = rawJsonObject.optString(TransactionFieldName.PAYMENT_METHOD.getFieldName());
        //this.selectedItems = rawJsonObject.optJSONArray(TransactionFieldName.ITEMS.getFieldName());
        /*value = rawJsonObject.optString(ProductFieldName.CREATED_ON.getFieldName());
        if (!StringUtils.isBlank(value)) {
            try {
                this.createdOn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US).parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/

        return this;
    }
}
