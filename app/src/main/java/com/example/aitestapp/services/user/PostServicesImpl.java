package com.example.aitestapp.services.user;

import com.example.aitestapp.retrofit.api.PostApi;
import com.example.aitestapp.retrofit.api.models.post.GetPostsResponseModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostServicesImpl implements PostService {
    private static final String TAGS = "story";
    private PostApi postApi;

    public PostServicesImpl(PostApi postApi) {
        this.postApi = postApi;
    }

    @Override
    public Single<GetPostsResponseModel> getPosts(int page) {
        return postApi.getPosts(TAGS, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(postsResponseModel -> {
                    if (postsResponseModel == null) {
                        return Single.error(new Throwable("Response not good"));
                    } else if (postsResponseModel.getHits() == null || postsResponseModel.getHits().isEmpty()) {
                        return Single.error(new Throwable("Hits list empty"));
                    }
                    return Single.fromCallable(() -> postsResponseModel);
                });
    }

}
