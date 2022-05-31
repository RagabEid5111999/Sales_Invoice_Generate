/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.model;

/**
 *
 * @author Ragab Eid
 */
public class InvoiceLine {
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private double itemTotal;
    private InvoiceHeader header;

    public InvoiceLine() {
    }

    public InvoiceLine(String itemName, double itemPrice, int itemCount, InvoiceHeader header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    
    public double getItemTotal(){
        return itemPrice * itemCount;
    }

    @Override
    public String toString() {
        return header.getInvoiceNum() + "," + itemName + "," + itemPrice + "," + itemCount;
    }
    
}
