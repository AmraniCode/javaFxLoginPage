/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginpage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tbza.org.datamanagement.IDataManager;
import tbza.org.datamanagement.SqlLiteManager;
import tbza.org.models.User;

/**
 *
 * @author PC-DEV-CRI
 */
public class LoginPage extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               IDataManager datamanager = new SqlLiteManager();
               
                //check connection
               boolean isconnected = datamanager.connectToData("");
               
               if(isconnected){
                   User user = new User();
                   user.setUserName("kira");
                   user.setPassword("root");

                   datamanager.insertUser(user);
                   datamanager.checkUserExist(null);
               }else{
                   System.out.println("No connection established");
               }
               
               
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
