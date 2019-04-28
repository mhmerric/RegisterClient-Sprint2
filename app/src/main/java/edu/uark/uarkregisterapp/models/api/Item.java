package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.fields.ItemFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class Item implements ConvertToJsonInterface, LoadFromJsonInterface<Item> {

    private String lookupCode;      // product NAme
    private UUID itemId;            // productID
    private int quantityPurchased;           // quantityPurchased
    private int price;              // Single item's subtotal
    private String paymentMethod;   // Payment Method

    public Item (ProductTransition p) {
        this.itemId = p.getId();
        this.lookupCode = p.getLookupCode();
        this.quantityPurchased = 1;
        this.price = p.getPrice();
        this.paymentMethod = "Cash";
    }


    public String getLookupCode() {
        return this.lookupCode;
    }
    public Item setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
        return this;
    }

    public Item setItemId(UUID itemId) {
        this.itemId = itemId;
        return this;
    }
    public UUID getItemId(){
        return this.itemId;
    }

    public Item setQuantity(int quantity) {
        this.quantityPurchased = quantity;
        return this;
    }
    public int getQuantity() {
        return quantityPurchased;
    }

    public Item setPrice(int price){
        this.price = price;
        return this;
    }
    public int getPrice(){
        return this.price;
    }

    public String getJsonObject(){
        return "{ItemId:"+this.itemId+",Quantity:"+this.quantityPurchased+"}";
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(ItemFieldName.ITEM_ID.getFieldName(), this.itemId.toString());
            jsonObject.put(ItemFieldName.QUANTITY.getFieldName(), this.quantityPurchased);
            jsonObject.put(ItemFieldName.PRICE.getFieldName(), this.price);
            //jsonObject.put(ItemFieldName.PAYMENT_METHOD.getFieldName(), this.paymentMethod);
            //jsonObject.put(ItemFieldName.CREATED_ON.getFieldName(), (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)).format(this.createdOn));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    @Override
    public Item loadFromJson(JSONObject rawJsonObject) {
        String value = rawJsonObject.optString(ItemFieldName.ITEM_ID.getFieldName());
        if (!StringUtils.isBlank(value)) {
            this.itemId = UUID.fromString(value);
        }

        this.quantityPurchased = rawJsonObject.optInt(ItemFieldName.QUANTITY.getFieldName());
        this.price = rawJsonObject.optInt(ItemFieldName.PRICE.getFieldName());

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
