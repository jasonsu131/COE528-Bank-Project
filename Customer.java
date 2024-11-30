/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
/**
 *
 * @author jmsu
 */
public class Customer extends User {
    
    private double balance, amount, fee;
    private boolean dep, with;
    private String level, path;
    private Path path2;
    
    public Customer(){
        //this.balance = getBalance();
        //level = new LevelState();
        setLevel();
        this.level = getLevel();
    }
    
    @Override
    public boolean login(String username, String password, String role){
        
        //check if there is a file with usernname
        path = "/home/student2/jmsu/coe528/project/Bank/src/coe528/project/" + username;
        path2 = Paths.get("/home/student2/jmsu/coe528/project/Bank/src/coe528/project/" + username);
        
        this.password = readFile(1, path);
        
        //insert customer login
        if(this.password.equals(password)){
            System.out.println("Logged into " + username + "'s account");
            //setUsername(username);
            
            return true;
        }
        return false;
    }
    
    
    public String readFile(int line,  String path){
        //read customer file and return specific line as string
        try {
            File customerFile = new File(path);
            Scanner reader = new Scanner(customerFile);
            
            for(int i = 1; i <= line; i++) {
                String data = reader.nextLine();
                System.out.println("read: " + data);
                
                if(i == line)
                    return data; 
            }
        }
        //if that file is not found
        catch (FileNotFoundException e) {
            System.out.println("Customer not found");
            //e.printStackTrace();
            return "ERROR";
        }
        return "ERROR";
    }
    
    public void writeFile(String oldLine, String newLine, Path path2){
        //replace an oldline with a new line in the string
        try{
            //saves all lines to a list then swaps strings if oldline is found
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path2, StandardCharsets.UTF_8));
            System.out.println("oldline: "+oldLine);
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(oldLine)) {
                    fileContent.set(i, newLine);
                    break;
                }
            }
            
            Files.write(path2, fileContent, StandardCharsets.UTF_8);
        }
        catch(IOException e){
            System.out.println("File not found");
        }
    }
    
    boolean deposit(double amount){
        //checks if a valid deposit can be made
        dep = false;
        if(amount > 0){
            dep = true;
            this.amount = amount;
            setBalance();
            return true;
        }
        else{
            return false;
        }    
    }
    
    boolean withdraw(double amount){       
        //checks if a valid withdraw can be made
        with = false;
        if(amount <= balance && amount > 0){
            with = true;
            this.amount = amount;
            setBalance();
            return true;
        }
        else{
            return false;
        }    
    }
    
    public void setBalance(){
        //changes balance whether deposit or withdraw are true
        double temp = balance;
        if(with == true){
            with = false;
            balance = balance - amount;
        }
        if(dep == true){
            dep = false;
            balance = balance + amount;
        }
        writeFile(String.valueOf(temp) , String.valueOf(balance), path2);
        setLevel();//Adjust account level after balance is changed
    }
    
    public double getBalance(){
        //returns balance from file
        balance = Double.parseDouble(readFile(2, path)); 
        System.out.println(path);
        return balance;
    }
        
    public void setLevel(){
        //sets levelState depending on balance
        if(balance < 10000){
            SilverState state = new SilverState();
            state.setLevel();
            this.level = state.getLevel();
            this.fee = state.getFee();
        }
        else if(balance < 20000){
            GoldState state = new GoldState();
            state.setLevel();
            this.level = state.getLevel();
            this.fee = state.getFee();
        }
        else{
            PlatinumState state = new PlatinumState();
            state.setLevel();
            this.level = state.getLevel();
            this.fee = state.getFee();
        }
    }
    
    public String getLevel(){
        return level;
    }
    
    public double getFee(){
        return fee;
    }
    
    public boolean purchase(double price){
        //purchase must be $50+
        if(price < 50){
            return false;
        }
        //performs a withdraw with added purchase fee
        withdraw(balance + fee);
        return true;
    }
    
}
