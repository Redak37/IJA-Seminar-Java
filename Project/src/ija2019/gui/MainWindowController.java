/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * FXML Controller class
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 * Controller hlavního okna
 */
public class MainWindowController implements Initializable {

    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addChess();
    }
    
    /**
     * Funkce bindovaná z tlačítka, vytvoří novou záložku s hrou šachy.
     */
    @FXML
    private void addChess() {
        Tab tab = new Tab("Šachy");
        FXMLLoader loader = new FXMLLoader();
        try {
            BorderPane paneContent = loader.load(getClass().getResource("TabContent.fxml"));
            tab.setContent(paneContent);
        } catch (IOException ex) {
        }
        tabPane.getTabs().add(tab);
    }

    /**
     * Funkce bindovaná z tlačítka, vytvoří novou záložku s hrou Checkers.
     */
    @FXML
    private void addCheckers() {
        Tab tab = new Tab("Checkers");
        FXMLLoader loader = new FXMLLoader();
        try {
            BorderPane paneContent = loader.load(getClass().getResource("TabContentCheckers.fxml"));
            tab.setContent(paneContent);
        } catch (IOException ex) {
        }
        tabPane.getTabs().add(tab);
    }
}
