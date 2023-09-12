/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Field.Direction;
import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.common.Game;
import java.util.Stack;

/**
 *
 * @author radek
 */
public class CheckersGame implements Game {
    Stack S = new Stack();
    
    public CheckersGame(Board board) {
        for (int i = 1; i <= 8; i++) {
            board.getField(i, 1).put(new Disk(true, 'P'));
            board.getField(i, 2).put(new Disk(true, 'P'));
        }
    }

    @Override
    public boolean move(Figure figure, Field field) {
            Disk disk = (Disk) field.nextField(Direction.LU).get();
        if (disk == figure && field.isEmpty()) {
            this.S.push(field.nextField(Direction.LU));
            this.S.push(figure);
            this.S.push(field);
            field.nextField(Direction.LU).remove();
            field.put(disk);
            return true;
        }
        
        disk = (Disk) field.nextField(Direction.RU).get();
        if (disk == figure && field.isEmpty()) {
            this.S.push(field.nextField(Direction.RU));
            this.S.push(figure);
            this.S.push(field);
            field.nextField(Direction.RU).remove();
            field.put(disk);
            return true;
        }
        
        disk = (Disk) field.nextField(Direction.LD).get();
        if (disk == figure && field.isEmpty()) {
            this.S.push(field.nextField(Direction.LD));
            this.S.push(figure);
            this.S.push(field);
            field.nextField(Direction.LD).remove();
            field.put(disk);
            return true;
        }
        
        disk = (Disk) field.nextField(Direction.RD).get();
        if (disk == figure && field.isEmpty()) {
            this.S.push(field.nextField(Direction.RD));
            this.S.push(figure);
            this.S.push(field);
            field.nextField(Direction.RD).remove();
            field.put(disk);
            return true;
        }
        
        return false;
        
    }

    @Override
    public void undo() {
        if (!this.S.empty()){
            Field newField = (Field) this.S.pop();
            Disk figure = (Disk) this.S.pop();
            Field oldField = (Field) this.S.pop();
            newField.remove();
            oldField.put(figure);
        }
    }
    
}
