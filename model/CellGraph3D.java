/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author lars
 */

// Her vil dimisjonene til kuben deklareres.
public class CellGraph3D {
    
    private final CellGraphRight right;
    private final CellGraphTop top;
    private final CellGraphLeft left;
    private final int xDimension;
    private final int yDimension;
    private final int zDimension;
    
    public CellGraph3D(int x, int y, int z)
    {
        this.right=new CellGraphRight(y,z);
        this.left=new CellGraphLeft(x,y);
        this.top=new CellGraphTop(x,z);
        this.xDimension=x;
        this.yDimension=y;
        this.zDimension=z;
    }
    // Returnerer CellgrafRight
    public CellGraph getCGR()
    {
        return this.right;
    }
    // Returnerer CellgrafTop
    public CellGraph getCGT()
    {
        return this.top;
    }
    // Returnerer CellgrafLeft
    public CellGraph getCGL()
    {
        return this.left;
    }
    // Returnerer x dimijsonen
    public int getX()
    {
        return this.xDimension;
    }
    // Returnerer y dimijsonen
    public int getY()
    {
        return this.yDimension;
    }
    // Returnerer z dimijsonen
    public int getZ()
    {
        return this.zDimension;
    }
}
