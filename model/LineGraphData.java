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
public class LineGraphData {
    
    private ArrayList<ArrayList<Integer>> data;
    
    public LineGraphData()
    {
        this.data = new ArrayList<ArrayList<Integer>>();
    }
    
    public void updateData(Integer a) 
    {
        this.data.add(new ArrayList<Integer>());
        this.data.get(this.data.size()-1).add(a);
    }
    public void updateGraph(LineChart lc)
    {
        lc.getData().clear();
        XYChart.Series<Number, Number> aliveCells = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> changeCells = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> similarity = new XYChart.Series<Number, Number>();
        
        aliveCells.setName("Alive Cells");
        changeCells.setName("Change in living Cells");
        similarity.setName("Similarity Measure");
        
        for(int i=0; i<this.data.size()-1;i++)
        {
            int alive =data.get(i).get(0);
            int change = data.get(i+1).get(0)-data.get(i).get(0);
            
            aliveCells.getData().add(new XYChart.Data<Number, Number>(i, alive));
            changeCells.getData().add(new XYChart.Data<Number, Number>(i, change));
            similarity.getData().add(new XYChart.Data<Number, Number>(i, (0.5*alive+3*change+0.25)));
        }
        lc.getData().add(aliveCells);
        lc.getData().add(changeCells);
    }
    public void resetData()
    {
        this.data= new ArrayList<ArrayList<Integer>>();
    }
}
