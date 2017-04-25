/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 *
 * @author lars
 */
public class GraphicsHandler {
    public static void CreatePlaneR(CellGraph3D cg3d, Group g)
    {
        int x = cg3d.getX();
        int y = cg3d.getY();
        int z = cg3d.getZ();
        for(int i=0; i < y; i++)
        {
            for(int n=0; n < z; n++)
            {
               cg3d.getCGR().table.get(i).get(n).setTranslateZ(n*20);
               cg3d.getCGR().table.get(i).get(n).setTranslateY(i*20);
               cg3d.getCGR().table.get(i).get(n).setTranslateX(10+(x-1)*20);
               g.getChildren().add(cg3d.getCGR().table.get(i).get(n)); 
            }
        }
    }
    
    public static void CreateGridLines(CellGraph3D cg3d, Group g)
    {
        int x = cg3d.getX();
        int y = cg3d.getY();
        int z = cg3d.getZ();
        PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseColor(Color.BLACK);
        
        for(int i=0; i < z+1; i++)
        {
            Box n = new Box(1,20*y-1,1);
            n.setTranslateZ(i*20-10);
            n.setTranslateY(y*10-10);
            n.setTranslateX(10+(x-1)*20);
            n.setMaterial(mat);
            g.getChildren().add(n);
            Box b = new Box(20*x-1,1,1);
            b.setTranslateZ(i*20-10);
            b.setTranslateY(10+(y-1)*20);
            b.setTranslateX(x*10-10);
            b.setMaterial(mat);
            g.getChildren().add(b);
        }
        
        for(int i=0; i < y+1; i++)
        {
            Box n = new Box(1,1,20*z-1);
            n.setTranslateZ(z*10-10);
            n.setTranslateY(i*20-10);
            n.setTranslateX(10+(x-1)*20);
            n.setMaterial(mat);
            g.getChildren().add(n);
            Box b = new Box(20*x-1,1,1);
            b.setTranslateZ(10+(z-1)*20);
            b.setTranslateY(i*20-10);
            b.setTranslateX(x*10-10);
            b.setMaterial(mat);
            g.getChildren().add(b);
        }
        
        for(int i=0; i < x+1; i++)
        {
            Box n = new Box(1,20*y-1,1);
            n.setTranslateZ(10+(z-1)*20);
            n.setTranslateY(y*10-10);
            n.setTranslateX(i*20-10);
            n.setMaterial(mat);
            g.getChildren().add(n);
            Box b = new Box(1,1,20*z-1);
            b.setTranslateZ(z*10-10);
            b.setTranslateY(10+(y-1)*20);
            b.setTranslateX(i*20-10);
            b.setMaterial(mat);
            g.getChildren().add(b);
        }
    }
    
    public static void CreatePlaneL(CellGraph3D cg3d, Group g)
    {
        int x = cg3d.getX();
        int y = cg3d.getY();
        int z = cg3d.getZ();
        for(int i=0; i < x; i++)
        {
            for(int n=0; n < y; n++)
            {
               cg3d.getCGL().table.get(i).get(n).setTranslateZ(10+(z-1)*20);
               cg3d.getCGL().table.get(i).get(n).setTranslateY(n*20);
               cg3d.getCGL().table.get(i).get(n).setTranslateX(i*20);
               g.getChildren().add(cg3d.getCGL().table.get(i).get(n)); 
            }
        }
    }
    
    public static void CreatePlaneT(CellGraph3D cg3d, Group g)
    {
        int x = cg3d.getX();
        int y = cg3d.getY();
        int z = cg3d.getZ();
        for(int i=0; i < x; i++)
        {
            for(int n=0; n < z; n++)
            {
               cg3d.getCGT().table.get(i).get(n).setTranslateZ(n*20);
               cg3d.getCGT().table.get(i).get(n).setTranslateY(10+(y-1)*20);
               cg3d.getCGT().table.get(i).get(n).setTranslateX(i*20);
               g.getChildren().add(cg3d.getCGT().table.get(i).get(n)); 
            }
        }
    }
    public static void AdjustGroup(CellGraph3D cg3d, Group g)
    {
        int x = cg3d.getX();
        int y = cg3d.getY();
        int z = cg3d.getZ();
        double average = (x+y+z)/3;
        
        double posX = 500;
        double posY = 250;
        double yOffSet = Math.sqrt(x^2+y^2+z^2)*10;
        double xOffSet = Math.sqrt(2)*(y-z)*5;
        Translate trans = new Translate(posX+xOffSet,posY-yOffSet);
       
        Rotate rotateX = new Rotate(45,  Rotate.X_AXIS);
        Rotate rotateY = new Rotate(135, Rotate.Y_AXIS);
        Rotate rotateZ = new Rotate(-90, Rotate.Z_AXIS);
        
        double scaleValue=2.58-0.67*Math.log(average);
        Scale scale = new Scale(scaleValue, scaleValue, scaleValue);
        
        
        g.getTransforms().addAll(trans, scale, rotateX, rotateY, rotateZ); 
    }
    public static void Paint(CellGraph3D cg3d) throws ArrayIndexOutOfBoundsException
    {
        PhongMaterial white = new PhongMaterial(Color.rgb(255,255,255));
        
        int maxGreen=255/cg3d.getY();
        int maxBlue=255/cg3d.getZ();
        int maxRed=255/cg3d.getX();
        
        try
        {
            for(int i=0; i< cg3d.getCGR().getW();i++)
            {
                for(int n=0; n<cg3d.getCGR().getH(); n++)
                {
                    if(cg3d.getCGR().table.get(i).get(n).isAlive())
                    {
                        PhongMaterial color = new PhongMaterial(Color.rgb(maxGreen*i, maxBlue*n, 255));
                        cg3d.getCGR().table.get(i).get(n).setMaterial(color);
                    }
                    else
                    {
                        cg3d.getCGR().table.get(i).get(n).setMaterial(white);
                    }
                }
            }
            for(int i=0; i< cg3d.getCGL().getW();i++)
            {
                for(int n=0; n<cg3d.getCGL().getH(); n++)
                {
                    if(cg3d.getCGL().table.get(i).get(n).isAlive())
                    {
                        PhongMaterial color = new PhongMaterial(Color.rgb(maxGreen*n, 255, maxRed*i));
                        cg3d.getCGL().table.get(i).get(n).setMaterial(color);
                    }
                    else
                    {
                        cg3d.getCGL().table.get(i).get(n).setMaterial(white);
                    }  
                }
            }
            for(int i=0; i< cg3d.getCGT().getW();i++)
            {
                for(int n=0; n<cg3d.getCGT().getH(); n++)
                {
                    if(cg3d.getCGT().table.get(i).get(n).isAlive())
                    {
                        PhongMaterial color = new PhongMaterial(Color.rgb(255, maxBlue*n, maxRed*i));
                        cg3d.getCGT().table.get(i).get(n).setMaterial(color);
                    }
                    else
                    {
                        cg3d.getCGT().table.get(i).get(n).setMaterial(white);
                    }
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException|NullPointerException e)
        {
            System.err.println("Error is"+e.toString());
        }
   }

}
