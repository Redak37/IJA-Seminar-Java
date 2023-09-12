/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro krále v šachu
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game.chess;

import ija2019.game.Field;
import ija2019.game.Figure;

/**
 * Třída pro krále v šachu
 */
public class King extends Figure{

    /**
     * Vytvoří figurku dané barvy
     * @param isWhite - true pro bílou figurku, false pro černou
     */
    public King(boolean isWhite) {
        super(isWhite);
        znakFigurky = 'K';
    }

    /** Funkce řekne, jestli figurka dokáže dojít na zadané políčko.
     * 
     * @param moveTo - políčko, kam se má figurka pohnout
     * @return True, pokud se figurka může pohnout na toto políčko.
     */
    @Override
    public boolean canIGoTo(Field moveTo) {
        return muzeNa(moveTo) && (moveTo == pole.nextField(Field.Direction.D) || moveTo == pole.nextField(Field.Direction.L) || moveTo == pole.nextField(Field.Direction.LD)
            || moveTo == pole.nextField(Field.Direction.LU) || moveTo == pole.nextField(Field.Direction.R) || moveTo == pole.nextField(Field.Direction.RD)
            || moveTo == pole.nextField(Field.Direction.RU) || moveTo == pole.nextField(Field.Direction.U));
    }
}
