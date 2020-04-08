package com.example.aitestapp.app;

import android.app.Application;

import com.example.aitestapp.BuildConfig;
import com.example.aitestapp.retrofit.api.PostApi;
import com.example.aitestapp.services.user.PostService;
import com.example.aitestapp.services.user.PostServicesImpl;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class App extends Application {

    private Retrofit retrofit;
    private PostService postService;
    private static App application;

    @Override
    public void onCreate() {
        super.onCreate();
        App.application = this;

        init();
    }

    public static App getApplication() {
        return application;
    }

    public PostService getPostService() {
        return postService;
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        postService = new PostServicesImpl(retrofit.create(PostApi.class));
    }
}
