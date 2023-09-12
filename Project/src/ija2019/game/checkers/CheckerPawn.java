/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro figurku v checkers (pěšáka i jeho povýšenou podobu)
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game.checkers;

import ija2019.game.Field;
import ija2019.game.Figure;

/**
 * Třída pro figurku v checkers (pěšáka i jeho povýšenou podobu)
 */
public class CheckerPawn extends Figure{
    
    /**
     * Vytvoří figurku dané barvy
     * @param isWhite - true pro bílou figurku, false pro černou
     */
    public CheckerPawn(boolean isWhite) {
        super(isWhite);
        znakFigurky = 'p';
    }

    /** Funkce řekne, jestli figurka dokáže dojít na zadané políčko.
     * 
     * @param moveTo - políčko, kam se má figurka pohnout
     * @return True, pokud se figurka může ponout na toto políčko.
     */
    @Override
    public boolean canIGoTo(Field moveTo) {
        if(pole == null || moveTo == null)
            return false;
        if(moveTo.isEmpty()){
            if(isWhite() || isKing()){
                if(pole.nextField(Field.Direction.LU) == moveTo ||
                   pole.nextField(Field.Direction.RU) == moveTo)
                    return true;
                if(pole.nextField(Field.Direction.LU) != null && pole.nextField(Field.Direction.LU).nextField(Field.Direction.LU) == moveTo
                    && !pole.nextField(Field.Direction.LU).isEmpty() && pole.nextField(Field.Direction.LU).get().isWhite() != isWhite())
                    return true;
                if(pole.nextField(Field.Direction.RU) != null && pole.nextField(Field.Direction.RU).nextField(Field.Direction.RU) == moveTo
                    && !pole.nextField(Field.Direction.RU).isEmpty() && pole.nextField(Field.Direction.RU).get().isWhite() != isWhite())
                    return true;
            }
            if(!isWhite() || isKing()){
                if(pole.nextField(Field.Direction.LD) == moveTo ||
                   pole.nextField(Field.Direction.RD) == moveTo)
                    return true;
                if(pole.nextField(Field.Direction.LD) != null && pole.nextField(Field.Direction.LD).nextField(Field.Direction.LD) == moveTo
                    && !pole.nextField(Field.Direction.LD).isEmpty() && pole.nextField(Field.Direction.LD).get().isWhite() != isWhite())
                    return true;
                if(pole.nextField(Field.Direction.RD) != null && pole.nextField(Field.Direction.RD).nextField(Field.Direction.RD) == moveTo
                    && !pole.nextField(Field.Direction.RD).isEmpty() && pole.nextField(Field.Direction.RD).get().isWhite() != isWhite())
                    return true;
            }
        }
        return false;
    }

    /** Funkce která v základu otestuje pohyb na cíl přes canIGoTo a pak se pohne na cílové políčko a popřípadě vyhodí přeskočené figurky.
     * 
     * @param moveTo - políčko, kam se má figurka pohnout
     * @return True, pokud byl pohyb úspěšný.
     */
    @Override
    public boolean move(Field moveTo) {
        if(! canIGoTo(moveTo))
            return false;
        //Vyhození
        if(pole.nextField(Field.Direction.LU) != null && pole.nextField(Field.Direction.LU).nextField(Field.Direction.LU) == moveTo)
            pole.nextField(Field.Direction.LU).remove(pole.nextField(Field.Direction.LU).get());
        else if(pole.nextField(Field.Direction.LD) != null && pole.nextField(Field.Direction.LD).nextField(Field.Direction.LD) == moveTo)
            pole.nextField(Field.Direction.LD).remove(pole.nextField(Field.Direction.LD).get());
        else if(pole.nextField(Field.Direction.RD) != null && pole.nextField(Field.Direction.RD).nextField(Field.Direction.RD) == moveTo)
            pole.nextField(Field.Direction.RD).remove(pole.nextField(Field.Direction.RD).get());
        else if(pole.nextField(Field.Direction.RU) != null && pole.nextField(Field.Direction.RU).nextField(Field.Direction.RU) == moveTo)
            pole.nextField(Field.Direction.RU).remove(pole.nextField(Field.Direction.RU).get());
        
        pole.remove(this);
        moveTo.put(this);
        return true;
    }
    
    /**
     * @return Vrátí, zda je tato figurka Král
     */
    public boolean isKing(){
        return znakFigurky == 'K';
    }
    
    /**
     * Nastaví, zda je tato figurka král. Změní znak, který ji reprezentuje.
     * @param king true pokud se má figurka stát králem
     */
    public void setKing(boolean king){
        this.znakFigurky = king ? 'K' : 'p';
    }
}
