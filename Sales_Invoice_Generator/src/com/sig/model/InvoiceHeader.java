/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.model;

import com.sig.view.InvoiceFrame;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ragab Eid
 */
public class InvoiceHeader {
    private int invoiceNum;
    private Date invoiceDate;
    private String customerName;    
    private ArrayList<InvoiceLine> lines;

    public InvoiceHeader() {
    }

    public InvoiceHeader(int invoiceNum, Date invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null){
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

    public double getInvoiceTotal(){
        double total = 0.0;
        
        for (int i = 0 ; i < getLines().size(); i++) {
            total += getLines().get(i).getItemTotal();
        }
               
        return total;
    }

    @Override
    public String toString() {
        return invoiceNum + "," + InvoiceFrame.dateFormate.format(invoiceDate) + "," + customerName;
    }

}
