package com.jtsoft.letmedo.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 2017/9/12.
 */

@SuppressLint("AppCompatCustomView")
public class MyTableTextView extends TextView {
    Paint paint = new Paint();

    public MyTableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int color = Color.parseColor("#000000");
        // 为边框设置颜色
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画TextView的4个边
        canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
        canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
        canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight() - 1, paint);
        canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1, paint);
    }
}
