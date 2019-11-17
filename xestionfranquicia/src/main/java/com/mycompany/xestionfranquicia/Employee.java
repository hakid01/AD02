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
class Employee extends Person implements Serializable{

    public Employee(String name, String surname) {
        super(name, surname);
    }
    
}
