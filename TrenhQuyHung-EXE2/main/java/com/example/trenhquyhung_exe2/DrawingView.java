package com.example.trenhquyhung_exe2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
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
        drawPaint.setColor(Color.RED);
        canvas.drawRect(100, 100, 200 ,200,  drawPaint);
        drawPaint.setColor(Color.BLUE);
        canvas.drawCircle(point.x, point.y, 20, drawPaint);

        Rect r = new Rect(200, 200, 400, 400);
        canvas.drawRect(r, drawPaint);
        Rect r2 = new Rect(400, 400, 800, 800);
        canvas.drawRect(r2, drawPaint);

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
                point.y -=10;
                break;
            case 2:
                point.y +=10;
                break;
            case 3:
                point.x -=10;
                break;
            case 4:
                point.x +=10;
                break;
        }
        invalidate();

    }
}