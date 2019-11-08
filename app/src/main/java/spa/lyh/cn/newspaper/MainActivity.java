package spa.lyh.cn.newspaper;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.io.File;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private PhotoPagerAdapter photoPagerAdapter;
    private ArrayList<String> imgUrls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        //下面图2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        getWindow().setAttributes(lp);
        //setTranslucent();
        initViewPager();
    }

    private void initViewPager() {
        final ViewPager2 viewPager = findViewById(R.id.viewpager2);
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
        imgUrls.add("http://paperstatic.uschinapress.com/repo/image/newspaperNo/2019/10/31/aebfde85-6722-4cf2-a915-1688a71d70ca/1031/A02最美国.jpg");
        imgUrls.add("http://epaper.br-cn.com/media/pt/p/c4/0b0fb929f66a49fa98f0eb6e71dd13c4/e8a29f2053c01dcc7970e2160752981a.jpg");
        imgUrls.add("http://img.zcool.cn/community/0130175568a93000000127164788c3.jpg");
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572952809598&di=6c26006f0286eab62f8fc60db3bf5d0e&imgtype=0&src=http%3A%2F%2Fimage.51bidlive.com%2Fu%2F20180613%2F0247.jpg");
        imgUrls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572975179441&di=c8d1a8c3de6b9bf801e9324b1de63e37&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201409%2F10%2F20140910170324_hvjiG.thumb.700_0.jpeg");
        imgUrls.add("http://img.zcool.cn/community/0175b257189a156ac7251343e36176.jpg");
        imgUrls.add("http://img.zcool.cn/community/01462b572c593e32f875a399b76259.jpg");
        imgUrls.add("http://img.zcool.cn/community/012c79572c59bf32f875a399b7adf8.jpg");
        imgUrls.add("http://img.zcool.cn/community/01bef35770e01b0000012e7e9ff90d.jpg");

        photoPagerAdapter = new PhotoPagerAdapter(this,imgUrls);
        viewPager.setAdapter(photoPagerAdapter);
        //viewPager.setCurrentItem(3);
        /*new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(4);
            }
        },2000);*/
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
