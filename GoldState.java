/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author jmsu
 */
public class GoldState extends LevelState{
    
    //sets level and fee for gold
    @Override
    public void setLevel(){
        this.level = "Gold";
        this.fee = 10;
    }
}
