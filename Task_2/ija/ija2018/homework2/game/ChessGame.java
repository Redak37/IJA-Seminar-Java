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
public class ChessGame implements Game {
    Stack S = new Stack();
    
    public ChessGame(Board board) {
        for (int i = 1; i <= 8; i++) {
            board.getField(i, 2).put(new Disk(true, 'P'));
            board.getField(i, 7).put(new Disk(false, 'P'));
        }

        board.getField(1, 1).put(new Disk(true, 'V'));
        board.getField(8, 1).put(new Disk(true, 'V'));
        board.getField(1, 8).put(new Disk(false, 'V'));
        board.getField(8, 8).put(new Disk(false, 'V'));
    }
    
        /*figure = board.getField(3, 7).get();
        field = board.getField(3, 8);*/
    @Override
    public boolean move(Figure figure, Field field) {
        Disk disk = (Disk) figure;
        if (field == null)
            return false;
        if (figure.getType() == 'P') {
            if (!figure.isWhite() && field.nextField(Direction.D) != null) {
                disk = (Disk) field.nextField(Direction.D).get();
                if (disk == figure && field.isEmpty()) {
                    this.S.push(field.nextField(Direction.D).get());
                    this.S.push(field.nextField(Direction.D));
                    this.S.push(field.get());
                    this.S.push(field);
                    field.nextField(Direction.D).remove();
                    field.put(disk);
                    return true;
                }
            }
            
            if (figure.isWhite() && field.nextField(Direction.U) != null) {
                disk = (Disk) field.nextField(Direction.U).get();
                if (disk == figure && field.isEmpty()) {
                    this.S.push(field.nextField(Direction.U).get());
                    this.S.push(field.nextField(Direction.U));
                    this.S.push(field.get());
                    this.S.push(field);
                    field.nextField(Direction.U).remove();
                    field.put(disk);
                    return true;
                }
            }
        }
        else if (figure.getType() == 'V') {
            Field field2 = field.nextField(Direction.D);
            while (field2 != null) {
                if (!field2.isEmpty())
                    break;
                field2 = field2.nextField(Direction.D);
            }
            if (field2 == null || field2.get() != figure)
                field2 = field.nextField(Direction.U);
            while (field2 != null) {
                if (!field2.isEmpty())
                    break;
                field2 = field2.nextField(Direction.U);
            }
            if (field2 == null || field2.get() != figure)
                field2 = field.nextField(Direction.R);
            while (field2 != null) {
                if (!field2.isEmpty())
                    break;
                field2 = field2.nextField(Direction.R);
            }
            if (field2 == null || field2.get() != figure)
                field2 = field.nextField(Direction.L);
            while (field2 != null) {
                if (!field2.isEmpty())
                    break;
                field2 = field2.nextField(Direction.L);
            }
            if (field2 != null && field2.get() == figure && (field.isEmpty() || field.get().isWhite() != figure.isWhite())) {
                this.S.push(field2.get());
                this.S.push(field2);
                this.S.push(field.get());
                this.S.push(field);
                disk = (Disk) figure;
                field2.remove();
                field.put(disk);
                return true;
            }
            
        }
        
        return false;
    }

    @Override
    public void undo() {
        if (!this.S.empty()){
            Field newField = (Field) this.S.pop();
            Disk figure1 = (Disk) this.S.pop();
            Field oldField = (Field) this.S.pop();
            Disk figure2 = (Disk) this.S.pop();
            newField.put(figure1);
            oldField.put(figure2);
        }
    }
}
