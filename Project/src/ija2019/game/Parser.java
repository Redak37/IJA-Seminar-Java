/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro načtení hry v šachu
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

/**
 * Třída pro načtení hry v šachu
 */
public class Parser {
    private Figure figurka;
    private Field pole_do;
    
    private int cislo_tahu = 0;
    
    private Board deska;
    private Game game;
    
    private Vector<String> tahy = new Vector<>();
    
    /**
     * Vytvoří šachový parser na dané hře
     * @param hra - vytvořená hra
     */
    public Parser(Game hra) {
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
        if(tmp.length > 2) {
            return false;
        }
        
        String figury[] = {"p", "V", "J", "S", "D", "K"};
        for(int ind = 0; ind < tmp.length; ++ind) {
            int i = 0;
            int x = 0;
            int znakyNakonci = 0;
            boolean prySach = false;
            char povysujeSeNa = 0;
            if( Arrays.asList(figury).contains(tmp[ind].charAt(0)+"")) {
                i++;
            }
            char znakFigurky = (i == 1) ? tmp[ind].charAt(0) : 'p';
            
            if(tmp[ind].contains("x")) {
                ++x;
            }
            
            if(tmp[ind].contains("+")) {
                ++znakyNakonci;
                prySach = true;
            }
            if(tmp[ind].contains("#")) {
                ++znakyNakonci;
                if(prySach)
                    return false;
                prySach = true;
            }
            char toCoJeNaKonci = tmp[ind].charAt(tmp[ind].length() - znakyNakonci - 1);
            if(Arrays.asList(figury).contains(toCoJeNaKonci+"")){
                ++znakyNakonci;
                povysujeSeNa = tmp[ind].charAt(tmp[ind].length() - znakyNakonci);
            }
            
            int delka = tmp[ind].length() - i - znakyNakonci - x;
            
            int row;
            int col;
            
            if (delka == 2) {
                col = tmp[ind].charAt(i+x) - 'a' + 1;
                row = tmp[ind].charAt(i+x+1) - '0';
                if(col < 1 || col > 8 || row < 1 || row > 8)
                    return false;
                figurka = null;
                pole_do = deska.getField(col, row);
                for(Figure fig : game.getFigures()){
                    if((fig.getChar() == znakFigurky) && (fig.canIGoTo(pole_do))){
                        if(figurka == null){
                            figurka = fig;
                        }
                        else //žádné upřesnění
                            return false;
                    }
                }
            } else if (delka == 3) {
                int prvni = tmp[ind].charAt(i+1);
                if (prvni - 'a' < 0) {
                    prvni -= '0';
                    if(prvni < 1 || prvni > 8)
                        return false;
                    figurka = null; 
                    for(Figure fig : game.getFigures()){
                        if((fig.getChar() == znakFigurky) && (fig.getField().getCol() == prvni -1)){
                            if(figurka == null){
                                figurka = fig;
                            }
                            else //blbé upřesnění
                                return false;
                        }
                    }
                } else {
                    prvni -= 'a';
                    if(prvni < 1 || prvni > 8)
                        return false;
                    figurka = null; 
                    for(Figure fig : game.getFigures()){
                        if((fig.getChar() == znakFigurky) && (fig.getField().getRow() == prvni -1)){
                            if(figurka == null){
                                figurka = fig;
                            }
                            else //blbé upřesnění
                                return false;
                        }
                    }
                }
                col = tmp[ind].charAt(i+x+1) - 'a' + 1;
                row = tmp[ind].charAt(i+x+2) - '0';
            } else if(delka == 4) {
                col = tmp[ind].charAt(i) - 'a' + 1;
                row = tmp[ind].charAt(i+1) - '0';
                if(col < 1 || col > 8 || row < 1 || row > 8)
                    return false;
                figurka = deska.getField(col, row).get();
                col = tmp[ind].charAt(i+x+2) - 'a' + 1;
                row = tmp[ind].charAt(i+x+3) - '0';
            } else {
                return false;
            }
            
            if(col < 1 || col > 8 || row < 1 || row > 8)
                return false;
            pole_do = deska.getField(col, row);
            char savePrevious = game.promoteTo;
            if(povysujeSeNa != 0)
                game.setPromoteTo(povysujeSeNa);
            if(!game.move(figurka, pole_do))
                return false;
            game.setPromoteTo(savePrevious);
            if(game.byloVyhozeni() != (x == 1))
                return false;
            if(game.getCheck() != prySach)
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
