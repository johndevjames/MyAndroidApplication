package com.johndevjames.myandroid;

public class DataModel {
    private String Mrp;
    private String item_code;
    private Integer item_id;
    private String item_name;
    private String quantity;
    private String rate;




    public DataModel(Integer item_id, String ItemName, String ItemCode, String Quantity, String Rate, String Mrp) {
        this.item_id = item_id;
        this.item_name = ItemName;
        this.item_code = ItemCode;
        this.quantity = Quantity;
        this.rate = Rate;
        this.Mrp = Mrp;
    }

    public Integer getItemId() {
        return this.item_id;
    }

    public String getItemName() {
        return this.item_name;
    }

    public String getItemCode() {
        return this.item_code;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getRate() {
        return this.rate;
    }

    public String getMrp() {
        return this.Mrp;
    }
}
