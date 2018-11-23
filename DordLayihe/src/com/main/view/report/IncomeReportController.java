/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * FXML Controller class
 *
 * @author Yusif
 */
public class IncomeReportController implements Initializable {

   
    @FXML
    private DatePicker endDateDP;

    @FXML
    private TableColumn<IncomeReportBean, Integer> idCol;

    @FXML
    private TableView<IncomeReportBean> IncomeReportTable;

    @FXML
    private TableColumn<IncomeReportBean, Double> amountCol;

    @FXML
    private TableColumn<IncomeReportBean, LocalDate> dateCol;

    @FXML
    private TableColumn<IncomeReportBean, String> noteCol;

    @FXML
    private Label rowCountLabel;

    @FXML
    private TableColumn<IncomeReportBean, String> categoryCol;

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
         IncomeReportTable.setItems(db.getIncomes(condition));
         
         rowCountLabel.setText(""+IncomeReportTable.getItems().size());
         
    }

    @FXML
    void IncomeReportTablePressed(MouseEvent event) {

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
