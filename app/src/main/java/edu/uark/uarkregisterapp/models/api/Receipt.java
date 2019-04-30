package edu.uark.uarkregisterapp.models.api;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private int totalPrice;
    private String paymentMethod;
    private int employeeId;
    private List<Item> items;
    private int saleId;

    public Receipt() {
        this.totalPrice = 0;
        this.paymentMethod = "";
        this.employeeId = 0;
        this.items = new ArrayList<>();
        this.saleId = 0;
    }
}
