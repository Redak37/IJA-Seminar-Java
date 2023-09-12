/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro pěšce v šachu
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game.chess;

import ija2019.game.Field;
import ija2019.game.Figure;

/**
 * Třída pro pěšce v šachu
 */
public class Pesec extends Figure{
    
    /**
     * Vytvoří figurku dané barvy
     * @param isWhite - true pro bílou figurku, false pro černou
     */
    public Pesec(boolean isWhite) {
        super(isWhite);
        znakFigurky = 'p';
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
        Field.Direction dopredu = (isWhite() ? Field.Direction.U : Field.Direction.D);
        Field poleDalsi = pole.nextField(dopredu);
        //pole.nextField(dopredu) nikdy nebude null, protože pěšák nemůže stát v poslední řadě při tahu
        if(muzeNa(moveTo) &&(
                (moveTo == poleDalsi && poleDalsi.isEmpty())
                || (moveTo == poleDalsi.nextField(Field.Direction.R) && !poleDalsi.nextField(Field.Direction.R).isEmpty())
                || (moveTo == poleDalsi.nextField(Field.Direction.L) && !poleDalsi.nextField(Field.Direction.L).isEmpty())
                || (moveTo == poleDalsi.nextField(dopredu) && poleDalsi.nextField(dopredu).isEmpty() && poleDalsi.isEmpty() && pole.getRow() == (isWhite() ? 1 : 6))))
            return true;
        return false;
    }
    
    /**
     * Funkce povýší pěšce na figurku identifikovanou jejím znakem. Provede výměnu na svém políčku.
     * @param naCo Znak povýšené figurky.
     * @return Vrací výslednou povýšenou figurku.
     */
    public Figure promote(char naCo){
        Field field = pole;
        pole.remove(this);
        Figure fig = null;
        switch(naCo){
            case 'D':
                fig = new Queen(isWhite());
                break;
            case 'V':
                fig = new Vez(isWhite());
                break;
            case 'J':
                fig = new Knight(isWhite());
                break;
            case 'S':
                fig = new Bishop(isWhite());
                break;
        }
        field.put(fig);
        return fig;
    }
}
