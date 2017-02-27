package com.zjb.test.recycleviewheaderfooterdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by khb on 2017/2/27.
 */
public class MaskView extends RelativeLayout {

    private Bitmap mBitmap;

    public MaskView(Context context) {
        super(context);
    }

    public MaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Canvas canvas1 = new Canvas(mBitmap);
        canvas.drawColor(0xb2000000);
        RectF rectF = new RectF(100, 200, 300, 400);
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setXfermode(porterDuffXfermode);
//        paint.setColor(Color.TRANSPARENT);
//        paint.setColor(getContext().getResources().getColor(android.R.color.transparent));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectF, paint);



    }
}
