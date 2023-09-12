/*
 * IJA 2018/2019
 * Ukol 2
 */
package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;

/**
 *
 * @author radek
 */
public class BoardField implements Field {
    
    public Field D, RD, R, RU, U, LU, L, LD;
    public int x, y;
    public Disk disk;

    public BoardField(int col, int row) {
        this.x = col+1;
        this.y = row+1;
        this.disk = null;
    }

    @Override
    public void addNextField(Field.Direction dirs, Field field) {
        switch (dirs) {
            case D:
                this.D = field;
            case RD:
                this.RD = field;
            case R:
                this.R = field;
            case RU:
                this.RU = field;
            case U:
                this.U = field;
            case LU:
                this.LU = field;
            case L:
                this.L = field;
            case LD:
                this.LD = field;
            default:
        }
    }

    @Override
    public Field nextField(Field.Direction dirs) {
        switch (dirs) {
            case D:
                return this.D;
            case RD:
                return this.RD;
            case R:
                return this.R;
            case RU:
                return this.RU;
            case U:
                return this.U;
            case LU:
                return this.LU;
            case L:
                return this.L;
            case LD:
                return this.LD;
            default:
                return null;
        }
    }

    @Override
    public Figure get() {
        return this.disk;
    }

    @Override
    public boolean isEmpty() {
        return this.disk == null;
    }

    @Override
    public boolean put(Disk disk) {
        this.disk = disk;
        if (disk != null)
            this.disk.setPos(this.x, this.y);
        return true;
    }

    @Override
    public boolean remove() {
        this.disk = null;
        return true;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
    
}
