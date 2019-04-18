package edu.uark.uarkregisterapp;

public class Item {

    public String ItemId;
    public String ItemName;
    public String Quantity;
    public String Price;

    public Item() {
        ItemId = "";
        ItemName = "";
        Quantity = "";
        Price = "";
    }

    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public String Status;

    public String getQuantity() {
        return Quantity;
    }
    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public void setItemId(String ItemId){
        this.ItemId=ItemId;
    }
    public String getItemId(){
        return ItemId;
    }

    public void setItemName(String ItemName){
        this.ItemName=ItemName;
    }
    public String getItemName(){
        return ItemName;
    }

    public void setPrice(String Price){
        this.Price=Price;
    }
    public String getPrice(){
        return Price;
    }

    public String getJsonObject(){
        return "{ItemId:"+ItemId+",ItemName:"+ItemName+",Quantity:"+Quantity+"}";
    }
}
