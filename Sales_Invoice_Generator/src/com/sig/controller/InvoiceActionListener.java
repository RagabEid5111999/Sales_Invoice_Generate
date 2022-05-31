/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.controller;

import com.sig.model.InvTableModel;
import com.sig.model.InvTableModel2;
import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.view.InvHdrDialog;
import com.sig.view.InvLnDialog;
import com.sig.view.InvoiceFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.charset.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author Ragab Eid
 */
public class InvoiceActionListener implements ActionListener{

    private InvoiceFrame frame;
    private InvHdrDialog invHdrDialog;
    private InvLnDialog invLnDialog;
    private InvoiceHeader invoiceHeader;
    public InvoiceActionListener(InvoiceFrame frame) {
        this.frame = frame;
    }    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            
            case "Load File":
                loadFile();
                break;
                
            case "Create":
                create();
                break;
                
            case "Cancle Invoice":
                cancleInvoice();
                break;
                
            case "Save File":
                saveFile();
                break;
                
            case "Create New Invoice":
                createNewInv();
                break;
                
            case "Delete Invoice":
                deleteInv();
                break;
                
            case "Save":
                save();
                break;
                
            case "Done":
                done();
                break;
                
            case "Ignor":
                ignor();
                break;
                
            case "Cancle":
                cancle();
                break;
        }
    }

    private void loadFile() {
        
            JFileChooser chooser = new JFileChooser();
            //chooser.setMultiSelectionEnabled(true);   // this line make you choose multi files
            int result = chooser.showOpenDialog(frame);
        try {
            if(result == chooser.APPROVE_OPTION){  // Approve option that mean you clacked open not cancle            
                File hF = chooser.getSelectedFile();
                Path headerPath = Paths.get(hF.getAbsolutePath());                
                List<String> headerLines = Files.readAllLines(headerPath, Charset.forName("US-ASCII"));
                ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
                for (String headerLine : headerLines){
                    String[] spLines = headerLine.split(",");
                    String sCode = spLines[0];
                    String sDate = spLines[1];
                    String sName = spLines[2];
                    int code = Integer.parseInt(sCode);
                    Date invioceDate = InvoiceFrame.dateFormate.parse(sDate);
                    InvoiceHeader header = new InvoiceHeader(code, invioceDate, sName);
                    invoiceHeaders.add(header);
                }
                frame.setInvoiceHeaders(invoiceHeaders);
                result = chooser.showOpenDialog(frame);
                if(result == chooser.APPROVE_OPTION){
                    File lF = chooser.getSelectedFile();
                    Path linePath = Paths.get(lF.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath, Charset.forName("US-ASCII"));
                    ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
                    for (String lineLine : lineLines){
                        String[] spLines = lineLine.split(",");
                        String sCode = spLines[0];
                        String sItemName = spLines[1];
                        String sItemPrice = spLines[2];
                        String sCount = spLines[3];
                        int lCode = Integer.parseInt(sCode);
                        double price = Double.parseDouble(sItemPrice);
                        int count = Integer.parseInt(sCount);
                        InvoiceHeader invH = frame.getInvObject(lCode);
                        InvoiceLine line = new InvoiceLine(sItemName, price, count, invH);
                        invH.getLines().add(line);
                    }
                }
                InvTableModel tableModel1 = new InvTableModel(invoiceHeaders);
                frame.setTableModel1(tableModel1);
                frame.getTblInvoiceHeader().setModel(tableModel1);
            }
            
        }catch(IOException | ParseException ex){
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNewInv() {
        
        invHdrDialog = new InvHdrDialog(frame);
        invHdrDialog.setVisible(true);        
        
    }

    private void create() {
        Date d = new Date();
        try {
            d = frame.dateFormate.parse(invHdrDialog.getTxtInvDate().toString());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Can not parse Date Format", "Invaild Date Format", JOptionPane.ERROR_MESSAGE);
        }
        InvoiceHeader invoiceHeader = new InvoiceHeader(frame.getInvoiceHeaders().size()+1, d, invHdrDialog.getTxtInvCustumName().getText());
        frame.getInvoiceHeaders().add(invoiceHeader);
        frame.getTableModel1().fireTableDataChanged();
        invHdrDialog.setVisible(false);
        invHdrDialog.dispose();
        invHdrDialog = null;
    }

    private void cancleInvoice() {
        invHdrDialog.setVisible(false);
        invHdrDialog.dispose();
        invHdrDialog = null;
    }

    private void deleteInv() {
        int i = frame.getTblInvoiceHeader().getSelectedRow();
        if (i != -1){
            frame.getInvoiceHeaders().remove(i);
            frame.getTableModel1().fireTableDataChanged();
            frame.getTblInvoiceLine().setModel(new InvTableModel2(new ArrayList<InvoiceLine>()));
            frame.setInvoiceLines(null);
            frame.getTxtCustomerName().setText("");
            frame.getTxtInvoiceDate().setText("");
            frame.getLblInvoiceNum().setText("");
            frame.getLblInvoiceTotal().setText("");
        }
    }

    private void ignor() {
        invLnDialog.setVisible(false);
        invLnDialog.dispose();
        invLnDialog = null;    }

    private void done() {        
        
        double d = 1.0;
        int i = 1;
        try {
            d = Double.parseDouble(invLnDialog.getTxtItemPrice().getText());
            i = Integer.parseInt(invLnDialog.getTxtItemcount().getText());
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Can not convert number", "can not convert", JOptionPane.ERROR_MESSAGE);
        }
        int sRow = frame.getTblInvoiceHeader().getSelectedRow();
        if (sRow != -1){
            InvoiceLine invoiceLine = new InvoiceLine(invLnDialog.getTxtTtemName().getText(), d, i, frame.getInvoiceHeaders().get(sRow));
            frame.getInvoiceLines().add(invoiceLine);
            InvTableModel2 invTableModel2 = (InvTableModel2)frame.getTblInvoiceLine().getModel();
            invTableModel2.fireTableDataChanged();
            frame.getLblInvoiceTotal().setText(String.valueOf(frame.getInvoiceHeaders().get(sRow).getInvoiceTotal()));
            frame.getTableModel1().fireTableDataChanged();
        }
        frame.getTblInvoiceHeader().setRowSelectionInterval(sRow, sRow);
        invLnDialog.setVisible(false);
        invLnDialog.dispose();
        invLnDialog = null;
        
    }

    private void save() {
        
        invLnDialog = new InvLnDialog(frame);
        invLnDialog.setVisible(true);
        
    }

    private void cancle() {
        int sRow = frame.getTblInvoiceLine().getSelectedRow();
        int sInvRow = frame.getTblInvoiceHeader().getSelectedRow();
        out.println("sr = "+sRow);
        if (sRow != -1) {
            frame.getInvoiceLines().remove(sRow);
            InvTableModel2 invTableModel2 = (InvTableModel2)frame.getTblInvoiceLine().getModel();
            invTableModel2.fireTableDataChanged();
            frame.getLblInvoiceTotal().setText(String.valueOf(frame.getInvoiceHeaders().get(sRow).getInvoiceTotal()));
            frame.getTableModel1().fireTableDataChanged();
            frame.getTblInvoiceHeader().setRowSelectionInterval(sInvRow, sInvRow);
        }        
    }

    private void saveFile() {
        FileWriter fileWriter = null;
        try {
            JFileChooser jFileChooser = new JFileChooser();
            int saved = jFileChooser.showSaveDialog(frame);
            if (saved == JFileChooser.APPROVE_OPTION){
                File fileH = jFileChooser.getSelectedFile();
                FileWriter fileWriterH = new FileWriter(fileH);
                String headers = "";
                String lines = "";
                for (InvoiceHeader invoiceHeader : frame.getInvoiceHeaders()){
                    headers += invoiceHeader.toString()+"\n";
                    for (InvoiceLine invoiceLine : invoiceHeader.getLines()){
                        lines += invoiceLine.toString()+"\n";
                    }
                }
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                saved = jFileChooser.showSaveDialog(frame);
                File fileL = jFileChooser.getSelectedFile();
                FileWriter fileWriterL = new FileWriter(fileL);
                fileWriterH.write(headers);
                fileWriterL.write(lines);
                fileWriterH.close();
                fileWriterL.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(InvoiceActionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
