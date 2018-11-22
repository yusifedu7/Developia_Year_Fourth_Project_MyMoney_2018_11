 
package com.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 
public class MainClass extends Application{

    @Override
    public void start(Stage pS) throws Exception {
        
         
        
        try{
            
            FXMLLoader loader=new FXMLLoader(getClass().getResource("view/Login.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);
            pS.setScene(scene);
            pS.show();
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
      }
    
    
    public static void main(String[] args) {
        
        
        launch(args);
        
    }
}
