/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro královnu v šachu
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game.chess;

import ija2019.game.Field;
import ija2019.game.Figure;

/**
 * Třída pro královnu v šachu
 */
public class Queen extends Figure{

    /**
     * Vytvoří figurku dané barvy
     * @param isWhite - true pro bílou figurku, false pro černou
     */
    public Queen(boolean isWhite) {
        super(isWhite);
        znakFigurky = 'D';
    }

    /** Funkce řekne, jestli figurka dokáže dojít na zadané políčko.
     * 
     * @param moveTo - políčko, kam se má figurka pohnout
     * @return True, pokud se figurka může pohnout na toto políčko.
     */
    @Override
    public boolean canIGoTo(Field moveTo) {
        if(pole == null || pole == moveTo || !muzeNa(moveTo))
            return false;
        int rozdilX = moveTo.getCol() - pole.getCol();
        int rozdilY = moveTo.getRow() - pole.getRow();
        if(pole.getCol() == moveTo.getCol() || pole.getRow() == moveTo.getRow()){ //jako věž
            if(pole.getCol() == moveTo.getCol()){
                int rozdil = pole.getRow() - moveTo.getRow();
                if(rozdil > 0){ //cílové políčko je menší -> přesun dolů
                    if(kamenNaCeste(pole, moveTo, Field.Direction.D))
                        return false;
                }
                else if(rozdil < 0){ //přesun nahoru
                    if(kamenNaCeste(pole, moveTo, Field.Direction.U))
                        return false;
                }
            }
            else if(pole.getRow() == moveTo.getRow()){
                int rozdil = pole.getCol() - moveTo.getCol();
                if(rozdil > 0){ //cílové políčko je menší -> přesun doleva
                    if(kamenNaCeste(pole, moveTo, Field.Direction.L))
                        return false;
                }
                else if(rozdil < 0){ //přesun doprava
                    if(kamenNaCeste(pole, moveTo, Field.Direction.R))
                        return false;
                }
            }
        }
        else if(Math.abs(rozdilX) == Math.abs(rozdilY)){ //jako střelec
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
        }
        else{
            return false;
        }
        return true;
    }
}
