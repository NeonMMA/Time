package com.company.firstedition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class DrawView extends View {
    Paint paint = new Paint(), paint1 = new Paint();
    final int rad=300;
    final int deltar=40;
    public float degree;
    public float WIDTH;
    public  float HEIGHT;
    public String TimerText;

    public void setTimerText(String timerText) {TimerText = timerText;}
    public void setDegree(float degree) {
        this.degree = degree;
    }
    public void setWIDTH(float WIDTH) { this.WIDTH = WIDTH;}
    public void setHEIGHT(float HEIGHT) { this.HEIGHT = HEIGHT;}

    public DrawView(Context context) {
        super(context);
    }
    public DrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        final RectF ovalin= new RectF();
        final RectF ovalout= new RectF();
        paint.setStyle(Paint.Style.FILL);

        ovalin.set(WIDTH-rad,HEIGHT-rad,WIDTH+rad,HEIGHT+rad);
        ovalout.set(WIDTH-rad+deltar,HEIGHT-rad+deltar,WIDTH+rad-deltar,HEIGHT+rad-deltar);


        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);
        paint1.setColor(Color.BLUE);
        if (degree!=0) {
            canvas.drawArc(ovalin, 270, 360 - degree, true, paint1);
            canvas.drawArc(ovalout, 270, 360 - degree, true, paint);
        }
        paint.setColor(Color.GREEN);
        canvas.drawCircle(WIDTH,HEIGHT,rad-deltar,paint);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(WIDTH,HEIGHT,rad-deltar-10,paint);
        paint.setColor(Color.RED);
        paint.setTextSize(200);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(TimerText, WIDTH, 200, paint);
    }

}
