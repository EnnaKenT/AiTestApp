package com.example.aitestapp.retrofit.api;

import com.example.aitestapp.retrofit.api.models.post.GetPostsResponseModel;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostApi {

    @GET("/api/v1/search_by_date")
    Single<GetPostsResponseModel> getPosts(@Query("tags") String tags, @Query("page") int page);

}
