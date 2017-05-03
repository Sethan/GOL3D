/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

/**
 *
 * @author ZuraH
 */
// oppretter en feil-melding med titlen error og header/inneholdet blir bestemt andre steder.
public class ErrorHandler {
    public static void showError(String type, String info)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(type);
        alert.setContentText(info);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
}
