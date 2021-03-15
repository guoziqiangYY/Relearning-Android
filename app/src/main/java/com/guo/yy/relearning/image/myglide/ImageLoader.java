package com.guo.yy.relearning.image.myglide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;


/**
 * Created by guoziqiang on 2018/3/6.
 * <p>
 * 加载图片调用此类。
 */

public class ImageLoader {

    private volatile static ImageLoader mImageLoader;

    private ImageLoader() {

    }


    public static ImageLoader getInstance() {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                mImageLoader = new ImageLoader();
            }
        }
        return mImageLoader;
    }


    /**
     * 加载图片
     *
     * @param context context
     * @param view
     * @param imgUrl
     */
    public void load(Context context, ImageView view, String imgUrl) {
        GlideApp
                .with(context)
                .load(imgUrl)
                .into(view);
    }

    /**
     * @param view   bind the lifecycle to View
     * @param imgUrl
     */
    public void load(ImageView view, String imgUrl) {

        GlideApp
                .with(view)
                .load(imgUrl)
                .dontAnimate()
                .into(view);

    }

    public void loadNoCache(ImageView view, String imgUrl) {

        GlideApp
                .with(view)
                .load(imgUrl)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(view);

    }


    /**
     * 限定宽高的加载图片
     *
     * @param view
     * @param imgUrl
     * @param width
     * @param height
     */
    public void load(ImageView view, String imgUrl, int width, int height) {

        GlideApp
                .with(view)
                .load(imgUrl)
                .dontAnimate()
                .override(width, height)
                .into(view);

    }


    /**
     * 圆形
     *
     * @param view
     * @param imgUrl
     */
    public void loadCircle(ImageView view, String imgUrl) {
        GlideApp
                .with(view)
                .load(imgUrl)
                .transform(new CircleCrop())
                .into(view);

    }

    /**
     * 圆形
     *
     * @param context
     * @param view
     * @param imgUrl
     */
    public void loadCircle(Context context, ImageView view, String imgUrl) {
        GlideApp
                .with(context)
                .load(imgUrl)
                .transform(new CircleCrop())
                .into(view);


    }


    /**
     * 带展位图的
     *
     * @param view
     * @param imgUrl
     * @param placeHolder
     * @param width
     * @param height
     */
    public void load(ImageView view, String imgUrl, int placeHolder, int width, int height) {

        GlideApp
                .with(view)
                .load(imgUrl)
                .dontAnimate()
                .placeholder(placeHolder)
                .override(width, height)
                .into(view);

    }


    /**
     * @param view   bind the lifecycle to View
     * @param imgUrl
     */
    public void load(ImageView view, String imgUrl, int placeHolder) {

        GlideApp
                .with(view)
                .load(imgUrl)
                .placeholder(placeHolder)
                .dontAnimate()
                .into(view);

    }


    public void load(ImageView view, int imgResId) {

        GlideApp
                .with(view)
                .load(imgResId)
                .dontAnimate()
                .into(view);

    }


    public void loadCorners(ImageView view, String url, int corners) {

        GlideApp
                .with(view)
                .load(url)
//                .transform(new RoundedCorners(corners))
                .transform(new CenterCrop(), new RoundedCorners(corners))
                .dontAnimate()
                .into(view);



    }


    public void loadWH(ImageView view, Uri path) {
        GlideApp.with(view)
                .asBitmap()
                .load(path)
                .into(new CustomViewTarget<ImageView, Bitmap>(view) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        view.setImageURI(path);

                        int maxWidth = resource.getWidth();
                        int maxHeight = resource.getHeight();
                        Log.e("gzq", "bitmap:" + maxWidth + "," + maxHeight);

                        int deviceWidth = view.getResources().getDisplayMetrics().widthPixels;
                        int deviceHeight = view.getResources().getDisplayMetrics().heightPixels;

                        if (maxHeight < deviceHeight && maxWidth < deviceWidth) {
                            view.getLayoutParams().height = maxHeight;
                            view.getLayoutParams().width = maxWidth;
                            view.requestLayout();
                        } else {
                            float aspSource = (float) maxWidth / (float) maxHeight;
                            if(maxWidth >=maxHeight){//是个横图
                                maxWidth = deviceWidth;
                                maxHeight = (int) (maxWidth/aspSource);
                            }else{
                                maxHeight = view.getLayoutParams().height;
                                maxWidth = (int) (maxHeight*aspSource);
                            }
                            view.getLayoutParams().height = maxHeight;
                            view.getLayoutParams().width = maxWidth;
                            view.requestLayout();

                        }


                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });
    }


    public void  loadWH(ImageView view, Uri path, OnResourceLoadListener<Bitmap> listener) {

        GlideApp.with(view)
                .asBitmap()
                .load(path)
                .into(new CustomViewTarget<ImageView, Bitmap>(view) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if(listener != null){
                            listener.onResourceReady(resource);
                        }
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });
    }


    public interface OnResourceLoadListener<T>{
        void onResourceReady(T t);
    }



    public int getH(Context context){
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            return context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
