package com.example.w0269431.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AnimatedView extends ImageView {


    private Context mContext;
    private Handler h;
    private final int FRAME_RATE = 30;



    public AnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        h = new Handler();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas c) {


        // c.drawColor(Color.BLACK);
        h.postDelayed(r, FRAME_RATE);
    }

}