package com.guo.yy.relearning.image.myglide;

import android.text.TextUtils;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import java.io.InputStream;

/**
 * Created by Guo on 2017/6/23.
 */

public class CustomBaseGlideUrlLoader extends BaseGlideUrlLoader<String> {
    private static final ModelCache<String, GlideUrl> urlCache = new ModelCache<>();
//  协议
    private static final String SCHEME = "http:";
    private static final String SCHEME1 = "https:";

    public CustomBaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader, ModelCache<String, GlideUrl> modelCache) {
        super(concreteLoader, modelCache);
    }

    /**
     * If the URL contains a special variable width indicator (eg "__w-200-400-800__")
     * we get the buckets from the URL (200, 400 and 800 in the example) and replace
     * the URL with the best bucket for the requested width (the bucket immediately
     * larger than the requested width).
     * <p>
     * 可以控制图片的大小，也可以对url做统一处理
     */
    @Override
    protected String getUrl(String model, int width, int height, Options options) {
        if (!TextUtils.isEmpty(model)) {
            if (!(model.startsWith(SCHEME) || model.startsWith(SCHEME1))) {//url省略了http/https
                model = SCHEME + model;
            }
        }
        return model;

    }

    @Override
    public boolean handles(String s) {
        return true;
    }

    /**
     * 工厂来构建CustormBaseGlideUrlLoader对象
     */
    public static class Factory implements ModelLoaderFactory<String, InputStream> {
        @Override
        public ModelLoader<String, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new CustomBaseGlideUrlLoader(multiFactory.build(GlideUrl.class, InputStream.class), urlCache);
        }

        @Override
        public void teardown() {

        }
    }
}