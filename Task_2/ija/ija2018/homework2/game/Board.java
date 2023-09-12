/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;

/**
 *
 * @author radek
 */
public class Board extends java.lang.Object {
    public Field fields[][];
    protected int size;
    

    public Board(int size) {
        this.fields = new Field[size][size];
        this.size = size;
        for (int x = 0; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                this.fields[x][y] = new BoardField(x, y);
            }
        }
        for (int x = 0; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                this.fields[x][y].addNextField(Field.Direction.D, this.getNeighbor(x, y+1));
                this.fields[x][y].addNextField(Field.Direction.RD, this.getNeighbor(x+1, y+1));
                this.fields[x][y].addNextField(Field.Direction.R, this.getNeighbor(x+1, y));
                this.fields[x][y].addNextField(Field.Direction.RU, this.getNeighbor(x+1, y-1));
                this.fields[x][y].addNextField(Field.Direction.U, this.getNeighbor(x, y-1));
                this.fields[x][y].addNextField(Field.Direction.LU, this.getNeighbor(x-1, y-1));
                this.fields[x][y].addNextField(Field.Direction.L, this.getNeighbor(x-1, y));
                this.fields[x][y].addNextField(Field.Direction.LD, this.getNeighbor(x-1, y+1));
            }
        }
    }
    
    protected final Field getNeighbor(int col, int row) {
        if (col >= 0 && col < this.getSize() && row >= 0 && row < this.getSize())
            return this.fields[col][row];
        
        return null;
    }

    public Field getField(int col, int row) {
        if (col >= 1 && col <= this.getSize() && row >= 1 && row <= this.getSize())
            return this.fields[col-1][row-1];
        
        return null;
    }

    public int getSize() {
        return this.size;
    }
    
}
