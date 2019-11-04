package spa.lyh.cn.newspaper;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import cns.workspace.lib.androidsdk.image.glide.ProgressInterceptor;
import cns.workspace.lib.androidsdk.image.glide.ProgressListener;


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



    /*public View instantiateItem(ViewGroup container, final int position) {
        final ImageView photoView;
        final RoundProgressBar bar;
        *//*if (mIsMatch) {
            photoView = new ImageView(mContext);
            photoView.setScaleType(ScaleType.FIT_XY);
        } else {
            photoView = new PhotoView(mContext);
        }*//*
        View root = View.inflate(mContext, R.layout.img_view,null);
        photoView = root.findViewById(R.id.photo);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnMyClick(v,0);
            }
        });
        bar = root.findViewById(R.id.bar);
        if (pro[position] == 0){
            bar.setProgress(1);
        }else {
            bar.setProgress(pro[position]);
        }
        ProgressInterceptor.addListener(mData.get(position), new ProgressListener() {
            @Override
            public void onProgress(int progress) {
                bar.setProgress(progress);
                pro[position] = progress;
            }
        });
        RequestOptions options = new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888);
        Glide.with(mContext)
                .asDrawable()
                .apply(options)
                .load(mData.get(position))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        ProgressInterceptor.removeListener(mData.get(position));
                        bar.setVisibility(View.GONE);
                        //photoView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pic_big));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        ProgressInterceptor.removeListener(mData.get(position));
                        bar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(photoView);

        container.addView(root, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        return root;
    }*/

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
                .asDrawable()
                .apply(options)
                .load(mData.get(position))
                //.load("/sdcard/1.jpg")
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.photoView.setImageDrawable(resource);
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
        PhotoView photoView;
        RoundProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.photo);
            progressBar = itemView.findViewById(R.id.bar);

        }
    }
}
