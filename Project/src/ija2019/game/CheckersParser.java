/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro načítání hry v Checkers
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * Třída pro načítání hry v Checkers
 */
public class CheckersParser {
    
    
    private int cislo_tahu = 0;
    
    private Board deska;
    private Game game;
    
    private Vector<String> tahy = new Vector<>();
    
    /**
     * Vytvoří checkers parser na dané hře
     * @param hra - vytvořená hra
     */
    public CheckersParser(Game hra) {
        game = hra;
        deska = hra.getBoard();
    }
    
    /**
     * Přečte vstupní soubor, zkontrolu notaci zápisu, odsimuluje hru a naplní její vektor retahy pro redo()
     * @param fileName - jméno souboru
     */
    public void read(String fileName) {
        tahy.clear();
        BufferedReader vstup;
        try {
            vstup = new BufferedReader(new FileReader(fileName));
            
            String tmp;
            while((tmp = vstup.readLine()) != null) {
                if(!parse(tmp)){
                    return;
                }
                tahy.add(tmp);
            }
        } catch (IOException e) {
        }
        while(game.undo()){
        }
    }
    
    private boolean parse(String radek) {
        String tmp[] = radek.split("\\. ");
        
        if(tmp.length != 2) {
            return false;
        }
        
        try {
            if(++cislo_tahu != Integer.parseInt(tmp[0])) {
                return false;
            }
        }
        catch(NumberFormatException e) {
            return false;
        }
        
        tmp = tmp[1].split(" ");
        for (String part : tmp) {
            if (part.length() != 5)
                return false;
        
            Figure figurka;
            Field policko;
            int row;
            int col;

            col = part.charAt(1) - 'a' + 1;
            row = part.charAt(2) - '0';
            if(col < 1 || col > 8 || row < 1 || row > 8)
                return false;
            figurka = deska.getField(col, row).get();
            col = part.charAt(3) - 'a' + 1;
            row = part.charAt(4) - '0';
            if(col < 1 || col > 8 || row < 1 || row > 8)
                return false;
            policko = deska.getField(col, row);
            if(!game.move(figurka, policko))
                return false;
        }
        return true;
    }
    
    /**
     * @return Vektorů načtených správných tahů. Použitelné pouze po zavolání read()
     */
    public Vector<String> getMoves(){
        return tahy;
    }
}
