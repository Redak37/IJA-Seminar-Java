/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Abstraktní třída pro figurky
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game;

/**
 * Abstraktní třída pro figurky
 */
public abstract class Figure{
    private boolean jeBily;
    protected Field pole;
    protected char znakFigurky = 'X';
    
    /**
     * Vytvoří figurku dané barvy
     * @param isWhite - true pro bílou figurku, false pro černou
     */
    public Figure(boolean isWhite){
        jeBily = isWhite;
        pole = null;
    }
    
    /**
     * @return True, pokud je figurka bílá.
     */
    public boolean isWhite(){
        return jeBily;
    }
    
    /**
     * @return Vrací znak reprezentující typ figurky
     */
    public char getChar(){
        return znakFigurky;
    }
    
    /** Funkce pro nastavení políčka, na kterém se tenta figurka nachází. Null signalizuje, že se nenachází na žádném políčku.
     * @param field - nové políčko
     */
    public void setField(Field field){
        pole = field;
    }
    
    /**
     * @return Vrací políčko, na kterém stojí figurka.
     */
    public Field getField() {
        return pole;
    }
    
    /** Funkce implementovaná u každé figurky, která nám řekne, jestli figurka dokáže dojít na zadané políčko.
     * 
     * @param moveTo - políčko, kam se má figurka pohnout
     * @return True, pokud se figurka může ponout na toto políčko.
     */
    abstract public boolean canIGoTo(Field moveTo);
    
    /** Funkce která v základu otestuje pohyb na cíl přes canIGoTo a pak se pohne na cílové políčko a popřípadě vyhodí na něm stojící figurku.
     * 
     * @param moveTo - políčko, kam se má figurka pohnout
     * @return True, pokud byl pohyb úspěšný.
     */
    public boolean move(Field moveTo){
        if(! canIGoTo(moveTo))
            return false;
        pole.remove(this);
        moveTo.remove(moveTo.get()); //toto nám zajistí správně fungující vyhazování
        moveTo.put(this);
        return true;
    }
    
    /** Funkce pro kontrolu volné cesty
     * Pre-condition: políčka začátek a cíl patří do stejného boardu a leží ve stejném řádku, ve stejném sloupci nebo na diagonálách.
     * @param zacatek - počáteční políčko cesty
     * @param cil - koncové políčko cesty
     * @param smer - směr jakým máme cestovat
     * @return - Vrací true, pokud na cestě mezi začátkem a cílem v zadaném smětu leží figurka nebo je směr špatný, jinak vrací false.
     */
    protected boolean kamenNaCeste(Field zacatek, Field cil, Field.Direction smer){
        while((zacatek = zacatek.nextField(smer)) != cil){
            if (zacatek == null)
                return true; //Pokud figurka uteče z plochy, tak do cíle tímto směrem nedojde
            if(!zacatek.isEmpty())
                return true; //políčko není prázdné - něco je mezi zdrojovým a cílovým = konec
        }
        return false;
    }
    
    /** Funkce zkontroluje, zda zadané políčko je prázdné nebo na něm stojí figurka opačné barvy
     * Pouze pro ŠACHY
     * @param policko
     * @return true, pokud je políčko prázné nebo na něm stojí figurka opačné barvy
     */
    protected boolean muzeNa(Field policko){
        boolean result = policko.isEmpty() || policko.get().isWhite() != this.isWhite();
        return result;
    }
}
