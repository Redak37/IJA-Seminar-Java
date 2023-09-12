/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Figure;

/**
 *
 * @author radek
 */
public class Disk implements Figure{
    
    private final boolean white;
    private char type;
    private int x, y;

    public Disk(boolean isWhite, char type) {
        this.white = isWhite;
        this.type = type;
    }

    @Override
    public boolean isWhite() {
        return this.white;
    }

    @Override
    public String getState() {
        if (this.isWhite())
            return this.type + "[W]" + this.x + ":" + this.y;
        else
            return this.type + "[B]" + this.x + ":" + this.y;
    }

    void setType(char type) {
        this.type = type;
    }

    @Override
    public char getType() {
        return this.type;
    }

    void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
}
