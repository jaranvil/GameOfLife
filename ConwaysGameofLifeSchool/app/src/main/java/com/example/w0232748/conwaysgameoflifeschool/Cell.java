package com.example.w0232748.conwaysgameoflifeschool;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by w0232748 on 12/3/2015.
 */
public class Cell {
    private int width;//for drawing
    private int height;//for drawing
    private int x;//x coordinate in cellGrid
    private int y;//y coordinate in cellGrid
    private boolean alive;//true for alive, false for dead
    private boolean markedToLive;//whether the cell will be set as alive during the step where cells can be set as alive/dead
    private boolean sentinel;

    Cell(int width,int height,int x,int y,boolean sentinel){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.alive = false;
        this.markedToLive = false;
        this.sentinel = sentinel;
    }

    boolean getAlive()
    {
        return this.alive;
    }

    void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    boolean getMarkedToLive()
    {
        return this.markedToLive;
    }

    void setMarkedToLive(boolean markedToLive)
    {
        this.markedToLive = markedToLive;
    }

    boolean isSentinel()
    {
        return this.sentinel;
    }

    boolean lifeCheck(Cell[][] cellGrid)
    {
        int liveCount = 0;
        //checks of surrounding cells, incrementing a count of living
        for(int x = -1;x<1;x++)
        {
            for(int y = -1;y<1;y++)
            {
                if(!(x==0 && y==0))
                {
                    if (cellGrid[this.x+x][this.y+y].getAlive() && !(cellGrid[this.x+x][this.y+y].isSentinel()))
                        liveCount++;
                }
            }
        }
        //check case on count
        if(liveCount == 2)
        {
            if(this.getAlive())
                return true;
        }
        else if(liveCount == 3)
            return true;
        return false;//else case
    }

    void Draw(Canvas c,Paint p)
    {
        c.drawRect(this.x,this.y,this.width,this.height,p);
    }



}
