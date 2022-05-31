/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.model;

import com.sig.view.InvoiceFrame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ragab Eid
 */
public class InvTableModel extends AbstractTableModel{
    
    private ArrayList<InvoiceHeader> invoicesArray = new ArrayList<>();
    private String[] columns = {"No.", "Date", "Customer", "Total"};

    public InvTableModel(ArrayList<InvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }
    
    

    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        InvoiceHeader invH = invoicesArray.get(i);
            switch (i1){
                case 0: return invH.getInvoiceNum();
                case 1: return InvoiceFrame.dateFormate.format(invH.getInvoiceDate());
                case 2: return invH.getCustomerName();
                case 3: return invH.getInvoiceTotal();
        }
        return "";
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }
    
    
    
}
