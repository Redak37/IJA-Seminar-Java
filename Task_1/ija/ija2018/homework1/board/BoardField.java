/*
 * IJA 2018/2019
 * Ukol 1
 */
package ija.ija2018.homework1.board;

/**
 *
 * @author radek
 */
public class BoardField implements Field {
    
    public Field D, RD, R, RU, U, LU, L, LD;
    public int x, y;
    public Disk disk;

    public BoardField(int col, int row) {
        this.x = col;
        this.y = row;
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
    public Disk get() {
        return this.disk;
    }

    @Override
    public boolean isEmpty() {
        return this.disk == null;
    }

    @Override
    public boolean put(Disk disk) {
        if (this.disk != null)
            return false;
        this.disk = disk;
        return true;
    }

    @Override
    public boolean remove(Disk disk) {
        if (this.disk != disk)
            return false;
        this.disk = null;
        return true;
    }
    
}
