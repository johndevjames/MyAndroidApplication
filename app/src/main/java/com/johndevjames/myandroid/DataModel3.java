package com.johndevjames.myandroid;

public class DataModel3 {
    private Integer Customer_id;
    private String Order_date;
    private Integer Order_no;
    private Integer user_id;

    public DataModel3(Integer Customer_id, String Order_date, Integer Order_no, Integer user_id) {
        this.Customer_id = Customer_id;
        this.Order_date = Order_date;
        this.Order_no = Order_no;
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

    public Integer getUser_Id() {
        return this.user_id;
    }
}
