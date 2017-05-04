/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ZuraH
 */

// Her vil den bruke enten cellgrapfleft,top eller right og 3dcellgrapf til å først lage en ny copy av grafen og deretter endre copyet 
// etter reglene for game of life. 
public class ThreadTasks {
    public static void RunPattern(CellGraph cg, CellGraph3D cg3d)
    {
        cg.setCopy(new Boolean[cg.getW()][cg.getH()]);
        for(int i=0; i<cg.getW();i++)
        {
            for(int n=0; n<cg.getH();n++)
            {
                cg.getCopy()[i][n]=cg.table.get(i).get(n).isAlive();
            }
        }
        for(int i=0; i < cg.getW(); i++)
        {
            for(int n=0; n < cg.getH(); n++)
            {    
               cg.decide(i, n, cg3d);
            }
        }    
    }
    // WritePattern bruker copien til å skrive over ogrinalen. 
    public static void WritePattern(CellGraph cg)
    {
        for(int i=0; i< cg.getW();i++)
        {
            for(int n=0; n<cg.getH(); n++)
            {
                if(cg.getCopy()[i][n]!=cg.table.get(i).get(n).isAlive())
                {
                    cg.table.get(i).get(n).changeState();
                }
            }
        }   
    }
}
