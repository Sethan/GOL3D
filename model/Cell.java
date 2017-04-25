/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.shape.Box;

/**
 *
 * @author lars
 */
public class Cell extends Box {
    private boolean alive;
    private int loc1;
    private int loc2;
    public Cell(double w, double h, double d, int loc1, int loc2)
    {
        super(w,h,d);
        this.alive=false;
        this.loc1=loc1;
        this.loc2=loc2;
    }
    
    public void changeState()
    {
        this.alive=!this.alive;
    }
    public boolean isAlive()
    {
        return this.alive;
    }
    public String getLoc()
    {
        return this.loc1+" "+this.loc2;
    }
}
