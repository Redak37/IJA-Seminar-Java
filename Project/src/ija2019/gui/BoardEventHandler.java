/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.gui;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Abstraktní třída BoardEventHandler zpracující eventy kliknutí na herní ploše
 * @param <T> 
 */
public abstract class BoardEventHandler<T extends Event> implements EventHandler{
    protected final int x;
    protected final int y;
    
    /**
     * Zpracuje event kliknutí na políčko na těchto souřadnicích
     * @param x - sloupec
     * @param y - řádek
     */
    public BoardEventHandler(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
