/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

/**
 *
 * @author ZuraH
 */
public class DimensionHandler {
    public static CellGraph3D createDialog()
    {
        Dialog dialog = new Dialog();
        dialog.setTitle("Dimension Editor");
        dialog.initStyle(StageStyle.UTILITY);

        ButtonType confirmButton = new ButtonType("Confirm", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField xcor = new TextField();
        xcor.setPromptText("X");
        xcor.setMaxWidth(40);
        TextField ycor = new TextField();
        ycor.setPromptText("Y");
        ycor.setMaxWidth(40);
        TextField zcor = new TextField();
        zcor.setPromptText("Z");
        zcor.setMaxWidth(40);

        grid.add(xcor, 0, 0);
        grid.add(ycor, 1, 0);
        grid.add(zcor, 2, 0);

        dialog.getDialogPane().setContent(grid);
        
        Optional result = dialog.showAndWait();
        if (result.isPresent()){
            int x = returnInt(xcor.getText());
            int y = returnInt(ycor.getText());
            int z = returnInt(zcor.getText());
            CellGraph3D cg3d = new CellGraph3D(x,y,z);
            return cg3d;
        };

        return null;
    }
    public static int returnInt(String number)
    {
        try
        {
            if(Integer.parseInt(number)>0&&Integer.parseInt(number)<15)
            {
                return Integer.parseInt(number);  
            }
            else
            {
                ErrorHandler.showError("Out of bounds error", number+" is not a valid number, only real numbers between 0 and 15");
                return 7;
            }
        }
        catch(NumberFormatException e)
        {
            ErrorHandler.showError("Format error", number+" is not a valid number, only real numbers between 0 and 15");
            return 7;
        }
    }
}
