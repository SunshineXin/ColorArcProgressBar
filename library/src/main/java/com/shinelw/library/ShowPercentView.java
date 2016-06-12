package com.shinelw.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ShowPercentView extends View{

    private Paint percentPaint;

    private Paint textPaint;
    private int textSize = 30;

    private int percent;
    private int allLineColor;
    private int percentLineColorLow;
    private int percentLineColorHight;

    private int allLineWidth = 2;
    private int percentLineWidth = 4;
    private int lineHeight = 10;


    public ShowPercentView(Context context) {
        super(context);
        init(null, 0);
    }

    public ShowPercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ShowPercentView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // TODO Auto-generated method stub
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ShowPercentView, defStyle, 0);
        percent = a.getInt(R.styleable.ShowPercentView_percent, 0);
        allLineColor = a.getColor(R.styleable.ShowPercentView_allLineColor, Color.GRAY);
        percentLineColorLow = a.getColor(R.styleable.ShowPercentView_percentLineColorLow, Color.GREEN);
        percentLineColorHight = a.getColor(R.styleable.ShowPercentView_percentLineColorHight, Color.RED);

        a.recycle();

        percentPaint = new Paint();
        percentPaint.setAntiAlias(true);



        textPaint = new Paint();
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int pointX =  width/2;
        int pointY = height/2;

        float textWidth = textPaint.measureText(percent + "%");
        if(percent < 50){
            //textPaint.setColor(oxbf3800);
            textPaint.setColor(Color.BLACK);
        }else{
            //textPaint.setColor(new Color(ox6ec705));
            textPaint.setColor(Color.RED);
        }
        canvas.drawText(percent+"%",pointX - textWidth/2,pointY + textPaint.getTextSize()/2 ,textPaint);


        percentPaint.setColor(allLineColor);
        percentPaint.setStrokeWidth(allLineWidth);

        float degrees = (float) (320.0/100);

        canvas.save();
        canvas.translate(0,pointY);
        canvas.rotate(-70, pointX, 0);
        for(int i = 0;i<100;i++){
            canvas.drawLine(0, 0, lineHeight, 0, percentPaint);
            canvas.rotate(degrees, pointX, 0);
        }
        canvas.restore();

        if(percent<60){
            percentPaint.setColor(percentLineColorLow);
        }else{
            percentPaint.setColor(percentLineColorHight);
        }

        percentPaint.setStrokeWidth(percentLineWidth);
        canvas.save();
        canvas.translate(0,pointY);
        canvas.rotate(-70, pointX, 0);
        for(int i = 0;i<percent;i++){
            canvas.drawLine(0, 0, lineHeight, 0, percentPaint);
            canvas.rotate(degrees, pointX, 0);
        }
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int d = (width >= height) ? height : width;
        setMeasuredDimension(d,d);
    }

    public void setPercent(int percent) {
        // TODO Auto-generated method stub
        this.percent = percent;
        postInvalidate();
    }
}