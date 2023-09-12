/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.common;

import ija.ija2018.homework2.game.BoardField;
import ija.ija2018.homework2.game.Disk;

/**
 *
 * @author radek
 */
public interface Field {
    
    void addNextField(Field.Direction dirs, Field field);
    Figure get();
    boolean isEmpty();
    Field nextField(Field.Direction dirs);
    boolean put(Disk disk);
    boolean remove();
    int getX();
    int getY();
        
    public static enum Direction {
        D, L, LD, LU, R, RD, RU, U;
    }
    
}