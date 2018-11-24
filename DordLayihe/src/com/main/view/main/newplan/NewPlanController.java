 
package com.main.view.main.newplan;

import com.main.db.DB;
import com.main.view.main.MainController;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
 
public class NewPlanController implements Initializable {
private Stage thisStage;

    public Stage getThisStage() {
        return thisStage;
    }

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    
    public MainController controller;
    
    @FXML
    private Label warningsLabel;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private DatePicker endDateDP;

    @FXML
    private TextField planNameTF;

    @FXML
    private Label currentBalanceLabel;

    @FXML
    private ListView<String> expenseCategoryList;

    @FXML
    private ListView<String> expenseAmountList;

    @FXML
    private DatePicker beginDateDP;
    private DB db = DB.getInstange();
    @FXML
    private TextField amountTF;

    @FXML
    private Button confirmButton;

    @FXML
    void expenseCategoryListPressed(MouseEvent event) {
amountTF.setText("");
    }

    @FXML
    void expenseAmountListPressed(MouseEvent event) {

    }

    @FXML
    void amountTFReleased(KeyEvent event) {

        
        //if(event.getCode()==KeyCode.ENTER)
        String amount = amountTF.getText();

        String selected = expenseCategoryList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            msg("Siyahıdan seçim edilməlidir");
        } else {
            
            int selectedIndex = expenseCategoryList.getSelectionModel().getSelectedIndex();

            expenseAmountList.getItems().set(selectedIndex, amount);
            
            calculateTotalAmount();
            
//replace
            
        }

    }

    @FXML
    void confirmButtonPressed(ActionEvent event) {

        String name=planNameTF.getText();
        
        if(name.trim().equals("")){
            msg("Planın adını boş qoymaq olmaz");
        }else{
            
            LocalDate beginDate=beginDateDP.getValue();
               LocalDate endDate=endDateDP.getValue();
            
               if(beginDate==null || endDate == null){
                   msg("Tarixləri boş qoymaq olmaz");
               }else{
                   
                 // burada proses davam edecek
                  
                   
                   if(totalAmount==0){
                       msg("Minimum 1 ədəd olmalıdır");
                   }else{ 
                       db.iud("insert into plans (name,beginDate,endDate,totalAmount) values ('"+name+"','"+beginDate.toString()+"','"+endDate.toString()+"','"+totalAmount+"')");
                       controller.loadAllPlans();
                      
                       int max=db.getMaxColumnValueFromTable("plans", "id");
                       
                       
                       for (int i = 0; i < expenseAmountList.getItems().size(); i++) {
                             db.iud("insert into plandetails (planID,category,amount) values ("+max+",'"+expenseCategoryList.getItems().get(i)+"','"+expenseAmountList.getItems().get(i)+"')");
                       }
                       
                       
                       
                        msg("Ugurlu");
                         thisStage.close();
                       
                       
                       
                       
                       
                   }
                   
                   
                   
                   
               }
            
            
            
        }
                
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        expenseCategoryList.getItems().addAll(db.getColumnRowsFromTable("expense_category", "category", ""));

        for (int i = 0; i < expenseCategoryList.getItems().size(); i++) {
            expenseAmountList.getItems().add("0");
        }

        currentBalanceLabel.setText(db.getColumnRowsFromTable("info", "v", "where k='balance'").get(0));
        
        
        
    }

    private void msg(String s) {
        warningsLabel.setText(s);
    }

    private void calculateTotalAmount() {
     
    double totalAmount=0;
        for (int i = 0; i < expenseAmountList.getItems().size()
                ; i++) {
            
            
            totalAmount+=Double.parseDouble( expenseAmountList.getItems().get(i));
            
        }
        this.totalAmount=totalAmount;
        totalAmountLabel.setText(String.valueOf(totalAmount));
    
    }
    
    private double totalAmount;

}
