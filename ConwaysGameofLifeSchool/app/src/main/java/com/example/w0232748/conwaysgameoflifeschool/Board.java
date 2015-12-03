package com.example.w0232748.conwaysgameoflifeschool;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import android.os.Handler;

public class Board extends View {
    // size of grid
    private static final int HORIZONTAL_CELLS = 30;
    private static final int VERTICAL_CELLS = 30;
    // grid line objects
    private ArrayList<Line> verticalLines = new ArrayList<>();
    private ArrayList<Line> horizontalLines = new ArrayList<>();
    // drawing thread values
    private Handler h;
    private final int FRAME_RATE = 30;
    // drawing objects
    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;
    private Paint mPaint;
    private Cell[][] cellGrid;


    public Board(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        h = new Handler();

        // setup a default paint
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    // main draw method
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw Grid
        for (int i = 0;i< horizontalLines.size();i++)
        {
            horizontalLines.get(i).draw(canvas, mPaint);
        }
        for (int i = 0;i< verticalLines.size();i++)
        {
            verticalLines.get(i).draw(canvas, mPaint);
        }

        // call this method again in thread "r", after the FRAME_RATE as elasped
        h.postDelayed(r, FRAME_RATE);
    }

    // create the grid line objects
    public void setupGrid(int w, int h)
    {
        // clear anything currently there
        verticalLines.clear();
        horizontalLines.clear();

        // setup vertical lines
        int cellWidth = w / HORIZONTAL_CELLS;
        for (int i = 0; i < HORIZONTAL_CELLS; i++)
        {
            // new line
            int x1 = cellWidth * i;
            int y1 = 0; // start at top of screen
            int x2 = x1; // vertical lines have same x values
            int y2 = h; // go to bottom of page
            verticalLines.add(new Line(x1, x2, y1, y2));
        }

        // setup horizontal lines
        int cellHeight = h / VERTICAL_CELLS;
        for (int i = 0; i < VERTICAL_CELLS; i++)
        {
            // new line
            int x1 = 0;
            int y1 = cellHeight * i;
            int x2 = w;
            int y2 = y1;
            horizontalLines.add(new Line(x1, x2, y1, y2));
        }

    }

    // override onSizeChanged
    // setup canvas
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        setupGrid(w, h);
    }

    public void clearCanvas() {
        invalidate();
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        // TODO add new cells by touch here...

        return true;
    }
}