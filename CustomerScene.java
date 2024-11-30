/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package coe528.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author jmsu
 */
public class CustomerScene extends Application {
    //private String username, password, role;
                
    private Stage stage;
    private Button logout;
    private Button deposit;
    private Button withdraw;
    private Button purchase;
    private Customer customer;
    private Scene customerScene;
    private TilePane t3;
    
    private String accountInfo;
    private String balanceInfo;
        
    public CustomerScene(Customer customer){
        this.customer = customer;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //start method foor customerscene
        stage = primaryStage;
        stage.setTitle("Customer Screen");

        customer = new Customer();

        customerScene = createCustomerScene();

        stage.setScene(customerScene);
        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public Scene createCustomerScene() {
        // create elements
        t3 = new TilePane();
        logout = new Button("Logout");
        deposit = new Button("Deposit");
        withdraw = new Button("withdraw");
        purchase = new Button("Online Purchase");

        TextField amount = new TextField("Amount");
        
        
        // set alignment of text
        logout.setAlignment(Pos.CENTER);
        deposit.setAlignment(Pos.CENTER);
        withdraw.setAlignment(Pos.CENTER);
        purchase.setAlignment(Pos.CENTER);
        amount.setAlignment(Pos.CENTER);

        System.out.println("username: "+customer.getUsername());
        
        //set strings for interface labels
        accountInfo = customer.getUsername() + "'s " + customer.getLevel() + " Account";
        balanceInfo = "Balance: $" + customer.getBalance();
        
        // create a label using strings created
        Label account = new Label(accountInfo);
        Label balance = new Label(balanceInfo);
        Label i = new Label("");
        
        //display elements
        t3.getChildren().addAll(logout, account, balance, 
   amount,deposit, withdraw, purchase, i);
        
       
        // action event
        //Logout
        EventHandler<ActionEvent> logoutEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                switchToLoginScene();//switch back to login               
            }
        };
        
        //Deposit
        EventHandler<ActionEvent> depositEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                double amountDbl = Double.parseDouble(amount.getText());
                if(customer.deposit(amountDbl) == true){
                    //if deposit is successful
                    i.setText("Deposit Successful");
                    
                    //update balance on gui
                    balanceInfo = "Balance: $" + customer.getBalance();
                    balance.setText(balanceInfo);
                    accountInfo = customer.getUsername() + "'s " + customer.getLevel() + " Account";
                    account.setText(accountInfo);
                }
                else{
                    i.setText("Deposit Unuccessful");
                }
            }
        };
        
        //withdraw
        EventHandler<ActionEvent> withdrawEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                double amountDbl = Double.parseDouble(amount.getText());
                if(customer.withdraw(amountDbl) == true){
                    //if withdraw is successful
                    i.setText("Withdraw Successful");
                    
                    //update balance on gui
                    balanceInfo = "Balance: $" + customer.getBalance();
                    balance.setText(balanceInfo);
                    accountInfo = customer.getUsername() + "'s " + customer.getLevel() + " Account";
                    account.setText(accountInfo);
                }
                else{
                    i.setText("Withdraw Unuccessful");
                }
            }
        };

        //online purchase
        EventHandler<ActionEvent> purchaseEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                double amountDbl = Double.parseDouble(amount.getText());
                amountDbl += customer.getFee();//Purchase fee is added to the amount
                
                //purchase amount must be $50+
                if(amountDbl < 50){
                    i.setText("Purchase must be $50 or more");
                }
                
                else if(customer.withdraw(amountDbl) == true){
                    //if withdraw is successful
                    i.setText("Purchase Successful");
                    
                    //update balance on gui
                    balanceInfo = "Balance: $" + customer.getBalance();
                    balance.setText(balanceInfo);
                    accountInfo = customer.getUsername() + "'s " + customer.getLevel() + " Account";
                    account.setText(accountInfo);
                }
                else{
                    i.setText("Purchase Unuccessful");
                }
            }
        };
        
        // when button is pressed
        logout.setOnAction(logoutEvent);
        deposit.setOnAction(depositEvent);
        withdraw.setOnAction(withdrawEvent);
        purchase.setOnAction(purchaseEvent);


        Scene customerScene = new Scene(t3, 500, 150);
        return customerScene;
    }
    
    private void switchToLoginScene() {
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
