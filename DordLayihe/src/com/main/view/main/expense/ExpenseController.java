/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.view.main.expense;

import com.main.db.DB;
import com.main.view.main.income.IncomeController;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yusif
 */
public class ExpenseController implements Initializable {

private Stage thisStage;
private String expenseCategory;

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
            expenseCategoryLabel.setText(expenseCategory);
    }
    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    
 private void msg(String s) {
      
    warningsLabel.setText(s);
    
    }
   @FXML
   Label warningsLabel;
    @FXML
    private TextField noteTF;

   

    @FXML
    private DatePicker dateDP;

    @FXML
    private TextField amountTF;

    @FXML
    private Button confirmButton;
private DB db=DB.getInstange();
    @FXML
    void confirmButtonPressed(ActionEvent event) {

        
        String note=noteTF.getText();
        if(note.equals("")){
            msg("Qeydi yazmaq lazımdır");
        }
        else{
            String amountString=amountTF.getText();
            if(amountString.equals("")){
                msg("Məbləği boş qoymaq olmaz");
            }else{
                  double amount=Double.parseDouble(amountString);
                  
                  String category=expenseCategoryLabel.getText();
                  
                  LocalDate date=dateDP.getValue();
                  
                  if(date==null){
                      msg("Tarix yazılmalıdır ");
                  }else{
                    if(  db.iud("insert into expense (amount,note,category,date) values ('"+amount+"','"+note+"','"+category+"','"+date.toString()+"')")){
                        
                        
                        try {
                            msg("Uğurlu");
                            
                            
                            double balance=Double.parseDouble(db.getColumnRowsFromTable("info", "v", "where k='balance'").get(0));
                            balance-=amount;
                            
                            db.iud("update info set v='"+balance+"' where k='balance'");
                            
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(IncomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        thisStage.close();
                    }
                    else{
                          msg("Uğursuz");
                    }
                      
                      
                  }
                  
                  
                  
            }
            
          
        }
        
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
  

     

    @FXML
    private Label expenseCategoryLabel;

    public Label getExpenseCategoryLabel() {
        return expenseCategoryLabel;
    }

    public void setExpenseCategoryLabel(Label expenseCategoryLabel) {
        this.expenseCategoryLabel = expenseCategoryLabel;
    }
 
 

     

    
    
    
}
