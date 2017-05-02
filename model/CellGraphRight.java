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
// Koden utføre det samme som når CellGrapfLeft dannes, men CellGrapfRight har annet dimisjon og dimisjonen er (1,19,19)
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
    //teller naboene til en bestemt cell som lever eller ikke lever. 
    // Dette utføres ved at den vil starte med ei celle som for eksempel har posisjonen 12,9,. Koden vil da ta koordinatene den fikk og 
    //starte med 12-1 og 9-1.(i vil gjelde for 12 og n vil gjelde for 9)
    // også vil den jobbe seg opp til 12+1, 9+1. Den vil unngå n=0 og i= 0 fordi dette er den opprinlige cellen, siden programmeret skal 
    //representere 3d modell så har vi en bestemt tilfelle når vi kommer utenfor lengeden til tabellen.
    // Hvis y + n er større enn høyden - 1 og hvis top grafen's (x + i, siste element i høyden) hvis denne lever så telles den som nabo, 
    //og hvis x + i er større enn bredden
    // så bruker vi høyre graf sin (y+n, siste element i høyden) sjekker vi også om denne lever og om den lever teller vi den som nabo.
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
                   // vil sjekke left grafens (bredde - 1, x + n) hvis y + n er større enn høyde - 1.
                   else if((y+n>this.getH()-1))
                    {
                        if(cg3d.getCGL().table.get(cg3d.getCGL().getW()-1).get(x+i).isAlive())
                        {
                           neighbours++;
                        }
                    }
                    // vil sjekke top grafens bredde -1, y+n, dette gjør den hvis x+i er større en bredden -1.
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
