package com.guo.yy.relearning.image.myglide;

import androidx.annotation.NonNull;

import com.bumptech.glide.integration.okhttp3.OkHttpStreamFetcher;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginOkHttpUrlLoader implements ModelLoader<GlideUrl, InputStream> {

    private final Call.Factory client;

    // Public API.
    @SuppressWarnings("WeakerAccess")
    public LoginOkHttpUrlLoader(@NonNull Call.Factory client) {
        this.client = client;
    }

    @Override
    public boolean handles(@NonNull GlideUrl url) {
        return true;
    }

    @Override
    public LoadData<InputStream> buildLoadData(
            @NonNull GlideUrl model, int width, int height, @NonNull Options options) {
        return new LoadData<>(model, new OkHttpStreamFetcher(client, model));
    }

    /** The default factory for {@link OkHttpUrlLoader}s. */
    // Public API.
    @SuppressWarnings("WeakerAccess")
    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private static volatile Call.Factory internalClient;
        private final Call.Factory client;

        private static Call.Factory getInternalClient() {
            if (internalClient == null) {
                synchronized (OkHttpUrlLoader.Factory.class) {
                    if (internalClient == null) {
                        internalClient = new OkHttpClient()
                        .newBuilder()
                        .addInterceptor(new Interceptor() {
                            @NotNull
                            @Override
                            public Response intercept(@NotNull Chain chain) throws IOException {
                                Request header = chain.request().newBuilder()
//                                        .removeHeader("User-Agent")
                                        .build();
                                return chain.proceed(header);
                            }
                        })
//                                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                                    @Override
//                                    public void log(@NotNull String s) {
//                                        Log.e("img-log", s);
//                                    }
//                                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build();

                    }
                }
            }
            return internalClient;
        }

        /** Constructor for a new Factory that runs requests using a static singleton client. */
        public Factory() {
            this(getInternalClient());
        }

        /**
         * Constructor for a new Factory that runs requests using given client.
         *
         * @param client this is typically an instance of {@code OkHttpClient}.
         */
        public Factory(@NonNull Call.Factory client) {
            this.client = client;
        }

        @NonNull
        @Override
        public ModelLoader<GlideUrl, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new OkHttpUrlLoader(client);
        }

        @Override
        public void teardown() {
            // Do nothing, this instance doesn't own the client.
        }
    }
}
