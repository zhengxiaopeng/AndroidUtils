package com.rocko.android.common.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author Mr.Zheng
 * @date 2014年8月22日 上午11:00:30
 */
public final class BitmapUtils {
    /**
     * 给Bitmap图片加上文字水印
     *
     * @param bitmap   要加水印的bitmap，自动回收
     * @param tag      水印文字
     * @param location 0:加在左下角；1：底部中间
     * @return
     */
    public static Bitmap createTagBitmap(Bitmap bitmap, String tag, int location) {
        // 复制一份新的Bitmap，因为不能直接在原有的bitmap上进行水印操作
        Bitmap newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        bitmap.recycle();
        int width = newBitmap.getWidth();
        int height = newBitmap.getHeight();
        int textSize = width / 20;
        // 使用自定义画布
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTextSize(textSize);
        if (0 == location)
            canvas.drawText(tag, 0 + 2, height - paint.getFontSpacing() / 4 + 2, paint);
        else
            canvas.drawText(tag, width / 2 - 50, height - paint.getFontSpacing() / 4 + 2, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return newBitmap;
    }

    /*
     * Drawable to Bitmap
     */
    private static Bitmap drawable2Bitmap(Drawable drawable, int... defaultWH) {
        if (drawable == null)
            return null;
        if (drawable instanceof BitmapDrawable)
            return ((BitmapDrawable) drawable).getBitmap();
        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable)
                bitmap = Bitmap.createBitmap(defaultWH[0], defaultWH[1], Bitmap.Config.ARGB_8888);
            else
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                        Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Bitmap to Drawable
     */
    @SuppressWarnings("deprecation")
    private static Drawable bitmap2Drawable(Bitmap bm) {
        if (bm == null) {
            return null;
        }
        return new BitmapDrawable(bm);
    }
}
