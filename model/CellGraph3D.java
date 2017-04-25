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
    public CellGraph getCGR()
    {
        return this.right;
    }
    public CellGraph getCGT()
    {
        return this.top;
    }
    public CellGraph getCGL()
    {
        return this.left;
    }
    public int getX()
    {
        return this.xDimension;
    }
    public int getY()
    {
        return this.yDimension;
    }
    public int getZ()
    {
        return this.zDimension;
    }
}
