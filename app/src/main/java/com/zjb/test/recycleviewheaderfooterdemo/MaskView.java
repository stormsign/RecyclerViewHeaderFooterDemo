package com.zjb.test.recycleviewheaderfooterdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by khb on 2017/2/27.
 */
public class MaskView extends RelativeLayout {

    private Bitmap mBitmap;
    private Canvas canvas;
    private Paint paint;
    private RectF rectF;
    private PorterDuffXfermode porterDuffXfermode;
    private Canvas canvas1;
    private Paint paint2;

    private View anchorView;

    public MaskView(Context context, View anchorView) {
        super(context);
        this.anchorView = anchorView;
        init();
    }

    public MaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
//        mBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
//        canvas = new Canvas(mBitmap);
//        canvas.drawColor(0xb2000000);
        setBackgroundColor(Color.TRANSPARENT);
        setWillNotDraw(false);
        Log.i("LOG", "init");
        paint = new Paint();
        paint2 = new Paint();
        int[] anchorViewLocations = new int[2];
        anchorView.getLocationOnScreen(anchorViewLocations);
        int x = anchorView.getMeasuredWidth() + anchorViewLocations[0];
        int y = anchorView.getMeasuredHeight() + anchorViewLocations[1];
        rectF = new RectF(anchorViewLocations[0], anchorViewLocations[1], x, y);
//        rectF = new RectF(100, 300, 300, 500);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("LOG", "ondraw");
        setLayerType(LAYER_TYPE_HARDWARE, null);
        paint.setColor(0xb2000000);
//        paint.setAlpha(0X66);

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(rectF, paint);
        paint2.setColor(Color.WHITE);
        paint2.setPathEffect(new DashPathEffect(new float[]{10, 6}, 0));
        paint2.setStrokeWidth(3);
        paint2.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF, paint2);
        paint.setXfermode(null);


    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
//        应该是从配置文件中获取状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0){
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
