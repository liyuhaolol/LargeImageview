package spa.lyh.cn.newspaper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.shizhefei.view.largeimage.LargeImageView;

public class MyBigImg extends LargeImageView {
    public MyBigImg(Context context) {
        super(context);
    }

    public MyBigImg(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBigImg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int startX, startY;
    private int multiTouch;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                //Log.e("liyuhao","触发"+ev.getPointerCount());
                //getParent().requestDisallowInterceptTouchEvent(true);
                multiTouch = 1;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //Log.e("liyuhao","触发"+ev.getPointerCount());
                if (ev.getPointerCount() == 2){
                    //还有1个手指头
                    multiTouch = 0;
                }

                break;
            case MotionEvent.ACTION_DOWN:
                //Log.e("liyuhao","触发");
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int disX = Math.abs(endX - startX);
                int disY = Math.abs(endY - startY);
                if(disX > disY){
                    getParent().requestDisallowInterceptTouchEvent(multiTouch > 0 || canScrollHorizontally(startX -endX));
                }else {
                    getParent().requestDisallowInterceptTouchEvent(multiTouch > 0 || canScrollVertically(startY -endY));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

}
