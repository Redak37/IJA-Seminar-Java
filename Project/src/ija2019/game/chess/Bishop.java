/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro střelce v šachu
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game.chess;

import ija2019.game.Field;
import ija2019.game.Figure;

/**
 * Třída pro střelce v šachu
 */
public class Bishop extends Figure{

    /**
     * Vytvoří figurku dané barvy
     * @param isWhite - true pro bílou figurku, false pro černou
     */
    public Bishop(boolean isWhite){
        super(isWhite);
        znakFigurky = 'S';
    }
    
    /** Funkce řekne, jestli figurka dokáže dojít na zadané políčko.
     * 
     * @param moveTo - políčko, kam se má figurka pohnout
     * @return True, pokud se figurka může pohnout na toto políčko.
     */
    @Override
    public boolean canIGoTo(Field moveTo){
        if(pole == null || pole == moveTo || !muzeNa(moveTo))
            return false;
        int rozdilX = moveTo.getCol() - pole.getCol();
        int rozdilY = moveTo.getRow() - pole.getRow();
        if(Math.abs(rozdilX) != Math.abs(rozdilY)) // Cílové políčko neleží po diagonále od začátku
            return false;
        if(rozdilY > 0){
            if(rozdilX > 0){
                if(kamenNaCeste(pole, moveTo, Field.Direction.RU))
                    return false;
            }
            else if (rozdilX < 0){
                if(kamenNaCeste(pole, moveTo, Field.Direction.LU))
                    return false;
            }
        }
        else if(rozdilY < 0){
            if(rozdilX > 0){
                if(kamenNaCeste(pole, moveTo, Field.Direction.RD))
                    return false;
            }
            else if (rozdilX < 0){
                if(kamenNaCeste(pole, moveTo, Field.Direction.LD))
                    return false;
            }
        }
        return true;
    }
}
