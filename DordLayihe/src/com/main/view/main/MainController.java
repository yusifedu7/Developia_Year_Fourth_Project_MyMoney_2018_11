package com.main.view.main;

import com.main.db.DB;
import com.main.view.main.expense.ExpenseController;
import com.main.view.main.income.IncomeController;
import com.main.view.main.newplan.NewPlanController;
import com.sun.javafx.scene.control.skin.CustomColorDialog;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
    @FXML
    VBox   rightVBox;
    
    
    @FXML
    PieChart expenseStatuses;
    @FXML
    Label warningsLabel;
    
    @FXML
    private Button expenseEditButton;
    
    @FXML
    private Button deleteButton;
    
    @FXML
    private Button planDeleteButton;
   
    
    @FXML
    public void expensePressed(MouseEvent event){
        
     
            
            
                
        ObservableList<PieChart.Data> list=FXCollections.observableArrayList();
        
        
        ArrayList<String> expenseCategory=db.getColumnRowsFromTable("expense_category", "category", "");
        
        for (int i = 0; i < expenseCategory.size(); i++) {
            list.add(new PieChart.Data(expenseCategory.get(i), db.getSumColumnValuesFromTable("expense", "amount", "where category='"+expenseCategory.get(i)+"'")));
        }
        
           
           expenseStatuses.setData(list);
       expenseStatuses.setTitle("Bütün xərclərin kateqoriyalara görə faizləri");
        
        
         
           
            
   
     
        
        
        
    }
    
    
    @FXML
    public void incomeReportButtonPressed(ActionEvent event){
         try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/main/view/report/IncomeReport.fxml"));
                stage.initModality(Modality.APPLICATION_MODAL);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
               
                
                
                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }  @FXML
    public void expenseReportButtonPressed(ActionEvent event){
         try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/main/view/report/ExpenseReport.fxml"));
                stage.initModality(Modality.APPLICATION_MODAL);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
               
                
                
                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    
    
    @FXML
    private Button newPlanRegisterButton;
    
    @FXML
    private ListView<String> incomeList;
    
    @FXML
    private TextField planTF;
    
    @FXML
    private Button expenseDeleteButton;
    
    @FXML
    private TextField expenseTF;
    
    @FXML
    private Button newIncomeRegisterButton;
    
    @FXML
    private Button planNewButton;
    
    @FXML
    private Button expenseNewButton;
    
    @FXML
    private Button editButton;
    
    @FXML
    private Button newButton;
    
    @FXML
    private Button planEditButton;
    
    @FXML
    private ListView<String> expenseList;
    
    @FXML
    private ListView<String> planList;
    
    @FXML
    private TextField incomeTF;
    
    @FXML
    private Button newExpenseRegisterButton;
    
    @FXML
    void incomeListPressed(MouseEvent event) {
        String selected=incomeList.getSelectionModel().getSelectedItem();
        if(selected==null){
            msg("Siyahıdan seçim edilməyib");
        }else{
            
            incomeTF.setText(selected);
            this.selected=selected;
                    
            
        }
        
    }
    
    String selected;
    
    @FXML
    void newButtonPressed(ActionEvent event) {
        String name=incomeTF.getText();
        
        if(name==null){
            name="";
        }else{
            name=name.trim();
        }
        
        
        if(name.equals("")){
            msg("Adı boş qoymaq olmaz");
        }else{
            boolean has=db.hasRowInTableForThisCondition("income_category", " where category='"+name+"'");
            
            if(has){
                msg("Bu artıq var");
            }else{
                db.iud("insert into income_category (category)  values ('"+name+"')   ");
                loadIncomeCategory();
            }
        }
    }
    
    @FXML
    void editButtonPressed(ActionEvent event) {
          String selected = incomeList.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            msg("Siyahıdan seçim edilməyib");
        } else {
           
            String newCategory=incomeTF.getText();
            
            if(newCategory==null){
                newCategory="";
            }else{
                newCategory=newCategory.trim();
            }
            if(newCategory.equals("")){
                msg("Adı boş qoymaq olmaz");
            }else{
                 db.iud("update income_category set category='"+newCategory+"' where category='"+this.selected+"' ");
            loadIncomeCategory();
            }
            
            
            
           
            
        }
    }
    
    @FXML
    void deleteButtonPressed(ActionEvent event) {
          String selected = incomeList.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            msg("Siyahıdan seçim edilməyib");
        } else {
            db.iud("delete from income_category where category='"+selected+"'");
            loadIncomeCategory();
            
        }
    }
    
    @FXML
    void newIncomeRegisterButtonPressed(ActionEvent event) {
        String selected = incomeList.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            msg("Siyahıdan seçim edilməyib");
        } else {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("income/Income.fxml"));
                stage.initModality(Modality.APPLICATION_MODAL);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
                IncomeController c = loader.getController();
                c.setIncomeCategory(selected);
                c.setThisStage(stage);
                c.setMainController(this);
                
                
                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    @FXML
    void expenseListPressed(MouseEvent event) {
         String selected=expenseList.getSelectionModel().getSelectedItem();
        if(selected==null){
            msg("Siyahıdan seçim edilməyib");
        }else{
            
            expenseTF.setText(selected);
            this.selected=selected;
                    
            
        }
    }
    
    @FXML
    void expenseNewButtonPressed(ActionEvent event) {
         String name=expenseTF.getText();
        
        if(name==null){
            name="";
        }else{
            name=name.trim();
        }
        
        
        if(name.equals("")){
            msg("Adı boş qoymaq olmaz");
        }else{
            boolean has=db.hasRowInTableForThisCondition("expense_category", " where category='"+name+"'");
            
            if(has){
                msg("Bu artıq var");
            }else{
                db.iud("insert into expense_category (category)  values ('"+name+"')   ");
                loadExpenseCategory();
            }
        }
    }
    
    @FXML
    void expenseEditButtonPressed(ActionEvent event) {
        String selected = expenseList.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            msg("Siyahıdan seçim edilməyib");
        } else {
           
            String newCategory=expenseTF.getText();
            
            if(newCategory==null){
                newCategory="";
            }else{
                newCategory=newCategory.trim();
            }
            if(newCategory.equals("")){
                msg("Adı boş qoymaq olmaz");
            }else{
                 db.iud("update expense_category set category='"+newCategory+"' where category='"+this.selected+"' ");
            loadExpenseCategory();
            }
            
            
            
           
            
        }
    }
    
    @FXML
    void expenseDeleteButtonPressed(ActionEvent event) {
          String selected = expenseList.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            msg("Siyahıdan seçim edilməyib");
        } else {
            db.iud("delete from expense_category where category='"+selected+"'");
            loadExpenseCategory();
            
        }
    }
    
    @FXML
    void newExpenseRegisterButtonPressed(ActionEvent event) {
        String selected = expenseList.getSelectionModel().getSelectedItem();
        
        if (selected == null) {
            msg("Siyahıdan seçim edilməyib");
        } else {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("expense/Expense.fxml"));
                stage.initModality(Modality.APPLICATION_MODAL);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
                ExpenseController c = loader.getController();
                c.setExpenseCategory(selected);
                c.setThisStage(stage);
                c.setMainController(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @FXML
    void planListPressed(MouseEvent event) {
        String selected=planList.getSelectionModel().getSelectedItem();
        
        
        if(selected==null){
            msg("Seçilməyib");
        }else{
            int id=Integer.parseInt(db.getColumnSingleRowFromTable("plans", "id", "where name='"+selected+"'"));
            
            ArrayList<String> list=db.getColumnRowsFromTable("plandetails", "category", " where planid="+id+" ");
            
              ArrayList<String> amounts=db.getColumnRowsFromTable("plandetails", "amount", " where planid="+id+" ");
            
            LocalDate beginDate=db.getColumnLocalDateSingleRowFromTable("plans", "beginDate", "where id= "+id+" ");
            
              LocalDate endDate=db.getColumnLocalDateSingleRowFromTable("plans", "endDate", "where id= "+id+" ");
              
              
              
              planExpensesList.getItems().clear();
              
              for (int i = 0; i < list.size(); i++) {
                 HBox hb=new HBox();  
                 
                 
                 
                
                 
                 Label name=new Label(list.get(i)+" : ");
                 name.setStyle("-fx-font-size:20px;-fx-text-fill: rgb(255,0,0);");
               
                 
                    Label amount=new Label("Ayrılmış pul : "+amounts.get(i) );
                    
                    
                  
                     
                    // qayeqoriyanin xerclenen pulu
                    double spended=db.getSumColumnValuesFromTable("expense", "amount", "where (category='"+list.get(i)+"' and (date between '"+beginDate.toString()+"' and '"+endDate.toString()+"'))");
                   // ayrilmis pul
                   
                      Label spendedLabel=new Label("Xərclənən pul : "+spended );
                      
                      
                    double amountDouble=Double.parseDouble(amounts.get(i));
                    
                    double percent=spended/amountDouble;
                   
                  ProgressBar pb=new ProgressBar(percent); 
                  
                  
                  if(percent>1){
                        pb.setStyle("-fx-accent: red");
                    }else{
                        pb.setStyle("-fx-accent: blue");
                  }
                  
                  
                    Label forecast=new Label(list.get(i)+" : ");
                    
                    // 
                  // 10 
                  // 3  
                  // amount
                  
                // 
              long aradaki =ChronoUnit.DAYS.between(beginDate, endDate);
              
                  
                 hb.getChildren().addAll(name,amount,pb,spendedLabel);
                 planExpensesList.getItems().add(hb);
            }
        
            
        }
    }
    
    @FXML
    void planNewButtonPressed(ActionEvent event) {
        
    }
    
    @FXML
    void planEditButtonPressed(ActionEvent event) {
        
    }
    
    @FXML
    void planDeleteButtonPressed(ActionEvent event) {
        
    }
    
    @FXML
    void newPlanRegisterButtonPressed(ActionEvent event) {
         try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("newplan/NewPlan.fxml"));
                stage.initModality(Modality.APPLICATION_MODAL);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
                NewPlanController controller=loader.getController();
                controller.controller=this;
              
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    
    private DB db = DB.getInstange();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshBalance();
        loadIncomeCategory();
        
      
        
        expenseList.getItems().addAll(db.getColumnRowsFromTable("expense_category", "category", ""));
        
        
        
        
        
        
        loadAllPlans();
        
        
    }    
    
    private void msg(String s) {
        
        warningsLabel.setText(s);
        
    }
@FXML
private ListView<HBox> planExpensesList;

    private void loadIncomeCategory() {
        incomeList.getItems().clear();
         incomeList.getItems().addAll(db.getColumnRowsFromTable("income_category", "category", "")); }

    private void loadExpenseCategory() {
      expenseList.getItems().clear();
         expenseList.getItems().addAll(db.getColumnRowsFromTable("expense_category", "category", "")); }

    public  void loadAllPlans() {
     
    // 2018-11-22
       planList.getItems().clear();
         planList.getItems().addAll(db.getColumnRowsFromTable("plans", "name", " where endDate>'"+LocalDate.now().toString()+"'"));
    
    
    }
    @FXML
    Label currentBalanceLabel;
    
    
    
    public void refreshBalance(){
        String b=db.getColumnSingleRowFromTable("info", "v", "where k='balance'");
        currentBalanceLabel.setText(b);
    }
    
    
}
