/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro jezdce v šachu
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game.chess;

import ija2019.game.Field;
import ija2019.game.Figure;

/**
 * Třída pro jezdce v šachu
 */
public class Knight extends Figure {

    /**
     * Vytvoří figurku dané barvy
     * @param isWhite - true pro bílou figurku, false pro černou
     */
    public Knight(boolean isWhite) {
        super(isWhite);
        znakFigurky = 'J';
    }

    /** Funkce řekne, jestli figurka dokáže dojít na zadané políčko.
     * 
     * @param moveTo - políčko, kam se má figurka pohnout
     * @return True, pokud se figurka může pohnout na toto políčko.
     */
    @Override
    public boolean canIGoTo(Field moveTo) {
        if(pole == null)
            return false;
        Field levyhorni = pole.nextField(Field.Direction.LU);
        Field pravyhorni = pole.nextField(Field.Direction.RU);
        Field levydolni = pole.nextField(Field.Direction.LD);
        Field pravdydolni = pole.nextField(Field.Direction.RD);
                
        if(muzeNa(moveTo) && ((levyhorni != null && (levyhorni.nextField(Field.Direction.U) == moveTo || levyhorni.nextField(Field.Direction.L) == moveTo))
        || (pravyhorni != null && (pravyhorni.nextField(Field.Direction.U) == moveTo || pravyhorni.nextField(Field.Direction.R) == moveTo))
        || (levydolni != null && (levydolni.nextField(Field.Direction.D) == moveTo || levydolni.nextField(Field.Direction.L) == moveTo))
        || (pravdydolni != null && (pravdydolni.nextField(Field.Direction.D) == moveTo || pravdydolni.nextField(Field.Direction.R) == moveTo))))
            return true;
        return false;
    }
}
