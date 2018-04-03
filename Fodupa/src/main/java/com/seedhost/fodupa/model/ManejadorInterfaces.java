/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.model;
 
import javax.inject.Named;
/**
 *
 * @author fergch97
 */
@Named
public class ManejadorInterfaces {
    private String page;
 
    public String getPage() {
        return page;
    }
    
    public void setPage(String page) {
        this.page = page;
    }
    
    public boolean equals(String page){
        return this.page.equals(page);
    }
}


