/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ZuraH
 */
public class CellGraphRight extends CellGraph {
    public CellGraphRight(int x, int y)
    {
        
        super(x,y);
        for(int i=0; i < x; i++)
        {
            this.table.add(new ArrayList<Cell>());
            for(int n=0; n < y; n++)
            {
               this.table.get(i).add(new Cell(1,19,19));
            }
        }
    }
    @Override
    public int countNeighbours(int x, int y, CellGraph3D cg3d)
    {
        int neighbours=0;
            
        for(int i =-1; i<2; i++)
        {
            for(int n=-1; n<2; n++)
            {
                if(!(n==0&&i==0))
                {
                    if((x+i<0)||(y+n<0))
                    {
                        
                    }
                    else if((y+n>this.getH()-1)&&(x+i>this.getW()-1))
                    {
                        
                    }
                   else if((y+n>this.getH()-1))
                    {
                        if(cg3d.getCGL().table.get(cg3d.getCGL().getW()-1).get(x+i).isAlive())
                        {
                           neighbours++;
                        }
                    }
                    
                    else if((x+i>this.getW()-1))
                    {
                        if(cg3d.getCGT().table.get(cg3d.getCGT().getW()-1).get(y+n).isAlive())
                        {
                            neighbours++;
                        }
                    }
                    
                    else if(this.table.get(x+i).get(y+n).isAlive())
                    {
                        neighbours++;
                        
                    }
                }
            }
        }
        return neighbours;
    }
}
