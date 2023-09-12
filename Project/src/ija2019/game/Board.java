/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro šachovnici
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */

package ija2019.game;
/**
 * Třída pro šachovnici
 */
public class Board {
    private int velikost;
    private Field pole[][];
    
    /**
     * Vytvoří hrací desku s políčkami
     * @param size - velikost čtvercové desky
     */
    public Board(int size){
        velikost = size;
        pole = new Field[size][size];
        //Prvně vytvoříme pole všech políček
        for(int s = 0; s < size; s++){
            for(int r = 0; r < size; r++){
                pole[s][r] = new Field(s, r);
            }
        }
        //A poté políčkům dáme informace o jejich sousedech
        for(int s = 0; s < size; s++){
            for(int r = 0; r < size; r++){
                if(jeNaPlose(s, r-1))
                    pole[s][r].addNextField(Field.Direction.D, pole[s][r-1]);
                if(jeNaPlose(s-1, r))
                    pole[s][r].addNextField(Field.Direction.L, pole[s-1][r]);
                if(jeNaPlose(s-1, r-1))
                    pole[s][r].addNextField(Field.Direction.LD, pole[s-1][r-1]);
                if(jeNaPlose(s-1, r+1))
                    pole[s][r].addNextField(Field.Direction.LU, pole[s-1][r+1]);
                if(jeNaPlose(s+1, r))
                    pole[s][r].addNextField(Field.Direction.R, pole[s+1][r]);
                if(jeNaPlose(s+1, r-1))
                    pole[s][r].addNextField(Field.Direction.RD, pole[s+1][r-1]);
                if(jeNaPlose(s+1, r+1))
                    pole[s][r].addNextField(Field.Direction.RU, pole[s+1][r+1]);
                if(jeNaPlose(s, r+1))
                    pole[s][r].addNextField(Field.Direction.U, pole[s][r+1]);
            }
        }
    }
    
    /** Funkce kontroluje, zda se políčko se zadanými indexy nachází na hrací ploše nebo je mimo
     * @param s - sloupec
     * @param r - řádek
     * @return - funkce vrací true, pokud indexy políčka spadají do hrací plochy, jinak vrací false
     */
    private boolean jeNaPlose(int s, int r){
        return s >= 0 && s < velikost && r >= 0 && r < velikost;
    }
    
    /**
     * Vrátí políčko na daných souřadnicích, zadáváno 1-8
     * @param col - sloupec
     * @param row - řádek
     * @return políčko na souřadnicích
     */
    public Field getField(int col, int row){
        return pole[col-1][row-1];
    }
    
    /**
     * @return Vrátí velikost této desky
     */
    public int getSize(){
        return velikost;
    }
}
