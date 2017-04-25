/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author lars
 */
public class CellGraph 
{
    public ArrayList<ArrayList<Cell>> table;
    private final int cellWidth;
    private final int cellHeight;
    public Boolean[][] copy;
          
    public CellGraph(int x, int y)
    {    
        this.cellWidth=x;
        this.cellHeight=y;
        this.table=new ArrayList<ArrayList<Cell>>();
    }
    
    public int getW()
    {
        return this.cellWidth;
    }
    
    public int getH()
    {
        return this.cellHeight;
    }

    public Boolean[][] getCopy()
    {
        return this.copy;
    }
    
    public void setCopy(Boolean[][] copy)
    {
        this.copy=copy;
    }
    public int getAlive()
    {
        int alive = 0;
        for(int i=0; i< this.getW();i++)
        {
            for(int n=0; n<this.getH(); n++)
            {
                if(this.table.get(i).get(n).isAlive())
                {
                    alive++;
                }
            }
        }
        return alive;
    }
    public void decide(int x , int y, CellGraph3D cg3d)
    {
        int neighbours=this.countNeighbours(x, y, cg3d);

        if(this.table.get(x).get(y).isAlive())
        {
            if(neighbours>3||neighbours<2)
            {
                this.copy[x][y]=!this.copy[x][y];//dreper cellen
              
            }
        }
        else
        {
            if(neighbours==3)
            {
                this.copy[x][y]=!this.copy[x][y];//gjennopliver celler
            }
        }
    }
    
    public int countNeighbours(int x, int y, CellGraph3D test)
    {
        
        return -1;
        //en generisk ubrukelig metode

    }
    
    public void resetGraph()
    {
        for(int i=0; i < this.cellWidth; i++)
        {
            for(int n=0; n < this.cellHeight; n++)
            {
                if(this.table.get(i).get(n).isAlive())
                {
                    this.table.get(i).get(n).changeState();
                }
            }
        }
    }
}
