package com.johndevjames.myandroidapplication;

public class Tutorial2 {
    private Integer Outstanding_bal;
    private Integer customer_id;
    private String customer_name;

    public Tutorial2(Integer customer_id, String customer_name, Integer Outstanding_bal) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.Outstanding_bal = Outstanding_bal;
    }

    public Integer getcustomer_id() {
        return this.customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return this.customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Integer getOutstanding_bal() {
        return this.Outstanding_bal;
    }

    public void setOutstanding_bal(Integer Outstanding_bal) {
        this.Outstanding_bal = Outstanding_bal;
    }
}
