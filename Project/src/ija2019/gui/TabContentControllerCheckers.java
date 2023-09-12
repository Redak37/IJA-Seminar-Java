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

import ija2019.GameFactory;
import ija2019.game.Board;
import ija2019.game.CheckersParser;
import ija2019.game.Figure;
import ija2019.game.Game;
import ija2019.game.Tah;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Controller GUI checkers
 */
public class TabContentControllerCheckers implements Initializable {
    @FXML
    private ListView<String> movesList;
    private Game game;
    private Board board;
    @FXML
    private GridPane grid;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private ToggleButton autoButton;
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button saveButton;
    private ImageView viewArray[][];
    private boolean whiteOnMove = true;
    private Figure selectedFigure = null;
    private boolean userSelected = true;
    private boolean systemSelected = true;
    private Timer budicek;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        movesList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                moveSelected(oldValue, newValue);
            }
        });
        board = new Board(8);
        game = GameFactory.createCheckersGame(board);
        viewArray = new ImageView[8][8];
        initView();
        movesList.getItems().add("1. ");
        movesList.getSelectionModel().getSelectedIndex();
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
    }    
    
    /**
     * Funkce implementující změnu stavu hry při kliknutí na konkrétní tah.
     * @param oldValue - index starého řádku
     * @param newValue - index kliknutého řádku
     */
    @FXML
    private void moveSelected(Number oldValue, Number newValue) {
        if(userSelected){
            systemSelected = false;
            if(newValue.intValue() == -1)
                return;
            int posun = newValue.intValue() - oldValue.intValue();
            while(posun > 0){
                redo();
                posun--;
            }
            while(posun < 0){
                undo();
                posun++;
            }
            whiteOnMove = !whiteOnMove;
            systemSelected = true;
        }
    }
    
    /**
     * Funkce inicializující šachovnici a základní rozestavení figurek.
     */
    private void initView(){
        for(int i = 0; i < 8 ; i++){
            for(int j = 0; j < 8; j++){
                Figure fig = board.getField(i+1, 8 - j).get();
                if(fig != null){
                    String color = fig.isWhite() ? "W" : "B";
                    viewArray[i][j] = new ImageView("file:data/image/" + color + fig.getChar() + ".png");
                }
                else{
                    viewArray[i][j] = new ImageView("file:data/image/t.png");
                }
                viewArray[i][j].setPreserveRatio(true);
                viewArray[i][j].setFitHeight(60);
                viewArray[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new BoardEventHandler<MouseEvent>(i+1, 8 - j) {
                    @Override
                    public void handle(Event event) {
                        fieldClicked(x, y);
                        event.consume();
                    }
                });
                grid.add(viewArray[i][j], i + 1, j);
            }
        }
    }
    
    /**
     * Funkce pro vykreslení aktuálního rozestavení figur.
     */
    private void placeFigures(){
        for(int i = 0; i < 8 ; i++){
            for(int j = 0; j < 8; j++){
                Figure fig = board.getField(i+1, 8 - j).get();
                if(fig != null){
                    String color = fig.isWhite() ? "W" : "B";
                    viewArray[i][j].setImage(new Image("file:data/image/" + color + fig.getChar() + ".png"));
                }
                else{
                    viewArray[i][j].setImage(new Image("file:data/image/t.png"));
                }
            }
        }
    }
    
    /**
     * Funkce implementující kliknutí na konkrétní políčko hrací desky
     * @param i - sloupec
     * @param j - řádek
     */
    private void fieldClicked(int i, int j){
        Figure fig = board.getField(i, j).get();
        if(selectedFigure == null){
            if(fig == null || fig.isWhite() != whiteOnMove)
                return;
            selectedFigure = fig;
        }
        else{
            if(fig == null || fig.isWhite() != whiteOnMove){
                userSelected = false;
                boolean wasWhiteLast = whiteOnMove;
                if(game.move(selectedFigure, board.getField(i, j), true)){
                    whiteOnMove = game.isNextWhite();
                    Iterator<Tah> iter = game.getMoves().descendingIterator();
                    movesList.getItems().clear();
                    String move = "";
                    int kolik = 1;
                    boolean bylNaTahuBily = true;
                    while(iter.hasNext()){
                        Tah tah = iter.next();
                        if(move == ""){
                            move += kolik + ". ";
                            move += tah.getState();
                            bylNaTahuBily = tah.getFigurka().isWhite();
                        }
                        else{
                            if(tah.getFigurka().isWhite() == bylNaTahuBily){
                                move += " " + tah.getState();
                            }
                            else{
                                movesList.getItems().add(move);
                                movesList.getSelectionModel().select(move);
                                kolik++;
                                move = "";
                                move += kolik + ". ";
                                move += tah.getState();
                                bylNaTahuBily = tah.getFigurka().isWhite();
                            }
                        }
                    }
                    
                    if(move != ""){
                        movesList.getItems().add(move);
                        movesList.getSelectionModel().select(move);
                    }
                    int idx = movesList.getSelectionModel().getSelectedIndex();
                    movesList.getItems().add((idx + 2)+". ");
                    movesList.getSelectionModel().select(idx + 1);
                    placeFigures();
                }
                if(wasWhiteLast != whiteOnMove)
                    selectedFigure = null;
                userSelected = true;
            }
            else{
                selectedFigure = fig;
            }
        }
    }
    
    /**
     * Funkce provádějící operaci undo po kliknutí na příslušné tlačítko.
     */
    @FXML
    private void undo(){
        Tah move = game.getMoves().peekFirst();
        if(move == null)
            return;
        userSelected = false;
        game.undo();
        placeFigures();
        if(movesList.getSelectionModel().getSelectedIndex() != 0 && systemSelected)
            movesList.getSelectionModel().select(movesList.getSelectionModel().getSelectedIndex() - 1);
        whiteOnMove = !whiteOnMove;
        userSelected = true;
    }
    
    /**
     * Funkce provádějící operaci redo po kliknutí na příslušné tlačítko.
     */
    @FXML
    private void redo(){
        Tah move = game.getRedoMoves().peekFirst();
        if(move == null)
            return;
        userSelected = false;
        if(game.redo() && systemSelected)
            movesList.getSelectionModel().select(movesList.getSelectionModel().getSelectedIndex() + 1);
        placeFigures();
        whiteOnMove = !whiteOnMove;
        userSelected = true;
    }
    
    /**
     * Funkce načítající soubor s hrou po kliknutí na příslušné tlačítko.
     */
    @FXML
    private void load(){
        final Stage dialog = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vyber soubor s hrou");
        File file = fileChooser.showOpenDialog(dialog);
        board = new Board(8);
        game = GameFactory.createCheckersGame(board);
        placeFigures();
        CheckersParser parser = new CheckersParser(game);
        parser.read(file.getAbsolutePath());
        userSelected = false;
        movesList.getItems().clear();
        int kolik = 1;
        for (String tah: parser.getMoves()){
            movesList.getItems().add(tah);
            kolik++;
        }
        if(movesList.getItems().get(kolik-2).split(" ").length == 3)
            movesList.getItems().add(kolik + ". ");
        movesList.getSelectionModel().select(0);
        userSelected = true;
    }
    
    /**
     * Funkce pro vybrání souboru k uložení hry.
     */
    @FXML
    private void save(){
        FileWriter writer = null;
        final Stage dialog = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Uložit...");
        File file = fileChooser.showSaveDialog(dialog);
        if(file != null){
            try {
                writer = new FileWriter(file);
                for (String radek : movesList.getItems()){
                    if(radek.split(" ").length > 1)
                        writer.write(radek + "\n");
                }
                writer.flush();
                writer.close();
            } catch (IOException ex) {
            } finally {
                try {
                    if(writer != null)
                        writer.close();
                } catch (IOException ex) {
                }
            }
        }
    }
    
    /**
     * Funkce přepínající automatické přehrávání hry
     */
    @FXML
    private void auto(){
        if(autoButton.isSelected()){
            spinner.setDisable(true);
            undoButton.setDisable(true);
            redoButton.setDisable(true);
            loadButton.setDisable(true);
            saveButton.setDisable(true);
            movesList.setDisable(true);
            budicek = new Timer();
            budicek.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    redo();
                    if(game.getRedoMoves().peekFirst() == null){
                        autoButton.setSelected(false);
                        auto();
                    }
                }
            }, spinner.getValue()*1000, spinner.getValue()*1000);
        }
        else{
            spinner.setDisable(false);
            undoButton.setDisable(false);
            redoButton.setDisable(false);
            loadButton.setDisable(false);
            saveButton.setDisable(false);
            movesList.setDisable(false);
            budicek.cancel();
        }
    }
}
