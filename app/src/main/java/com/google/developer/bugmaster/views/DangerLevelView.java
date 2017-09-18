package com.google.developer.bugmaster.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.google.developer.bugmaster.R;

//TODO: This class should be used in the insect list to display danger level
public class DangerLevelView extends TextView {

    private Paint circlePaint;

    private int width, heigth;

    private boolean landOrientation;

    public DangerLevelView(Context context) {
        super(context);
        initParams();
    }

    public DangerLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public DangerLevelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    public DangerLevelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getMeasuredWidth();
        heigth = getMeasuredHeight();

        landOrientation = checkLandscapeOrientation();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        circlePaint.setColor(getParentBackgroundColor(getRootView()));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        makeRounded(canvas, landOrientation);
    }


    private void initParams() {
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.WHITE);
    }

    private int getParentBackgroundColor(View rootView) {
        int color = Color.WHITE;

        if (rootView.getBackground() instanceof ColorDrawable)
            color = ((ColorDrawable) rootView.getBackground()).getColor();

        return color;
    }

    private boolean checkLandscapeOrientation() {
        if (width > heigth) return true;
        else return false;
    }

    private void makeRounded(Canvas canvas, boolean orientation) {
        Path path = new Path();
        path.reset();
        if (!orientation) {
            RectF oval = new RectF(0, heigth - (heigth / 2 + width / 2), width, (heigth / 2 + width / 2));
            path.moveTo(0, 0);
            path.lineTo(0, heigth);
            path.lineTo(width, heigth);
            path.lineTo(width, heigth / 2);
            path.addOval(oval, Path.Direction.CW);
            path.lineTo(width, 0);
            path.lineTo(0, 0);

        } else {
            RectF oval = new RectF(width - (width / 2 + heigth / 2), 0, (width / 2 + heigth / 2), heigth);
            path.moveTo(0, 0);
            path.lineTo(0, heigth);
            path.lineTo(width, heigth);
            path.lineTo(width, heigth / 2);
            path.lineTo(width / 2 + heigth / 2, heigth / 2);
            path.addOval(oval, Path.Direction.CW);
            path.lineTo(width, heigth / 2);
            path.lineTo(width, 0);
            path.lineTo(0, 0);
        }
        canvas.drawPath(path, circlePaint);
    }

    public void setDangerLevel(int dangerLevel, Context context) {
        setText(dangerLevel);

        setBackgroundColor(getDangerLevelColor(dangerLevel, context));

        invalidate();
    }

    public int getDangerLevel() {
        return Integer.parseInt(getText().toString());
    }

    public int getDangerLevelColor(int dangerLevel, Context context) {
        int result;

        String[] colorValuesArray = context.getResources().getStringArray(R.array.dangerColors);

        switch (dangerLevel) {
            case 1:
                result = getColorValueFromArrayItem(colorValuesArray[0]);
                break;
            case 2:
                result = getColorValueFromArrayItem(colorValuesArray[1]);
                break;
            case 3:
                result = getColorValueFromArrayItem(colorValuesArray[2]);
                break;
            case 4:
                result = getColorValueFromArrayItem(colorValuesArray[3]);
                break;
            case 5:
                result = getColorValueFromArrayItem(colorValuesArray[4]);
                break;
            case 6:
                result = getColorValueFromArrayItem(colorValuesArray[5]);
                break;
            case 7:
                result = getColorValueFromArrayItem(colorValuesArray[6]);
                break;
            case 8:
                result = getColorValueFromArrayItem(colorValuesArray[7]);
                break;
            case 9:
                result = getColorValueFromArrayItem(colorValuesArray[8]);
                break;
            case 10:
                result = getColorValueFromArrayItem(colorValuesArray[9]);
                break;
            default:
                result = getColorValueFromArrayItem(colorValuesArray[0]);
                break;

        }
        return result;
    }

    private int getColorValueFromArrayItem(String arrayItem) {
        int result = Integer.parseInt(arrayItem);
        return result;
    }

}
