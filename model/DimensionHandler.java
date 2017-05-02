/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
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
 // danner en dialog box med en knapp og 3 felter(for å skrive inn verdier) 
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
    // Her dannes det en form for tilbake melding til brukern dersom brukeren enten ikke fyller inn verdier, eller setter inn ugyldige verdier.
    // Dette vil si; Hvis brukeren setter in Char verdier istedenfor Int verdier eller om brukeren velger int verdier etter max(som er tall fra 1 til 20)
    // Hvis brukeren gjør en av feilene over vil en "error" melding poppe up som ber brukeren om å sette inn valid numbers. 
    public static int returnInt(String number)
    {
        try
        {
            if(Integer.parseInt(number)>0&&Integer.parseInt(number)<21)
            {
                return Integer.parseInt(number);  
            }
            else
            {
                ErrorHandler.showError("Out of bounds error", number+" is not a valid number, only real numbers (min 1, max 20)");
                return 7;
            }
        }
        catch(NumberFormatException e)
        {
            ErrorHandler.showError("Format error", number+" is not a valid number, only real numbers between 0 and 20");
            return 7;
        }
    }
}
