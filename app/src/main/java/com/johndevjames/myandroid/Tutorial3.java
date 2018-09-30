package com.johndevjames.myandroid;

public class Tutorial3 {
    private Integer customer_id;
    private String customer_name;

    public Tutorial3(Integer customer_id, String customer_name) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
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
}
