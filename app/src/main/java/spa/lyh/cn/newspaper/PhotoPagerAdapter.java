package spa.lyh.cn.newspaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.shizhefei.view.largeimage.BlockImageLoader;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;
import com.shizhefei.view.largeimage.factory.InputStreamBitmapDecoderFactory;

import java.io.File;
import java.util.ArrayList;

import cns.workspace.lib.androidsdk.image.glide.ProgressInterceptor;
import cns.workspace.lib.androidsdk.image.glide.ProgressListener;
import cns.workspace.lib.androidsdk.utils.CnsCommonUtil;


/**
 * Created by renzhiqiang on 16/8/31.
 */
public class PhotoPagerAdapter extends RecyclerView.Adapter<PhotoPagerAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<String> mData;
    private LayoutInflater mInflater;

    private int[] pro;

    public PhotoPagerAdapter(Context context, ArrayList<String> list) {
        mContext = context;
        mData = list;
        this.mInflater = LayoutInflater.from(context);
        pro = new int[list.size()];
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.img_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (pro[position] == 0){
            holder.progressBar.setProgress(1);
        }else {
            holder.progressBar.setProgress(pro[position]);
        }
        ProgressInterceptor.addListener(mData.get(position), new ProgressListener() {
            @Override
            public void onProgress(int progress) {
                holder.progressBar.setProgress(progress);
                pro[position] = progress;
            }
        });
        RequestOptions options = new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888);
        Glide.with(mContext)
                .asFile()
                //.apply(options)
                .load(mData.get(position))
                .into(new CustomTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        FileBitmapDecoderFactory factory = new FileBitmapDecoderFactory(resource);
                        int[] info = factory.getImageInfo();
                        /*Log.e("liyuhao","图片宽："+info[0]);
                        Log.e("liyuhao","图片高："+info[1]);*/
                        float scale = (1.0f * info[0] / holder.photoView.getWidth()) * holder.photoView.getHeight() / info[1];
                        /*Log.e("liyuhao","控件宽："+holder.photoView.getWidth());
                        Log.e("liyuhao","控件高："+holder.photoView.getHeight());*/
                        holder.photoView.setImage(factory,null,scale < 1.0f?scale:1.0f);

                        ProgressInterceptor.removeListener(mData.get(position));
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        ProgressInterceptor.removeListener(mData.get(position));
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyBigImg photoView;
        RoundProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.photo);
            progressBar = itemView.findViewById(R.id.bar);
            photoView.setCriticalScaleValueHook(new LargeImageView.CriticalScaleValueHook() {
                @Override
                public float getMinScale(LargeImageView largeImageView, int imageWidth, int imageHeight, float suggestMinScale) {
                    /*float width = imageWidth;
                    float height = imageHeight;
                    float result = width/height;
                    Log.e("liyuhao","图片宽："+imageWidth);
                    Log.e("liyuhao","图片高："+imageHeight);
                    *//*DisplayMetrics metrics = new DisplayMetrics();
                    WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                    wm.getDefaultDisplay().getMetrics(metrics);*//*
                    Log.e("liyuhao","控件宽："+largeImageView.getWidth());
                    Log.e("liyuhao","控件高："+largeImageView.getHeight());
                    //Log.e("liyuhao","设备状态栏高："+CnsCommonUtil.getStatusBarHeight(mContext));
                    float b = (1.0f * imageWidth / largeImageView.getWidth()) * largeImageView.getHeight() / imageHeight;*/
                    //Log.e("liyuhao",b+"");
                    return suggestMinScale;
                }

                @Override
                public float getMaxScale(LargeImageView largeImageView, int imageWidth, int imageHeight, float suggestMaxScale) {
                    return suggestMaxScale;
                }
            });

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            photoView.setOnLoadStateChangeListener(new BlockImageLoader.OnLoadStateChangeListener() {
                @Override
                public void onLoadStart(int loadType, Object param) {

                }

                @Override
                public void onLoadFinished(int loadType, Object param, boolean success, Throwable throwable) {
                    //photoView.setScale(photoView.getMinScale());
                }
            });
        }
    }
}
