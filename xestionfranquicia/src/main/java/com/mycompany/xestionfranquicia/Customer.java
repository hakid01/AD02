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
class Customer extends Person implements Serializable{
    private String email;

    public Customer(String email, String name, String surname) {
        super(name, surname);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente: " + getSurname()+", "+ getName()+". Email: " + email + ".";

    }

  
}
