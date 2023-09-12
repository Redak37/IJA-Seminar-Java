/*
 * IJA 2018/2019
 * Ukol 1
 */
package ija.ija2018.homework1.board;

/**
 *
 * @author radek
 */
public class Disk {
    
    private final boolean white;

    public Disk(boolean isWhite) {
        this.white = isWhite;
    }

    public boolean isWhite() {
        return this.white;
    }

    public boolean move(Field moveTo) {
        Field tmp = moveTo;
        while (tmp != null) {
            if (tmp.get() == this) {
                tmp.remove(this);
                moveTo.put(this);
                return true;
            } else if (!tmp.isEmpty())
                break;
            else
                tmp = tmp.nextField(Field.Direction.R);
        }
        tmp = moveTo;
        while (tmp != null) {
            if (tmp.get() == this) {
                tmp.remove(this);
                moveTo.put(this);
                return true;
            } else if (!tmp.isEmpty())
                break;
            else
                tmp = tmp.nextField(Field.Direction.U);
        }
        tmp = moveTo;
        while (tmp != null) {
            if (tmp.get() == this) {
                tmp.remove(this);
                moveTo.put(this);
                return true;
            } else if (!tmp.isEmpty())
                break;
            else
                tmp = tmp.nextField(Field.Direction.L);
        }
        tmp = moveTo;
        while (tmp != null) {
            if (tmp.get() == this) {
                tmp.remove(this);
                moveTo.put(this);
                return true;
            } else if (!tmp.isEmpty())
                break;
            else
                tmp = tmp.nextField(Field.Direction.D);
        }
        
        return false;
    }
    
}
