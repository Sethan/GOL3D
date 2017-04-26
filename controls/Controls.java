/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cell;
import model.CellGraph3D;
import model.DimensionHandler;
import model.FileHandler;
import model.GraphicsHandler;
import model.ThreadTasks;
import model.LineGraphData;



/**
 *
 * @author lars
 */
public class Controls implements Initializable {
    
    
    
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) 
    {
      
        timer.scheduleAtFixedRate(task, 500, 500);
        group.setOnMouseClicked((event)->{
                PickResult res = event.getPickResult();
                if (res.getIntersectedNode() instanceof Cell){
                    ((Cell)res.getIntersectedNode()).changeState();
                    GraphicsHandler.Paint(test);
                }
        });

        GraphicsHandler.CreatePlaneL(test, group);
        GraphicsHandler.CreatePlaneT(test, group);
        GraphicsHandler.CreatePlaneR(test, group);
        GraphicsHandler.CreateGridLines(test, group);
        GraphicsHandler.AdjustGroup(test, group);
        group.getChildren().add(light);
        
        stage.initStyle(StageStyle.UTILITY);
        xAxis.setLabel("GOL Generations");
        lineChart=new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("GOL Statistics");
        stage.setScene(new Scene(lineChart));  
        stage.hide();
        stage.setOnCloseRequest(e->{
                stage.hide();
        });
    }
    
    @FXML private Group group=new Group();
    @FXML private Slider speedSlider;
    private NumberAxis xAxis=new NumberAxis();
    private NumberAxis yAxis=new NumberAxis();
    private LineChart<Number, Number> lineChart;
    private static boolean play=false;
    Timer timer = new Timer(true);
    RunTimer task = new RunTimer();
    CellGraph3D test = new CellGraph3D( 7, 7, 7);
    AmbientLight light = new AmbientLight(Color.rgb(245,245,245));
    ExecutorService executor = Executors.newFixedThreadPool(3);
    LineGraphData lineData = new LineGraphData();
    Stage stage = new Stage();
           
    
    //user events
    public void speedIncreased(MouseEvent event)
    {
        int delay = 350-15*(int)Math.sqrt(speedSlider.getValue());
        task.cancel();
        task = new RunTimer();
        timer.scheduleAtFixedRate(task, 250, delay);
    }

    public void stopButton(ActionEvent event)
    {
	play=false;     
    }
    
    public void startButton(ActionEvent event)
    {
        play=true;   
        GraphicsHandler.Paint(test);
    }    
    
    public void resetButton(ActionEvent event)
    {
        play=false;
        test.getCGL().resetGraph();
        test.getCGR().resetGraph();
        test.getCGT().resetGraph();
        GraphicsHandler.Paint(test);
        lineData.resetData();
        lineData.updateGraph(lineChart);
    }
    
    public void loadEvent(ActionEvent event)
    {
        play=false;
        FileHandler.loadFile(test);
        GraphicsHandler.Paint(test);
    }
    
    public void saveEvent(ActionEvent event)
    {
        play=false;  
        FileHandler.saveFile(test);
    }
 
    public void changeDimensions(ActionEvent event)
    {
        play=false;
        group.getChildren().clear();
        group.getTransforms().clear();
        group.getChildren().add(light);
        test=DimensionHandler.createDialog();
        GraphicsHandler.CreatePlaneL(test, group);
        GraphicsHandler.CreatePlaneT(test, group);
        GraphicsHandler.CreatePlaneR(test, group);
        GraphicsHandler.CreateGridLines(test, group);
        GraphicsHandler.AdjustGroup(test, group);
        GraphicsHandler.Paint(test);
    }
    
    public void showGraphEvent()
    {                 
        stage.show();
        lineData.updateGraph(lineChart);
    }

    
    private class RunTimer extends TimerTask
    {
        @Override 
        public void run()
        {
            if(play)
            {  
                int[] data= new int[2];
                data[0]=test.getCGL().getData()[0]+test.getCGR().getData()[0]+test.getCGT().getData()[0];
                data[1]=test.getCGL().getData()[1]+test.getCGR().getData()[1]+test.getCGT().getData()[1];
                lineData.updateData(data[0],data[1]);            
                RunTopTask rtt = new RunTopTask();
                RunRightTask rrt = new RunRightTask();
                RunLeftTask rlt = new RunLeftTask();
                executor.submit(rlt);
                executor.submit(rtt);
                executor.submit(rrt);
                try
                {
                    executor.awaitTermination(5, TimeUnit.MILLISECONDS);
                }
                catch(InterruptedException e)
                {

                }
                WriteTop wt = new WriteTop();
                WriteLeft wl = new WriteLeft();
                WriteRight wr = new WriteRight();
                executor.submit(wt);
                executor.submit(wl);
                executor.submit(wr);
                try
                {
                    executor.awaitTermination(5, TimeUnit.MILLISECONDS);
                }
                catch(InterruptedException e)
                {

                }
                
                PaintPlanes pp = new PaintPlanes();
                executor.submit(pp);
                try
                {
                    executor.awaitTermination(50, TimeUnit.MILLISECONDS);
                }
                catch(InterruptedException e)
                {

                }
            }
        }
    }
    
    
    
    
    
    //Threads
    class PaintPlanes extends Task<Void>
    {
        @Override 
        public Void call()
        {
            GraphicsHandler.Paint(test);
            return null;  
        }
    }
    class RunTopTask extends Task<Void>
    {
        @Override 
        public Void call()
        {
            ThreadTasks.RunPattern(test.getCGT(), test); 
            return null;  
        }
    }
    class RunLeftTask extends Task<Void>
    {
        @Override 
        public Void call()
        {
            ThreadTasks.RunPattern(test.getCGL(), test);
            return null;  
        }
    }
    class RunRightTask extends Task<Void>
    {
        @Override 
        public Void call()
        {
            ThreadTasks.RunPattern(test.getCGR(), test);
            return null;  
        }
    }
    class WriteTop extends Task<Void>
    {
        @Override 
        public Void call()
        {
            ThreadTasks.WritePattern(test.getCGT());
            return null;  
        }
    }
    class WriteLeft extends Task<Void>
    {
        @Override 
        public Void call()
        {
            ThreadTasks.WritePattern(test.getCGL());
            return null;  
        }
    }
    class WriteRight extends Task<Void>
    {
        @Override 
        public Void call()
        {
            ThreadTasks.WritePattern(test.getCGR());
            return null;  
        }
    }
}

