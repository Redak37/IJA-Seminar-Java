/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Abstraktní třída pro jednotlivé hry
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game;

import java.util.ArrayDeque;
import java.util.Vector;

/**
 * Abstraktní třída pro jednotlivé hry
 */
public abstract class Game{
    protected Board deska;
    protected ArrayDeque<Tah> tahy = new ArrayDeque<>();
    protected ArrayDeque<Tah> retahy = new ArrayDeque<>();
    protected Vector<Figure> figures = new Vector<>();
    protected boolean check = false;
    protected boolean vyhozeni = false;
    protected int cisloTahu = 1;
    protected char promoteTo = 'D';
    protected boolean nextMoveWhite = true;
    
    /**
     * Vytvoří hru se zadanou deskou.
     * @param board 
     */
    public Game(Board board) {
        deska = board;
    }
    
    /**
     * Provede pohyb s figurkou na políčko.
     * @param figure - hýbaná figurka
     * @param field - cílové políčko
     * @param userMove - true, pokud se jedná o tah, který provedl uživatel. V tomto případě se vyprázdní vektor retahů pro redo()
     * @return true, pokud byl pohyb úspěšný
     */
    abstract public boolean move(Figure figure, Field field, boolean userMove);
    
    /**
     * Provede pohyb s figurkou na políčko.
     * @param figure - hýbaná figurka
     * @param field - cílové políčko
     * @return true, pokud byl pohyb úspěšný
     */
    public boolean move(Figure figure, Field field){
        return move(figure, field, false);
    }
    
    /**
     * Vrátí předchozí tah.
     * @return True, pokud se undo provedlo (zásobník tahů nebyl prázdný)
     */
    abstract public boolean undo();
    
    /**
     * @return Vrací desku této hry.
     */
    public Board getBoard() {
    	return deska;
    }
    
    /**
     * Znovu provede vrácený tah.
     * @return True, pokud se redo provedlo (zásobník retahů nebyl prázdný)
     */
    abstract public boolean redo();
    
    /**
     * @return True, pokud byl v minulém úspěšném tahu šach. Relevantní pouze po úspěšném volání move(). Pouze šachy
     */
    public boolean getCheck(){
        return check;
    }
    
    /**
     * @return True, pokud byla v minulém úspěšném tahu vyhozena figurka. Relevantní pouze po úspěšném volání move(). Pouze šachy
     */
    public boolean byloVyhozeni(){
        return vyhozeni;
    }
    
    /**
     * @return Vrací vektor figurek této hry.
     */
    public Vector<Figure> getFigures(){
        return figures;
    }
    
    /**
     * @return Vrací zásobník provedených tahů
     */
    public ArrayDeque<Tah> getMoves(){
        return tahy;
    }
    /**
     * @return Vrací zásobník vrácených retahů
     */
    public ArrayDeque<Tah> getRedoMoves(){
        return retahy;
    }
    
    /**
     * @param to Nastaví, na co se bude pěšec povyšovat.
     */
    public void setPromoteTo(char to){
        promoteTo = to;
    }
    
    /**
     * @return True, pokud je další hráč na tahu bílý. Pouze checkers
     */
    public boolean isNextWhite(){
        return nextMoveWhite;
    }
}
