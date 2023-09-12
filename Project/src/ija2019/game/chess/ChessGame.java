/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro hru v šachu
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game.chess;

import ija2019.game.Board;
import ija2019.game.Field;
import ija2019.game.Figure;
import ija2019.game.Game;
import ija2019.game.Tah;

/**
 * Třída pro hru v šachu
 */
public class ChessGame extends Game{
    
    /**
     * Vytvoří hru šachů a rozestaví na desku figurky.
     * @param board 
     */
    public ChessGame(Board board) {
        super(board);
        figures.add(deska.getField(1, 1).put(new Vez(true)));
        figures.add(deska.getField(8, 1).put(new Vez(true)));
        figures.add(deska.getField(2, 1).put(new Knight(true)));
        figures.add(deska.getField(7, 1).put(new Knight(true)));
        figures.add(deska.getField(3, 1).put(new Bishop(true)));
        figures.add(deska.getField(6, 1).put(new Bishop(true)));
        figures.add(deska.getField(4, 1).put(new Queen(true)));
        figures.add(deska.getField(5, 1).put(new King(true)));
        for(int i = 1; i <= 8; i++){
            figures.add(deska.getField(i, 2).put(new Pesec(true)));
        }
        
        figures.add(deska.getField(1, 8).put(new Vez(false)));
        figures.add(deska.getField(8, 8).put(new Vez(false)));
        figures.add(deska.getField(2, 8).put(new Knight(false)));
        figures.add(deska.getField(7, 8).put(new Knight(false)));
        figures.add(deska.getField(3, 8).put(new Bishop(false)));
        figures.add(deska.getField(6, 8).put(new Bishop(false)));
        figures.add(deska.getField(4, 8).put(new Queen(false)));
        figures.add(deska.getField(5, 8).put(new King(false)));
        for(int i = 1; i <= 8; i++){
            figures.add(deska.getField(i, 7).put(new Pesec(false)));
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
        check = false;
        vyhozeni = false;
        Field pred = figure.getField();
        Figure budeSebrana = field.get();
        vyhozeni = budeSebrana != null;
        boolean pohnulSe = figure.move(field);
        String state = "";
        if(pohnulSe){
            if(!figure.isWhite())
                cisloTahu++;
            state = generateState(figure, pred, field, vyhozeni, '\0');
            if(figure.getChar() == 'p' && field.getRow() == (figure.isWhite() ? 7 : 0)){
                Pesec pesak = (Pesec) figure;
                state = generateState(figure, pred, field, vyhozeni, promoteTo);
                figures.add(pesak.promote(promoteTo));
            }
        }
        Figure mujKral = null;
        Figure enemyKral = null;
        for(Figure fig : figures){
            if (fig.getChar() == 'K' && fig.isWhite() == figure.isWhite()){
                mujKral = fig;
            }
            if (fig.getChar() == 'K' && fig.isWhite() != figure.isWhite()){
                enemyKral = fig;
            }
        }
        for(Figure fig : figures){
            if(fig.canIGoTo(mujKral.getField())){
                tahy.push(new Tah(pred, figure, field, budeSebrana, null, ""));
                undo();
                retahy.pop();
                pohnulSe = false;
            }
        }
        for(Figure fig : figures){
            if(fig.canIGoTo(enemyKral.getField())){
                check = true;
            }
        }
        if(check)
            state += "+";
        if(pohnulSe){
            tahy.push(new Tah(pred, figure, field, budeSebrana, null, state));
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
        if(!figurka.isWhite())
            cisloTahu--;
        tah.setFigurka(tah.po().get());
        retahy.push(tah);
        tah.po().remove(tah.po().get());
        tah.pred().put(figurka);
        if(tah.druhaFigurka()!= null)
            tah.po().put(tah.druhaFigurka());
        return true;
    }
    
    /**
     * Znovu provede vrácený tah.
     * @return True, pokud se redo provedlo (zásobník retahů nebyl prázdný)
     */
    @Override
    public boolean redo(){
        if(retahy.isEmpty())
            return false;
        Tah tah = retahy.pop();
        Figure figurka = tah.getFigurka();
        if(!figurka.isWhite())
            cisloTahu++;
        tah.setFigurka(tah.pred().get());
        tahy.push(tah);
        tah.pred().remove(tah.pred().get());
        tah.po().put(figurka);
        return true;
    }
    
    private String generateState(Figure figure, Field from, Field to, boolean vyhozeni, char povyseni){
        String result = figure.isWhite() ? cisloTahu + ". " : " ";
        result += figure.getChar() + "" + (char) (from.getCol()+'a') + "" + (from.getRow()+1);
        if(vyhozeni)
            result += "x";
        result += (char)(to.getCol()+'a') + "" + (to.getRow()+1);
        if(povyseni != '\0'){
            result += povyseni;
        }
        return result;
    }
}
