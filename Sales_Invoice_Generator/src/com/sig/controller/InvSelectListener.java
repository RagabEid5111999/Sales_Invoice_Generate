/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.controller;

import com.sig.model.InvTableModel2;
import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.view.InvoiceFrame;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ragab Eid
 */
public class InvSelectListener implements ListSelectionListener {
    
    private InvoiceFrame frame;

    public InvSelectListener(InvoiceFrame frame) {
        this.frame = frame;
    }        

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        int selectedRow = frame.getTblInvoiceHeader().getSelectedRow();
        // out.println(selectedRow);
        if (selectedRow != -1){
            InvoiceHeader invH = frame.getInvoiceHeaders().get(selectedRow);
            ArrayList<InvoiceLine> lines = invH.getLines();
            InvTableModel2 m2 = new InvTableModel2(lines);
            frame.setInvoiceLines(lines);
            frame.getTblInvoiceLine().setModel(m2);
            frame.getLblInvoiceNum().setText(""+invH.getInvoiceNum());
            frame.getTxtInvoiceDate().setText(frame.dateFormate.format(invH.getInvoiceDate()));
            frame.getTxtCustomerName().setText(invH.getCustomerName());
            frame.getLblInvoiceTotal().setText(String.valueOf(invH.getInvoiceTotal()));
        }
    }
    
}
