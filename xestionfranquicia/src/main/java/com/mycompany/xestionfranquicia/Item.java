/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xestionfranquicia;

import java.io.Serializable;

/**
 *
 * @author erifc
 */
class Item implements Serializable{
    private String id;
    private String description;
    private float price;
    private int amount;

    public Item() {
    }

    public Item(String id, String description, float price, int amount) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}
