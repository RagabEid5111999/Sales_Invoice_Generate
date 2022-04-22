/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.view;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ragab Eid
 */
public class InvLnDialog extends JDialog{
    
    JLabel lblItemName;
    JTextField txtTtemName;
    JLabel lblTtemPrice;
    JTextField txtItemPrice;
    JLabel lblItemCount;
    JTextField txtItemcount;   
    JButton btnDone;
    JButton btnIgnor;

    public InvLnDialog(InvoiceFrame frame) {
        
        lblItemName = new JLabel("Enter Item Name:");
        txtTtemName = new JTextField(30);
        lblTtemPrice = new JLabel("Enter Item Price:");
        txtItemPrice = new JTextField(30);
        lblItemCount = new JLabel("Enter Number Of Item:");
        txtItemcount = new JTextField(30);
        
        btnDone = new JButton("Done");
        btnIgnor = new JButton("Ignor");
        
        btnDone.setActionCommand("Done");
        btnIgnor.setActionCommand("Ignor");
        
        btnDone.addActionListener(frame.getActionListener());
        btnIgnor.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(4, 4));
        
        add(lblItemName);
        add(txtTtemName);
        add(lblTtemPrice);
        add(txtItemPrice);
        add(lblItemCount);
        add(txtItemcount);
        add(btnDone);
        add(btnIgnor);
        
        pack();
    }

    public JTextField getTxtTtemName() {
        return txtTtemName;
    }

    public JTextField getTxtItemPrice() {
        return txtItemPrice;
    }

    public JTextField getTxtItemcount() {
        return txtItemcount;
    }
    
    
    
}
