/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author ZuraH
 */

   // LineGraphData inneholder 2d integer tabell kalt data. Formålet er å lagre informasjon hentet fra hovedprogrammet. 
public class LineGraphData {
    
    private ArrayList<ArrayList<Integer>> data;
    
    public LineGraphData()
    {
        this.data = new ArrayList<ArrayList<Integer>>();
    }
    // Updatedata lager en ny kolonne med to rader og radene inneholder tallet a og b, a er antall levende celler og 
    // b er summen av posisjonene til alle levende celler. 
    
    public void updateData(Integer a, Integer b) 
    {
        this.data.add(new ArrayList<Integer>());
        this.data.get(this.data.size()-1).add(a);
        this.data.get(this.data.size()-1).add(b);
    }
    // UpdateGraph oppdateterer grafen til linechart lc med informasjonen fra updatedata.
    // Den oppretter tre serier(levende celler, forandring av levende celler og likehetsmåling)
    //For å iterere gjennom tabellen og regne ut de tre seriene starter løkken i i=1 og avslutter i det nest siste elementet, dette 
    //Dette er viktig for å ikke få noen udefinerte verdier fra tabellen, t1 henter tall fra forrige iterasjon, og change henter fra neste iterasjon
    public void updateGraph(LineChart lc)
    {
        lc.getData().clear();
        XYChart.Series<Number, Number> aliveCells = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> changeCells = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> similarity = new XYChart.Series<Number, Number>();
        
        aliveCells.setName("Alive Cells");
        changeCells.setName("Change in living Cells");
        similarity.setName("Similarity Measure");
        
        for(int i=1; i<this.data.size()-1;i++)
        {
            int alive = data.get(i).get(0);
            int change = data.get(i+1).get(0)-data.get(i).get(0);
            double t1 = 0.5*data.get(i-1).get(0)+3*(data.get(i).get(0)-data.get(i-1).get(0))+0.25*data.get(i-1).get(1);
            double t2 = 0.5*alive+3*change+0.25*data.get(i).get(1);
            double result;
            if(t1==0||t2==0)
            {
                result = 0;
            }
            else
            {
                if(t1>t2)
                {
                     result = t2/t1;
                }
                else
                {
                    result = t1/t2;
                }
            }
            result = Math.floor(result*100);

            aliveCells.getData().add(new XYChart.Data<Number, Number>(i, alive));
            changeCells.getData().add(new XYChart.Data<Number, Number>(i, change));
            similarity.getData().add(new XYChart.Data<Number, Number>(i, result));
        }
        lc.getData().add(aliveCells);
        lc.getData().add(changeCells);
        lc.getData().add(similarity);
    }
    //resetDate sletter all informasjon i data.
    public void resetData()
    {
        this.data.clear();
    }
}
