/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Základní třída pro spuštění aplikace
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Základní třída pro spuštění aplikace
 */
public class ija2019 extends Application {
    
    @Override
    /**
     * Metoda inicializující GUI
     */
    public void start(Stage primaryStage) {
        FileInputStream fxml = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            BorderPane root = loader.load(getClass().getResource("gui/MainWindow.fxml"));
            Scene scene = new Scene(root, 1024, 720);
            primaryStage.setTitle("Šachy");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(750);
            primaryStage.setMinHeight(720);
            primaryStage.show();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ija2019.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ija2019.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Metoda main
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
