package com.guo.yy.relearning.image.myglide;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.Excludes;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.caverock.androidsvg.SVG;
import com.guo.yy.relearning.image.myglide.svg.SvgDecoder;
import com.guo.yy.relearning.image.myglide.svg.SvgDrawableTranscoder;


import java.io.InputStream;

/**
 * Created by guoziqiang on 2018/3/7.
 */
@Excludes(com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule.class)
@GlideModule
public class MyGlideModule extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
//        return super.isManifestParsingEnabled();
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        RequestOptions options = new RequestOptions()
                .format(DecodeFormat.PREFER_RGB_565);
//                .fitCenter();
//                .centerCrop();
        builder.setDefaultRequestOptions(options);


    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);



        registry.register(SVG.class, PictureDrawable.class, new SvgDrawableTranscoder())
                .append(InputStream.class, SVG.class, new SvgDecoder());
        registry.append(String.class, InputStream.class,new  CustomBaseGlideUrlLoader.Factory());
        //替换Ok的库
//        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
        registry.replace(GlideUrl.class, InputStream.class, new LoginOkHttpUrlLoader.Factory());


        // 指定Model类型为Picture的处理方式
//        registry.append(IPicture.class, InputStream.class, new MyModelLoader.LoaderFactory());
//
//        // 指定Model类型为File的处理方式
//        registry.append(File.class, InputStream.class,
//                new FileLoader.Factory<InputStream>(new FileLoader.FileOpener<InputStream>() {
//
//                    @Override public InputStream open(File file) throws FileNotFoundException {
//                        // 可以在这里进行文件处理,比如解密等.
//                        Timber.e(file.getAbsolutePath());
//                        return ConcealUtil.getCipherInputStream(file);
//                        // return new FileInputStream(file);
//                    }
//
//                    @Override public void close(InputStream inputStream) throws IOException {
//                        inputStream.close();
//                    }
//
//                    @Override public Class<InputStream> getDataClass() {
//                        return InputStream.class;
//                    }
//                }));
    }
}
