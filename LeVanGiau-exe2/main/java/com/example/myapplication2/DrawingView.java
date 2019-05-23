package com.example.myapplication2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    private Paint drawPaint;
    public Point point = new Point();
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
        point = new Point();
    }

    public  void setupPaint()
    {
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPaint.setColor(Color.BLUE);
        canvas.drawCircle(point.x, point.y, 20, drawPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX =  event.getX();
        float touchY = event.getY();
        point = new Point(Math.round(touchX),  Math.round(touchY));
        invalidate();
        return true;
    }

    public void move(int dk)
    {
        switch (dk)
        {
            case 1:
                //len
                point.y -=10;
                break;
            case 2:
                //xuong
                point.y +=10;
                break;
            case 3:
                //trai
                point.x -=10;
                break;
            case 4:
                //phai
                point.x +=10;
                break;
        }
        invalidate();

    }
}
