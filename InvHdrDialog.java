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
public class InvHdrDialog extends JDialog{
    
    JTextField txtInvCustumName;
    JTextField txtInvDate;
    JLabel lblCstmName;
    JLabel lblInvDate;
    JButton btnCreate;
    JButton btnCancle;

    public InvHdrDialog(InvoiceFrame f) {
        
        lblCstmName = new JLabel("Enter Custmer Name:");
        txtInvCustumName = new JTextField(30);
        lblInvDate = new JLabel("Enter Date Of The Invoice:");
        txtInvDate = new JTextField(30);
        btnCreate = new JButton("Create");
        btnCancle = new JButton("Cancle Invoice");
        
        btnCreate.setActionCommand("Create");
        btnCancle.setActionCommand("Cancle Invoice");
        
        btnCreate.addActionListener(f.getActionListener());
        btnCancle.addActionListener(f.getActionListener());
        setLayout(new GridLayout(4, 4));
        
        add(lblCstmName);
        add(txtInvCustumName);
        add(lblInvDate);
        add(txtInvDate);
        add(btnCreate);
        add(btnCancle);
        
        pack();
        
    }

    public JTextField getTxtInvCustumName() {
        return txtInvCustumName;
    }

    public JTextField getTxtInvDate() {
        return txtInvDate;
    }
    
    
    
    
}
