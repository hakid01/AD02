/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.xestionfranquicia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author erifc
 */
public class Store implements Serializable{

    private String name;
    private String town;
    private LinkedList<Item> items;
    private LinkedList<Employee> employees;

    public Store() {
    }

    public Store(String name, String town) {
        this.name = name;
        this.town = town;
        this.items = new LinkedList<>();
        this.employees = new LinkedList<>();
    }

    public Store(String name, String town, LinkedList<Item> items, LinkedList<Employee> employees) {
        this.name = name;
        this.town = town;
        this.items = items;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public LinkedList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(LinkedList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Tienda: " + name + ", ciudad: " + town + '.';
    }
    
}
