/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro jednotlivá pole šachovnice
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game;

import java.util.HashMap;

/**
 * Třída pro jednotlivá pole šachovnice
 */
public class Field{
    /**
     * Enum specifikující 8 směrů okolí políčka
     */
    public static enum Direction{
        D, L, LD, LU, R, RD, RU, U;
    }
    
    private int radek;
    private int sloupec;
    private Figure kamen;
    private HashMap<Field.Direction, Field> okoli;
    
    /**
     * Vytvoří políčko, které si zapamatuje, že leží na daných souřadnicích
     * @param col 0-7
     * @param row 0-7
     */
    public Field(int col, int row){
        sloupec = col;
        radek = row;
        kamen = null;
        okoli = new HashMap<>();
    }
    
    /**
     * @return - Funkce vrací číslo sloupce políčka počítáno od 0
     */
    public int getCol(){
        return sloupec;
    }
    
    /**
     * @return - Funkce vrací číslo řádku políčka počítáno od 0
     */
    public int getRow(){
        return radek;
    }
    
    /**
     * Funkce přidá políčku souseda v daném směru.
     * @param dirs směr
     * @param field sousední políčko
     */
    public void addNextField(Direction dirs, Field field) {
        okoli.put(dirs, field);
    }
    
    /**
     * @param dirs směr
     * @return - Funkce vrátí sousední políčko tohoto políčka v zadaném směru
     */
    public Field nextField(Direction dirs) {
        return okoli.get(dirs);
    }
    
    /**
     * Položí figurku na toto políčko
     * @param figure - figurka
     * @return - vrací předanou figurku
     */
    public Figure put(Figure figure) {
        kamen = figure;
        figure.setField(this);
        return figure;
    }
    
    /**
     * Odstraní konkrétní figurku z políčka
     * @param figure - figurka
     * @return - true, pokud předaná figurka byla odstraněna
     */
    public boolean remove(Figure figure) {
        if(isEmpty() || kamen != figure)
            return false;
        kamen.setField(null);
        kamen = null;
        return true;
    }
    
    /**
     * @return - True, pokud je políčko prázdné = nestojí na něm figurka.
     */
    public boolean isEmpty() {
        return kamen == null;
    }
    
    /**
     * @return Vrací figurka, která stojí na políčku. Null pokud na něm nic nestojí.
     */
    public Figure get() {
        return kamen;
    }
}
