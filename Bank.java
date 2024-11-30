// Java program to create a textfield with a initial text and center alignment of text
// and add a event handler to handle the event of textfield
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

public class Bank extends Application {

    private String username, password, role;
    private Stage stage;

    //login
    private Scene loginScene;
    private TilePane t1;

    //manager
    private Manager manager;
    private Scene managerScene;

    //customer
    private Customer customer;
    private Scene customerScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        stage = primaryStage;
        stage.setTitle("Login");

        manager = new Manager();
        customer = new Customer();

        loginScene = createLoginScene();

        stage.setScene(loginScene);
        stage.show();

    }

    public Scene createLoginScene() {
        stage.setTitle("Login");

        // create a tile pane
        t1 = new TilePane();

        // create a textfield
        TextField user = new TextField();
        TextField pass = new TextField();
        TextField rol = new TextField();
        Button login = new Button("Login");

        // set alignment of text
        user.setAlignment(Pos.CENTER);
        pass.setAlignment(Pos.CENTER);
        rol.setAlignment(Pos.CENTER);
        login.setAlignment(Pos.CENTER);

        // create a label
        Label u = new Label("Username:");
        Label p = new Label("Password:");
        Label r = new Label("Role:");
        Label i = new Label("");

        // add elements
        t1.getChildren().addAll(u, user, p, pass, r, rol, login, i);

        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                username = user.getText();
                password = pass.getText();
                role = rol.getText();

                //manager login
                if (role.equals("manager")) {
                    manager = new Manager();
                    if (manager.login(username, password, role) == true) {
                        manager.setUsername(username);
                        switchToManagerScene(manager);//change scene
                    } else {
                        i.setText("incorrect username or password");
                    }
                } 
                
                //customer login
                else if (role.equals("customer")) {
                    customer = new Customer();
                    if (customer.login(username, password, role) == true) {
                        //System.out.println(customer.getUsername());
                        customer.setUsername(username);
                        switchToCustomerScene(customer);//change scene
                    } else {
                        i.setText("incorrect username or password");
                    }
                } else {
                    i.setText("incorrect role (manager or customer)");
                }
            }
        };

        // when enter is pressed
        login.setOnAction(event);

        // create a scene
        Scene loginScene = new Scene(t1, 400, 150);
        return loginScene;
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void switchToManagerScene(Manager manager) {
        //Creates and switches to manager scene
        ManagerScene managerScene = new ManagerScene(manager);
        managerScene.setStage(stage);
        stage.setScene(managerScene.createManagerScene());
    }

    private void switchToCustomerScene(Customer customer) {
        //Creates and switches to customer scene
        CustomerScene customerScene = new CustomerScene(customer);
        customerScene.setStage(stage);
        stage.setScene(customerScene.createCustomerScene());
    }

    public static void main(String args[]) {
        // launch the application
        launch(args);
    }

}
