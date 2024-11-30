/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author jmsu
 */
public abstract class LevelState {
    
    //abstract classs for different level states
    protected String level;
    protected double fee;    
    
    public abstract void setLevel();
    
    public String getLevel(){
        return level;
    }
    
    public double getFee(){
        return fee;
    }
}
