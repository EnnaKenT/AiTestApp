package com.example.aitestapp.services.user;

import io.reactivex.rxjava3.core.Single;

import com.example.aitestapp.retrofit.api.models.post.GetPostsResponseModel;

public interface PostService {

    Single<GetPostsResponseModel> getPosts(int page);

}
