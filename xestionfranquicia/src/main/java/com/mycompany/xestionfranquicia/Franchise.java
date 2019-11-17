/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xestionfranquicia;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author erifc
 */
public class Franchise implements Serializable{

    private LinkedList<Store> stores;
    private LinkedList<Customer> customers;

    public Franchise() {
    }

    public Franchise(LinkedList<Store> stores, LinkedList<Customer> customers) {
        this.stores = stores;
        this.customers = customers;
    }

    public LinkedList<Store> getStores() {
        return stores;
    }

    public void setStores(LinkedList<Store> stores) {
        this.stores = stores;
    }

    public LinkedList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(LinkedList<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Franchise{" + "stores=" + stores + ", customers=" + customers + '}';
    }

    
}
