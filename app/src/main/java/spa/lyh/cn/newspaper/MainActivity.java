package spa.lyh.cn.newspaper;


import android.app.Service;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cns.workspace.lib.androidsdk.toast.Toast;
import spa.lyh.cn.lib_largeimageview.LargeImageView;
import spa.lyh.cn.lib_largeimageview.OnImageRectListener;


public class MainActivity extends AppCompatActivity {

    Vibrator vibrator;

    private PhotoPagerAdapter photoPagerAdapter;
    private ArrayList<Newspaper> imgUrls;
    ViewPager2 viewPager;
    private boolean isFullscreen;
    private List<HotZone> hotZoneList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setSystemUiVisibility(getWindow().getDecorView(),View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN,true);

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        //下面图2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        getWindow().setAttributes(lp);
        vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);

        //setTranslucent();
        initHotData();
        initViewPager();
    }

    private void initHotData(){
        hotZoneList = new ArrayList<>();
        HotZone gouzi = new HotZone(1666,1195,2162,1954,"狗子");
        hotZoneList.add(gouzi);
        HotZone telangpu = new HotZone(927,167,2158,979,"特朗普");
        hotZoneList.add(telangpu);
        HotZone wukelan = new HotZone(63,1303,899,1534,"乌克兰");
        hotZoneList.add(wukelan);
        HotZone yi = new HotZone(1123,2945,1458,3829,"1.9%");
        hotZoneList.add(yi);
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewpager2);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //Log.e("liyuhao","onPageScrolled->position:"+position);
                //Log.e("liyuhao","onPageScrolled->positionOffset:"+positionOffset);
                //Log.e("liyuhao","onPageScrolled->positionOffsetPixels:"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //Log.e("liyuhao","onPageSelected:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        /*File file = new File("/sdcard/A01.jpg");
        if (file.exists()){
            Log.e("liyuhao","文件存在");
        }*/


        imgUrls = new ArrayList<>();
        Newspaper newspaper1 = new Newspaper();
        newspaper1.setUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.16pic.com%2F00%2F17%2F25%2F16pic_1725344_b.jpg&refer=http%3A%2F%2Fphoto.16pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620048511&t=de735a218aed1c2b6e559dc8f901bd62");
        imgUrls.add(newspaper1);
        Newspaper newspaper2 = new Newspaper();
        newspaper2.setUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi3.sinaimg.cn%2Fdy%2Fo%2F2011-04-07%2FU2004P1T1D22252393F1395DT20110407173134.jpg&refer=http%3A%2F%2Fi3.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620048567&t=085e7ab9b9fd4d8de265b2140b7b304d");
        imgUrls.add(newspaper2);
        Newspaper newspaper3 = new Newspaper();
        newspaper3.setUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fupload.m4.cn%2F2013%2F0109%2F1357711680165.jpg&refer=http%3A%2F%2Fupload.m4.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620048603&t=d462bc05dafce4d21bf7c5040780d703");
        imgUrls.add(newspaper3);

        photoPagerAdapter = new PhotoPagerAdapter(this,imgUrls);
        viewPager.setAdapter(photoPagerAdapter);
        //viewPager.setCurrentItem(3);
        /*new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(4);
            }
        },2000);*/
        setFullscreen(true);
        /*photoPagerAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*isFullscreen = !isFullscreen;
                setFullscreen(isFullscreen);*//*
                LargeImageView imageView = (LargeImageView) view;
                *//*Log.e("liyuhao","Scale:"+imageView.getScale());
                Log.e("liyuhao","Width:"+imageView.getImageWidth());
                Log.e("liyuhao","Height:"+imageView.getImageHeight());
                Log.e("liyuhao","TouchX:"+imageView.getTouchX());
                Log.e("liyuhao","TouchY:"+imageView.getTouchY());*//*
                *//*Log.e("liyuhao","getCurrentItem:"+imgUrls.get(viewPager.getCurrentItem()).getLeft());
                Log.e("liyuhao","getCurrentItem:"+imgUrls.get(viewPager.getCurrentItem()).getTop());
                Log.e("liyuhao","getCurrentItem:"+imgUrls.get(viewPager.getCurrentItem()).getRight());
                Log.e("liyuhao","getCurrentItem:"+imgUrls.get(viewPager.getCurrentItem()).getBottom());*//*
                syncData(imageView.getWidth(),
                        imageView.getImageWidth(),
                        imageView.getImageHeight(),
                        imgUrls.get(viewPager.getCurrentItem()).getLeft(),
                        imgUrls.get(viewPager.getCurrentItem()).getTop(),
                        imgUrls.get(viewPager.getCurrentItem()).getRight(),
                        imgUrls.get(viewPager.getCurrentItem()).getBottom(),
                        imageView.getTouchX(),
                        imageView.getTouchY());
                vibrator.vibrate(100);
            }
        });*/

        photoPagerAdapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LargeImageView imageView = (LargeImageView) v;
                /*Log.e("liyuhao","Scale:"+imageView.getScale());
                Log.e("liyuhao","Width:"+imageView.getImageWidth());
                Log.e("liyuhao","Height:"+imageView.getImageHeight());
                Log.e("liyuhao","TouchX:"+imageView.getTouchX());
                Log.e("liyuhao","TouchY:"+imageView.getTouchY());*/
                /*Log.e("liyuhao","getCurrentItem:"+imgUrls.get(viewPager.getCurrentItem()).getLeft());
                Log.e("liyuhao","getCurrentItem:"+imgUrls.get(viewPager.getCurrentItem()).getTop());
                Log.e("liyuhao","getCurrentItem:"+imgUrls.get(viewPager.getCurrentItem()).getRight());
                Log.e("liyuhao","getCurrentItem:"+imgUrls.get(viewPager.getCurrentItem()).getBottom());*/
                if (imageView.hasLoad()){
                    syncData(imageView.getWidth(),
                            imageView.getImageWidth(),
                            imageView.getImageHeight(),
                            imgUrls.get(viewPager.getCurrentItem()).getLeft(),
                            imgUrls.get(viewPager.getCurrentItem()).getTop(),
                            imgUrls.get(viewPager.getCurrentItem()).getRight(),
                            imgUrls.get(viewPager.getCurrentItem()).getBottom(),
                            imageView.getTouchX(),
                            imageView.getTouchY());
                }
                //vibrator.vibrate(100);
                return true;
            }
        });

        /*photoPagerAdapter.setImageRectListener(new OnImageRectListener() {
            @Override
            public void getRect(int index, int left, int top, int right, int bottom) {
                //viewPager.getCurrentItem()
                Log.e("liyuhao","getCurrentItem:"+viewPager.getCurrentItem());
                Log.e("liyuhao","index:"+index);
            }
        });*/
    }

    private void syncData(int viewWidth,
                          int picWidth,
                          int picHeight,
                          int left,
                          int top,
                          int right,
                          int bottom,
                          int touchX,
                          int touchY){
        int widthSafe = 0;
        int heightSafe = 0;
        if (right > picWidth){
            widthSafe = (right - picWidth)/2;
        }
        if (bottom > picHeight){
            heightSafe = (bottom - picHeight)/2;
        }
        float scale = (float) (right - left) / (float)viewWidth;
        int x = (int) (scale * touchX) - widthSafe + left;
        int y = (int) (scale * touchY) - heightSafe + top;

        if (x >= 0 && x <= picWidth && y >= 0 && y <= picHeight){
            //点在图片里
            Log.e("liyuhao","X:"+x);
            Log.e("liyuhao","Y:"+y);
            if (viewPager.getCurrentItem() == 0){
                for (int i = 0; i< hotZoneList.size();i++){
                    if (x >= hotZoneList.get(i).getLeft() &&
                    x <= hotZoneList.get(i).getRight() &&
                    y >= hotZoneList.get(i).getTop()&&
                    y <= hotZoneList.get(i).getBottom()){
                        Toast.makeText(MainActivity.this,hotZoneList.get(i).getName(),Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setFullscreen(isFullscreen);
    }

    private void setFullscreen(boolean isFullscreen){
        setSystemUiVisibility(getWindow().getDecorView(),View.SYSTEM_UI_FLAG_FULLSCREEN,isFullscreen);
    }

    /**
     * 非纯色状态栏，比如用图片进入状态栏位置，使用这个方法。如果纯色状态栏使用这个方法，效果与上面一致，但是不再
     * 兼容换肤框架，状态栏颜色需要手动控制。
     */
    public void setTranslucent(){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        /*setSystemUiVisibility(window.getDecorView(),
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN,
                true);*/
        setSystemUiVisibility(window.getDecorView(),
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN,
                true);
        /*setSystemUiVisibility(getWindow().getDecorView(),
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY,
                true);*/
    }


    /**
     * 设置显示的样式
     * @param decorView
     * @param visibility
     */
    public void setSystemUiVisibility(View decorView, int visibility, boolean isAddVisibility){
        int oldVis = decorView.getSystemUiVisibility();
        int newVis = oldVis;
        if (isAddVisibility){
            newVis |= visibility;
        }else {
            newVis &= ~visibility;
        }
        if (newVis != oldVis) {
            decorView.setSystemUiVisibility(newVis);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
