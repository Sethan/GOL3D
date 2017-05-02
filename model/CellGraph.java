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
 // Cellgraph er en abstrakt klasse som inneholder bredden og høyden til cellen, inneholder en tabell med firekanter
 //( denne danner 3 dimisjonen for applikasjonen) og tilslutt kopi av tabellen «copy» som kun inneholder tilstanden til 
 //cellene (true, false). 


public abstract class CellGraph 
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
    //Returnerer cellens bredde med cellWidth
    public int getW()
    {
        return this.cellWidth;
    }
    
    //Returnerer cellens høyde med cellHeight
    public int getH()
    {
        return this.cellHeight;
    }
    //Returnerer copyet av tabellen med copy
    public Boolean[][] getCopy()
    {
        return this.copy;
    }
    //lager en copy og metoden vil oppdatere kopien til den som er i thread task. 
    public void setCopy(Boolean[][] copy)
    {
        this.copy=copy;
    }
    // Lager too «for» loop og en if statment, for loop-ene inneholder int variabler n og i. 
    //Disse vil samle inn hvor mange ruter/celler som er i live for både bredden og høyden, 
    //tillegg summen av posisjonen. Deretter har du variablene alive som viser hvor mange 
    //ruter/celler som er lever og posistionSum som viser summen av posisjonen.  
    public int[] getData()
    {
        int alive = 0;
        int posisitionSum=0;
        for(int i=0; i< this.getW();i++)
        {
            for(int n=0; n<this.getH(); n++)
            {
                if(this.table.get(i).get(n).isAlive())
                {
                    alive++;
                    posisitionSum=posisitionSum+i+n;
                }
            }
        }
        int[] data = new int[2];
        data[0]=alive; // inneholder informasjon om alive varibalen
        data[1]=posisitionSum;// inneholder informasjonen om positionsum
        return data; // returner informasjonen i data.
    }
    //Her er hovedoppgaven til koden å finne ut hvor mange naboer, for eksempel en celle har. 
    //Koden vil gå gjennom to «regler» Første if statmentet vil sjekke om det finnes levende 
    //celler i x eller y(høyde og bredde). Deretter vil if-statmentet nr 2 sjekke om cellen som lever, 
    //finne ut om cellen har flere naboer enn 3 eller mindre enn 2. Dersom denne blir false vil cellen dø. 
    //Men om cellen aldri var levende vil applikasjonen sjekke om cellen har 3 naboer(cellen som er dø), 
    //har den 3 naboer vil cellen gjenopplives.
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
    
    public abstract int countNeighbours(int x, int y, CellGraph3D test);

    // Her vil programmet resetet «spillet» ved at programmet går gjennom cellebredde og cellehøyde 
    //for å lete etter celler. Cellgraph/ruter som er i live. Dersom den finner noen som lever vil de 
    //endre statusen på disse. Altså fra true til false. 
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
