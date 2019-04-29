package edu.uark.uarkregisterapp.models.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.fields.TransactionFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;

public class Transaction implements ConvertToJsonInterface, LoadFromJsonInterface<Transaction> {
    private List<Item> selectedItems;
    private String employeeId;
    private int total;
    private String paymentMethod;
    //private int saleId;


    public Transaction(List<Item> selectedItems, String employeeId, int total) {
        //this.selectedItems = new ArrayList<>();
        this.selectedItems = selectedItems;
        this.employeeId = employeeId;
        this.total = total;
        this.paymentMethod = "cash";
    }

    public Transaction() {
        //this.selectedItems = new ArrayList<>();
        //this.employeeId = new UUID(0,0);
        this.employeeId = "";
        this.total = 0;
        this.paymentMethod = "cash";
    }

    public void setSaleId(int id) {
        //this.saleId = id;
    }


    @Override
    public JSONObject convertToJson() {
        JSONObject transaction = new JSONObject();

        try {
            // convert items to json array
            //Gson gson = new Gson();
            String items = new Gson().toJson(this.selectedItems);
            //items = this.selectedItems.
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
        return null;
    }
}
