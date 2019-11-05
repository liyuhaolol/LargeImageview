package spa.lyh.cn.newspaper;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private PhotoPagerAdapter photoPagerAdapter;
    private ArrayList<String> imgUrls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Window window = this.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        attributes.flags |= flagTranslucentStatus;
        //int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        //attributes.flags |= flagTranslucentNavigation;
        window.setAttributes(attributes);*/
        initViewPager();
    }

    private void initViewPager() {
        ViewPager2 viewPager = findViewById(R.id.viewpager2);

        /*viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });*/
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        imgUrls = new ArrayList<>();
        imgUrls.add("http://img.zcool.cn/community/0130175568a93000000127164788c3.jpg");
        imgUrls.add("http://img.zcool.cn/community/0175b257189a156ac7251343e36176.jpg");
        imgUrls.add("http://img.zcool.cn/community/01462b572c593e32f875a399b76259.jpg");
        imgUrls.add("http://img.zcool.cn/community/012c79572c59bf32f875a399b7adf8.jpg");
        imgUrls.add("http://img.zcool.cn/community/01bef35770e01b0000012e7e9ff90d.jpg");

        photoPagerAdapter = new PhotoPagerAdapter(this,imgUrls);
        viewPager.setAdapter(photoPagerAdapter);
    }
}
