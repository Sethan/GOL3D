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

    public Cell(double w, double h, double d)
    {
        super(w,h,d);
        this.alive=false;
    }
    
    public void changeState()
    {
        this.alive=!this.alive;
    }
    public boolean isAlive()
    {
        return this.alive;
    }
}
