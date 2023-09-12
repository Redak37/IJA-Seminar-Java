/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Vytváří jednotlivé hry
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019;

import ija2019.game.Game;
import ija2019.game.Board;
import ija2019.game.checkers.CheckersGame;
import ija2019.game.chess.ChessGame;

/**
 * Vytváří jednotlivé hry
 */
public class GameFactory {
    /**
     * Vytvoří hru šachů
     * @param board - vytvořená šachovnice velikosti minimálně 8
     * @return vytvořená hra
     */
    public static Game createChessGame(Board board){
        return new ChessGame(board);
    }
    
    /**
     * Vytvoří hru checkers
     * @param board - vytvořená šachovnice velikosti minimálně 8
     * @return vytvořená hra
     */
    public static Game createCheckersGame(Board board){
        return new CheckersGame(board);
    }
}
