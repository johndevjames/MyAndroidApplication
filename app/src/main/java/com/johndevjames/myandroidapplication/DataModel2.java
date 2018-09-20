package com.johndevjames.myandroidapplication;

public class DataModel2 {
    private Integer Customer_id;
    private String Order_date;
    private Integer Order_no;
    private Integer Product_no;
    private Integer id;
    private String narration;
    private Integer qty;
    private Integer user_id;

    public DataModel2(Integer Customer_id, String Order_date, Integer Order_no, Integer Product_no, String narration, Integer qty, Integer id, Integer user_id) {
        this.Customer_id = Customer_id;
        this.Order_date = Order_date;
        this.Order_no = Order_no;
        this.Product_no = Product_no;
        this.narration = narration;
        this.qty = qty;
        this.id = id;
        this.user_id = user_id;
    }

    public Integer getCustomer_id() {
        return this.Customer_id;
    }

    public String getOrder_date() {
        return this.Order_date;
    }

    public Integer getOrder_no() {
        return this.Order_no;
    }

    public Integer getProduct_no() {
        return this.Product_no;
    }

    public String getNarration() {
        return this.narration;
    }

    public Integer getQty() {
        return this.qty;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getUser_Id() {
        return this.user_id;
    }
}
