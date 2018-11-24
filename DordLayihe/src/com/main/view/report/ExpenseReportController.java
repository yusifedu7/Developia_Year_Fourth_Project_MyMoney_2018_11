
package com.main.view.report;

import com.main.db.DB;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ExpenseReportController implements Initializable {

   @FXML
        Label totalAmountLabel;
        
    @FXML
    private DatePicker endDateDP;

    @FXML
    private TableColumn<ExpenseReportBean, Integer> idCol;

    @FXML
    private TableView<ExpenseReportBean> expenseReportTable;

    @FXML
    private TableColumn<ExpenseReportBean, Double> amountCol;

    @FXML
    private TableColumn<ExpenseReportBean, LocalDate> dateCol;

    @FXML
    private TableColumn<ExpenseReportBean, String> noteCol;

    @FXML
    private Label rowCountLabel;

    @FXML
    private TableColumn<ExpenseReportBean, String> categoryCol;

    @FXML
    private DatePicker beginDateDP;

    @FXML
    private Button filterButton;

    
    private DB db=DB.getInstange();
    
    @FXML
    void filterButtonPressed(ActionEvent event) {

        
        
        LocalDate begin=beginDateDP.getValue();
        
        if(begin==null){
            loadTable("");
        }else{
            
               LocalDate end=endDateDP.getValue();
            
               if(end==null){
                       loadTable("");
               }else{
                   
                    loadTable(" where date between '"+begin.toString()+"' and '"+end.toString()+"' ");
                       
               }
        }
        
        
     
        
        
        
        
    }
    
    
    public void loadTable(String condition){
         expenseReportTable.setItems(db.getExpenses(condition));
         
         rowCountLabel.setText(""+expenseReportTable.getItems().size());
         
         
         
         double total=0;
         
         for (int i = 0; i < expenseReportTable.getItems().size(); i++) {
            
             total+=expenseReportTable.getItems().get(i).getAmount();
             
             
        }
         totalAmountLabel.setText(String.valueOf(total));
         
         
    }

    @FXML
    void expenseReportTablePressed(MouseEvent event) {

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
           noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
              amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
                 categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
                    dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
    }    
    
}
