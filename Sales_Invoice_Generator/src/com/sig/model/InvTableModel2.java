/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ragab Eid
 */
public class InvTableModel2 extends AbstractTableModel{
    
    private ArrayList<InvoiceLine> linesArray = new ArrayList<>();
    private String[] columns = {"No.", "Item Name", "Item Price", "Count", "Item Total"};

    public InvTableModel2(ArrayList<InvoiceLine> linesArray) {
        this.linesArray = linesArray;
    }
    
    
    @Override
    public int getRowCount() {
        return linesArray == null? 0 : linesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        InvoiceLine invLine = linesArray.get(i);
        switch (i1){
            case 0: return invLine.getHeader().getInvoiceNum();
            case 1: return invLine.getItemName();
            case 2: return invLine.getItemPrice();
            case 3: return invLine.getItemCount();
            case 4: return invLine.getItemTotal();
            default: return "";
        }
    }
    
    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    @Override
    public String toString() {
        return "InvTableModel2{" + "linesArray=" + linesArray + ", columns=" + columns + '}';
    }
    
    
    
}
