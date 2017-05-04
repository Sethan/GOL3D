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
//Celle extender box og arver alle verdiene box har I tilegg hare n celle verdien alive.
public class Cell extends Box {
    private boolean alive;

    public Cell(double w, double h, double d)
    {
        super(w,h,d);
        this.alive=false;
    }
    //Metoden changestate endrer cellens tilstand
    public void changeState()
    {
        this.alive=!this.alive;
    }
    //Metoden isAlive returneres om cellen lever.
    public boolean isAlive()
    {
        return this.alive;
    }
}
