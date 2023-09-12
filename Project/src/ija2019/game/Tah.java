/**
 * IJA 2018/2019
 * Projekt - Šachy/Dáma
 * 
 * Třída pro uložení tahu
 * 
 * @author Radek Duchoň (xducho07)
 * @author Jan Juda (xjudaj00)
 * @author Josef Oškera (xosker03)
 */
package ija2019.game;

/**
 * Třída pro uložení tahu
 */
public class Tah {
    //Políčko na které stála figurka před pohybem. V rošádě je to předchozí políčko krále.
    private Field pred;
    //Figurka, která se hýbe
    private Figure figurka;
    //Políčko kam se táhlo. Při normálním vyhazování je to políčko kde stála vyhozená figurka. Při rošádě je to cílové políčko krále.
    private Field po;
    //Vyhozená figurka nebo druhá figurka rošády.
    private Figure druhaFigurka;
    //Políčko vyhozené figurky JEN PŘI BRANÍ MIMOCHODEM nebo původní políčko druhé figurky rošády.
    private Field polickoDruheFigurky;
    
    private String state;
    private int jumpCount = 0;
    private boolean king = false;
    
    /**
     * Uloží tah
     * @param pred - Políčko na které stála figurka před pohybem.
     * @param figurka - Figurka, která se hýbe
     * @param po - Políčko kam se táhlo.
     * @param druhaFigurka - Vyhozená figurka nebo druhá figurka rošády.
     * @param polickoDruheFigurky - Políčko vyhozené figurky, pokud není shodné s cílovým políčkem.
     * @param state - Textová reprezentace tohoto tahu
     */
    public Tah(Field pred, Figure figurka, Field po, Figure druhaFigurka, Field polickoDruheFigurky, String state) {
        this(pred, figurka, po, druhaFigurka, polickoDruheFigurky, state, false);
    }

    /**
     * Uloží tah
     * @param pred - Políčko na které stála figurka před pohybem.
     * @param figurka - Figurka, která se hýbe
     * @param po - Políčko kam se táhlo.
     * @param druhaFigurka - Vyhozená figurka nebo druhá figurka rošády.
     * @param polickoDruheFigurky - Políčko vyhozené figurky, pokud není shodné s cílovým políčkem.
     * @param state - Textová reprezentace tohoto tahu
     * @param king - zda byla figurka v Checkers před provedením tahu král. Pouze Checkers
     */
    public Tah(Field pred, Figure figurka, Field po, Figure druhaFigurka, Field polickoDruheFigurky, String state, boolean king) {
        this.pred = pred;
        this.figurka = figurka;
        this.po = po;
        this.druhaFigurka = druhaFigurka;
        this.polickoDruheFigurky = polickoDruheFigurky;
        this.state = state;
        this.king = king;
    }
    
    /**
     * @return Vrací políčko na které stála figurka před pohybem.
     */
    public Field pred(){
        return pred;
    }
    
    /**
     * @return Vrací políčko kam se táhlo.
     */
    public Field po(){
        return po;
    }
    
    /**
     * Vrátí figurku se kterou se táhlo. V případě povyšování pěšce.
     * @return 
     */
    public Figure getFigurka(){
        return figurka;
    }
    
    /**
     * Vrátí figurku, která stojí na cílovém poli.
     * @return 
     */
    public Figure getFinishFigure(){
        return po.get();
    }
    
    /**
     * Nastaví figurku tahu
     * @param figurka 
     */
    public void setFigurka(Figure figurka){
        this.figurka = figurka;
    }
    
    /**
     * @return Vrátí druhou figurku (vyhozenou).
     */
    public Figure druhaFigurka(){
        return druhaFigurka;
    }
    
    /**
     * @return Vrátí políčko druhou figurky pokud se liší od cílového.
     */
    public Field polickoDruheFigurky(){
        return polickoDruheFigurky;
    }
    
    /**
     * @return Vrátí textovou reprezentaci tahu
     */
    public String getState(){
        return state;
    }
    
    /**
     * Nastaví textovou reprezentaci tahu
     * @param state 
     */
    public void setState(String state){
        this.state = state;
    }
    
    /**
     * @return Vrátí kolikrát se už v tomto tahu skočilo. Pouze Checkers
     */
    public int getJumps(){
        return jumpCount;
    }
    /**
     * Nastaví kolikrát se už v tomto tahu skočilo. Pouze Checkers
     * @param jumps 
     */
    public void setJumps(int jumps){
        jumpCount = jumps;
    }
    
    /**
     * @return Vrátí, zda figurka je král. Pouze Checkers
     */
    public boolean isKing(){
        return king;
    }
}
