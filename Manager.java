/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
/**
 *
 * @author jmsu
 */
public class Manager extends User{
    
    private String path;
    
    @Override
    public boolean login(String username, String password, String role){
        //authorizes login for manager
        if(username.equals("admin") && password.equals("admin") && role.equals("manager")){
            return true;
        }
        return false;
    }
    
    public boolean add(String username, String password){
        //method for adding new customer file        
        //Checking if a customer with username already exists and creatin new file   
        path = "/home/student2/jmsu/coe528/project/Bank/src/coe528/project/" + username;
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                Customer customer = new Customer();
                
                //set customer attributes
                customer.setUsername(username);
                customer.setPassword(password);
                customer.setRole("customer");
                
                //Add password and and starting balance of $100
                try {
                    FileWriter myWriter = new FileWriter(path);
                    myWriter.write(password);
                    myWriter.write("\n100.0");
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                    return true;
                }catch (IOException e) {
                    System.out.println("An error occurred.");
                }
            } 
            
            else {
                System.out.println("File already exists.");
            }
        
        }catch (IOException e) {
            System.out.println("An error occurred.");
        }             
        return false;
    }
    
    public boolean delete(String username){
        //method to delete customer files from directory
        //So project files can't be deleted
        if(username.equals("Bank.java") || 
           username.equals("Customer.java") ||      
           username.equals("CustomerScene.java") ||       
           username.equals("Manager.java") ||       
           username.equals("ManagerScene.java") ||   
           username.equals("User.java") ||   
           username.equals("admin")){
           
            return false;
        }
        
        path = "/home/student2/jmsu/coe528/project/Bank/src/coe528/project/" + username;
        File file = new File(path); 
        if (file.delete()) { 
          System.out.println("Deleted the file: " + file.getName());
          return true;
        } 
        else {
          System.out.println("Failed to delete the file.");
        }
        return false;
    }
}
