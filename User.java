/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author jmsu
 * 
 * OVERVIEW: 
 *      Users are mutable, and serves as an abstract blueprint for user-related
 *      functions such as login and logout. It encapsulates basic user attributes 
 *      such as username, password, and role.
 * 
 * ABSTRACTION FUNCTION:
 *      username: Unique identifier for user identification.
 *      password: Confidential authentication credential.
 *      role: Specifies user's access permissions.
 *      
 *      AF(c) = user u such that
// *          u.username = c.getUsername();
 *          u.password = c.getPassword();
 *          u.role = c.getRole();
 * 
 * REP INVARIANT:
 *      u.username != null || u.username is not an empty string;
 *      u.password != null || u.password is not an empty string;;
 *      u.role = "customer || u.role = "manager";
 */

public abstract class User{
    
    protected String username, password, role;
    
    
    public abstract boolean login(String username, String password, String role);
    
    public boolean logut(){
        // Effect: Logs out the user.
        // Modifies: None.
        // Requires: None.
        return true;
    }
    
    public void setUsername(String username){
        // Effect: Sets the username for the user.
        // Modifies: this.username
        // Requires: username is not null or empty.
        this.username = username;
    }
    
    public void setPassword(String password){
        // Effect: Sets the password for the user.
        // Modifies: this.password
        // Requires: password is not null or empty.
        this.password = password;
    }
    
    public void setRole(String role){
        // Effect: Sets the role for the user.
        // Modifies: this.role
        // Requires: role is not null or empty.
        this.role = role;
    }
    
    public String getUsername(){
        // Effect: Retrieves the username of the user.
        // Modifies: None.
        // Requires: None.
        return username;
    }
    
    public String getPassword(){
        // Effect: Retrieves the password of the user.
        // Modifies: None.
        // Requires: None.
        return password;
    }
    
    public String getRole(){
        // Effect: Retrieves the role of the user.
        // Modifies: None.
        // Requires: None.
        return role;
    }
    
    @Override
    public String toString() {
        // Abstraction Function:
        // Returns a string representation of the user object,
        // including username, password, and role.
        return "User{" +
                "username ='" + username + '\'' +
                ", password ='" + password + '\'' +
                ", role ='" + role + '\'' +
                '}';
    }
    
    public boolean repOk() {
        // Representation Invariant:
        // 1. username, password, and role are not null.
        // 2. username is not an empty string.
        // 3. password is not an empty string.
        // 4. role is either "customer" or "manager".
        return username != null && !username.isEmpty() &&
                password != null && !password.isEmpty() && 
                (role.equals("customer") || role.equals("customer"));
    }
}
