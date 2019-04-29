package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum ItemFieldName implements FieldNameInterface {
    ITEM_ID("productId"),
    QUANTITY("quantity"),
    PRICE("price"),
    PAYMENT_METHOD("paymentMethod"),
    API_REQUEST_STATUS("apiRequestStatus"),
    API_REQUEST_MESSAGE("apiRequestMessage");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    ItemFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
