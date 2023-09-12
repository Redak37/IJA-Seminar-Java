/*
 * IJA 2018/2019
 * Ukol 1
 */
package ija.ija2018.homework1.board;

/**
 *
 * @author radek
 */
public interface Field {
    
    void addNextField(Field.Direction dirs, Field field);
    Disk get();
    boolean isEmpty();
    Field nextField(Field.Direction dirs);
    boolean put(Disk disk);
    boolean remove(Disk disk);
        
    public static enum Direction {
        D, L, LD, LU, R, RD, RU, U;
    }
    
}
