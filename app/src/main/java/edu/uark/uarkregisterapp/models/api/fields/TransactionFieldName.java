package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum TransactionFieldName implements FieldNameInterface {
    EMPLOYEE_ID("employeeId"),
    ITEMS("items"),
    TOTAL_PRICE("totalPrice"),
    PAYMENT_METHOD("paymentMethod"),
    SALE_ID("saleId");


    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    TransactionFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
