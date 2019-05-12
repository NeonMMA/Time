package com.company.firstedition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RoundDrawView extends View {
    Paint paint = new Paint(), paint1 = new Paint();
    final int rad=300;
    final int deltar=40;
    public float degree;
    public float WIDTH;
    public  float HEIGHT;
    public String TextofTimer1;

    public void setTextofTimer1(String textofTimer1) {
        TextofTimer1 = textofTimer1;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }


    public RoundDrawView(Context context) {
        super(context);
    }
    public RoundDrawView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public RoundDrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        final RectF ovalin= new RectF();
        final RectF ovalout= new RectF();
        paint.setStyle(Paint.Style.FILL);
        WIDTH= rad+20;
        HEIGHT=250+rad;
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
        canvas.drawCircle(WIDTH,HEIGHT,rad-deltar-5,paint);
        paint.setColor(Color.RED);
        paint.setTextSize(200);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(TextofTimer1, canvas.getWidth()/2, 200, paint);
    }
}
