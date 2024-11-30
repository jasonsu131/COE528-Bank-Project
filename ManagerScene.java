/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package coe528.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.*;

/**
 *
 * @author jmsu
 */
public class ManagerScene extends Application {
    private String username, password, role;

    private Stage stage;

    private Button logout;
    private Manager manager;
    private Scene managerScene;
    private TilePane t2;
    
    public ManagerScene(Manager manager){
        this.manager = manager;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //start method for managerscene
        stage = primaryStage;
        stage.setTitle("Manager Screen");

        manager = new Manager();

        managerScene = createManagerScene();

        stage.setScene(managerScene);
        stage.show();

    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
   
    public Scene createManagerScene() {

        // create elements to be used
        t2 = new TilePane();
        logout = new Button("Logout");
        Button add = new Button("Add");
        Button delete = new Button("Delete");
        TextField user = new TextField("Username");
        TextField pass = new TextField("Password");
        
        Label m = new Label("Manager's Account");
        Label i = new Label("");
        
        //set allignment
        logout.setAlignment(Pos.CENTER);
        user.setAlignment(Pos.CENTER);
        pass.setAlignment(Pos.CENTER);
        
        //adds elements to scene
        t2.getChildren().addAll(logout, m, user, pass, add, delete, i);
        username = user.getText();

        // action events
        //Logout
        EventHandler<ActionEvent> logoutEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                switchToLoginScene();//switch back to login               
            }
        };

        //Add user
        EventHandler<ActionEvent> addEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(manager.add(user.getText(), pass.getText()) == true){
                    //if new user is successfully added
                    i.setText("New Customer Added");
                }
                else{
                    //if username is not added
                    i.setText("Username Already Exists");
                }
            }
        };

        //Delete user
        EventHandler<ActionEvent> deleteEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(manager.delete(user.getText()) == true){
                    //if customer is successfully deleted
                    i.setText("Customer Deleted");
                }
                else{
                    //if customer file is not found
                    i.setText("Customer Not Found");
                }
            }
        };

        // when button is pressed
        logout.setOnAction(logoutEvent);
        add.setOnAction(addEvent);
        delete.setOnAction(deleteEvent);

        Scene managerScene = new Scene(t2, 500, 150);
        return managerScene;
    }
    
    private void switchToLoginScene() {
        //switch back to login scene by creating and setting new stage
        Bank loginScene = new Bank();
        loginScene.setStage(stage);
        stage.setScene(loginScene.createLoginScene());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
