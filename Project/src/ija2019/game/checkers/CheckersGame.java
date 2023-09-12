/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro hru v checkers
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game.checkers;

import ija2019.game.Board;
import ija2019.game.Field;
import ija2019.game.Figure;
import ija2019.game.Game;
import ija2019.game.Tah;

/**
 * Třída pro hru v checkers
 */
public class CheckersGame extends Game{
    private boolean whiteOnMove = false;
    
    /**
     * Vytvoří hru Checkers a rozestaví na desku figurky.
     * @param board 
     */
    public CheckersGame(Board board) {
        super(board);
        for (int i = 1; i <= 8; i+=2){
            figures.add(deska.getField(i, 1).put(new CheckerPawn(true)));
        }
        for (int i = 2; i <=8; i+=2){
            figures.add(deska.getField(i, 2).put(new CheckerPawn(true)));
        }
        for (int i = 1; i <= 8; i+=2){
            figures.add(deska.getField(i, 3).put(new CheckerPawn(true)));
        }
        
        for (int i = 2; i <=8; i+=2){
            figures.add(deska.getField(i, 8).put(new CheckerPawn(false)));
        }
        for (int i = 1; i <= 8; i+=2){
            figures.add(deska.getField(i, 7).put(new CheckerPawn(false)));
        }
        for (int i = 2; i <=8; i+=2){
            figures.add(deska.getField(i, 6).put(new CheckerPawn(false)));
        }
    }

    
    /**
     * Provede pohyb s figurkou na políčko.
     * @param figure - hýbaná figurka
     * @param field - cílové políčko
     * @param userMove - true, pokud se jedná o tah, který provedl uživatel. V tomto případě se vyprázdní vektor retahů pro redo()
     * @return true, pokud byl pohyb úspěšný
     */
    @Override
    public boolean move(Figure figure, Field field, boolean userMove) {
        Field pred = figure.getField();
        Figure budeSebrana;
        if(pred.nextField(Field.Direction.LU) == field ||
           pred.nextField(Field.Direction.LD) == field ||
           pred.nextField(Field.Direction.RU) == field ||
           pred.nextField(Field.Direction.RD) == field)
            budeSebrana = null;
        else{
            if(pred.nextField(Field.Direction.LU) != null && pred.nextField(Field.Direction.LU).nextField(Field.Direction.LU) == field)
                budeSebrana = pred.nextField(Field.Direction.LU).get();
            else if(pred.nextField(Field.Direction.LD) != null && pred.nextField(Field.Direction.LD).nextField(Field.Direction.LD) == field)
                budeSebrana = pred.nextField(Field.Direction.LD).get();
            else if(pred.nextField(Field.Direction.RD) != null && pred.nextField(Field.Direction.RD).nextField(Field.Direction.RD) == field)
                budeSebrana = pred.nextField(Field.Direction.RD).get();
            else if(pred.nextField(Field.Direction.RU) != null && pred.nextField(Field.Direction.RU).nextField(Field.Direction.RU) == field)
                budeSebrana = pred.nextField(Field.Direction.RU).get();
            else
                return false;
        }
        Field polickoVyhozene = null;
        if(budeSebrana != null)
            polickoVyhozene = budeSebrana.getField();
        boolean pohnulSe = figure.move(field);
        String state = "";
        if(pohnulSe){
            if(figure.isWhite() == whiteOnMove){
                for(Tah move : tahy){
                    if(move.getFigurka().isWhite() == whiteOnMove){
                        move.setJumps(move.getJumps() + 1);
                    }
                    else{
                        break;
                    }
                }
            }
            whiteOnMove = figure.isWhite();
            if(budeSebrana == null){
                nextMoveWhite = !whiteOnMove;
            }
            else{
                boolean neprosly = true;
                if(field.nextField(Field.Direction.LU) != null && figure.canIGoTo(field.nextField(Field.Direction.LU).nextField(Field.Direction.LU)))
                    neprosly = false;
                if(field.nextField(Field.Direction.LD) != null && figure.canIGoTo(field.nextField(Field.Direction.LD).nextField(Field.Direction.LD)))
                    neprosly = false;
                if(field.nextField(Field.Direction.RU) != null && figure.canIGoTo(field.nextField(Field.Direction.RU).nextField(Field.Direction.RU)))
                    neprosly = false;
                if(field.nextField(Field.Direction.RD) != null && figure.canIGoTo(field.nextField(Field.Direction.RD).nextField(Field.Direction.RD)))
                    neprosly = false;
                if(neprosly)
                    nextMoveWhite = !whiteOnMove;
            }
            state = generateState(figure, pred, field);
            if(budeSebrana == null)
                tahy.push(new Tah(pred, figure, field, null, null, state, ((CheckerPawn)figure).isKing()));
            else
                tahy.push(new Tah(pred, figure, field, budeSebrana, polickoVyhozene, state, ((CheckerPawn)figure).isKing()));
            if(figure.getChar() == 'p' && field.getRow() == (figure.isWhite() ? 7 : 0)){
                CheckerPawn pesak = (CheckerPawn) figure;
                pesak.setKing(true);
            }
            if(userMove){
                retahy.clear();
            }
        }
        return pohnulSe;
    }

    /**
     * Vrátí předchozí tah.
     * @return True, pokud se undo provedlo (zásobník tahů nebyl prázdný)
     */
    @Override
    public boolean undo() {
        if(tahy.isEmpty())
            return false;
        Tah tah = tahy.pop();
        Figure figurka = tah.getFigurka();
        ((CheckerPawn) figurka).setKing(tah.isKing());
        tah.setFigurka(tah.po().get());
        retahy.push(tah);
        tah.po().remove(tah.po().get());
        tah.pred().put(figurka);
        if(tah.druhaFigurka()!= null)
            tah.polickoDruheFigurky().put(tah.druhaFigurka());
        if(!tahy.isEmpty() && tahy.peekFirst().getJumps() > 0)
            return undo();
        return true;
    }

    /**
     * Znovu provede vrácený tah.
     * @return True, pokud se redo provedlo (zásobník retahů nebyl prázdný)
     */
    @Override
    public boolean redo() {
        if(retahy.isEmpty())
            return false;
        Tah tah = retahy.pop();
        Figure figurka = tah.getFigurka();
        if(tah.po().getRow() == (figurka.isWhite() ? 7 : 0))
            ((CheckerPawn) figurka).setKing(true);
        tah.setFigurka(tah.pred().get());
        tahy.push(tah);
        tah.pred().remove(tah.pred().get());
        tah.po().put(figurka);
        if(tah.druhaFigurka()!= null)
            tah.polickoDruheFigurky().remove(tah.druhaFigurka());
        if(tah.getJumps() > 0)
            return redo();
        return true;
    }
    
    private String generateState(Figure figure, Field from, Field to) {
        String result = figure.getChar() + "" + (char) (from.getCol()+'a') + "" + (from.getRow()+1) + "" + (char)(to.getCol()+'a') + "" + (to.getRow()+1);
        return result;
    }
}
