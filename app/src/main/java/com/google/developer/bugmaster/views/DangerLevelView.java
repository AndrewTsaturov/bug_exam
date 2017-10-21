package com.google.developer.bugmaster.views;

import android.content.Context;
import android.content.res.TypedArray;
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

//--> This class should be used in the insect list to display danger level
public class DangerLevelView extends TextView {

    private Paint circlePaint;

    private int width, height;

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
        height = getMeasuredHeight();

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
        if (width > height) return true;
        else return false;
    }

    private void makeRounded(Canvas canvas, boolean orientation) {
        Path path = new Path();
        path.reset();
        if (!orientation) {
            RectF oval = new RectF(0, height - (height / 2 + width / 2), width, (height / 2 + width / 2));
            path.moveTo(0, 0);
            path.lineTo(0, height);
            path.lineTo(width, height);
            path.lineTo(width, height / 2);
            path.addOval(oval, Path.Direction.CW);
            path.lineTo(width, 0);
            path.lineTo(0, 0);

        } else {
            RectF oval = new RectF(width - (width / 2 + height / 2), 0, (width / 2 + height / 2), height);
            path.moveTo(0, 0);
            path.lineTo(0, height);
            path.lineTo(width, height);
            path.lineTo(width, height / 2);
            path.lineTo(width / 2 + height / 2, height / 2);
            path.addOval(oval, Path.Direction.CW);
            path.lineTo(width, height / 2);
            path.lineTo(width, 0);
            path.lineTo(0, 0);
        }
        canvas.drawPath(path, circlePaint);
    }

    public void setDangerLevel(int dangerLevel) {
        setText(String.valueOf(dangerLevel));

        setBackgroundColor(getDangerLevelColor(dangerLevel, getContext()));

        invalidate();
    }

    public int getDangerLevel() {
        return Integer.parseInt(getText().toString());
    }

    public int getDangerLevelColor(int dangerLevel, Context context) {
        int result;

        int [] colorValuesArray = getColorArrayFromStringRes();

        switch (dangerLevel) {
            case 1:
                result = colorValuesArray[0];
                break;
            case 2:
                result = colorValuesArray[1];
                break;
            case 3:
                result = colorValuesArray[2];
                break;
            case 4:
                result = colorValuesArray[3];
                break;
            case 5:
                result = colorValuesArray[4];
                break;
            case 6:
                result = colorValuesArray[5];
                break;
            case 7:
                result = colorValuesArray[6];
                break;
            case 8:
                result = colorValuesArray[7];
                break;
            case 9:
                result = colorValuesArray[8];
                break;
            case 10:
                result = colorValuesArray[9];
                break;
            default:
                result = colorValuesArray[0];
                break;

        }
        return result;
    }

    private int[] getColorArrayFromStringRes() {

        TypedArray bufferArray = getContext().getResources().obtainTypedArray(R.array.dangerColors);

        int[] result = new int[bufferArray.length()];

        for(int i = 0; i < bufferArray.length(); i++){
            result[i] = bufferArray.getColor(i, 0);
        }

        bufferArray.recycle();

        return result;
    }

}
