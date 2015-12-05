package com.nscc.gameoflife.jareds_machine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Cell {
    protected int width;//for drawing
    protected int height;//for drawing
    protected int x;//x coordinate in cellGrid
    protected int y;//y coordinate in cellGrid
    protected boolean alive;//true for alive, false for dead
    protected boolean sentinel;
    protected int age = 0;

    public Cell(int x,int y, int width,int height,boolean sentinel){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.alive = false;
        this.sentinel = sentinel;
    }

    public void lifeCheck(Cell[][] cellGrid, int myX, int myY)
    {
//        Random r = new Random();
//        int num = r.nextInt(2);
//
//        if (num == 1) {
//            alive = true;
//            age++;
//        }
//        else {
//            alive = false;
//            age = 0;
//        }

        // myX and myY is the position of this cell in the board's 2d array
        // the below positions are the top left cell in the 3x3 area around this cell
        int x = myX-1;
        int y = myY-1;

        int liveCount = 0;

        if (sentinel) {
            alive = true;
        } else {
            for(int row = x;row < x + 3;row++)
            {
                for(int col = y;col < y+3;col++)
                {
                    if (cellGrid[row][col].alive && !(cellGrid[row][col].sentinel))
                        liveCount++;
                }
            }

            if (alive) {
                if (liveCount == 2 || liveCount == 3)
                    alive = true;
                else
                    alive = false;
            } else {
                if (liveCount == 3)
                    alive = true;
            }
        }



        if (alive)
            age++;
        else
            age = 0;

    }

    void draw(Canvas c,Paint p)
    {
        if (alive) {

            // fill rect
            p.setStyle(Paint.Style.FILL);
            // set alpha value by cells age
            int alpha = 50 + (age * 10);
            if (alpha > 100)
                alpha = 100;
            p.setAlpha(alpha);

            // draw cell
            c.drawRect(this.x, this.y, x + this.width, y + this.height, p);

        } else {
//            p.setStyle(Paint.Style.FILL);
//            p.setColor(Color.BLACK);
//            c.drawRect(this.x, this.y, x+this.width, y+this.height, p);
        }
    }
}
