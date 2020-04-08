package com.example.aitestapp.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aitestapp.app.App;
import com.example.aitestapp.retrofit.api.models.post.GetPostsResponseModel;
import com.example.aitestapp.retrofit.api.models.post.Hit;
import com.example.aitestapp.services.user.PostService;
import com.example.aitestapp.ui.base.viewModel.BaseViewModelImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;

public class MainViewModelImpl extends BaseViewModelImpl implements MainViewModel {
    @NonNull
    private PostService postService;
    private int page = 0;

    private List<Hit> hitsList = new ArrayList<>();
    private MutableLiveData<List<Hit>> hitsPage = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<String> errorsMessage = new MutableLiveData<>(null);

    private int getPageAndIncrease() {
        return page++;
    }

    private boolean isLoading = false;

    public MainViewModelImpl() {
        postService = App.getApplication().getPostService();
        getFirstPostsPage();
    }

    private void getFirstPostsPage() {
        isLoading = true;
        page = 0;
        postService.getPosts(getPageAndIncrease())
                .subscribe(getPostsSubscriber(true), this::showError);
    }

    private void showError(Throwable throwable) {
        isLoading = false;
        errorsMessage.setValue(throwable != null ? throwable.getMessage() : "Error is null");
    }

    @Override
    public void onFullRefreshClick() {
        getFirstPostsPage();
    }

    @Override
    public void onNextPageRequest() {
        if (!isLoading) {
            isLoading = true;
            postService.getPosts(getPageAndIncrease())
                    .subscribe(getPostsSubscriber(false), this::showError);
        }
    }

    private Consumer<GetPostsResponseModel> getPostsSubscriber(boolean isFirstPage) {
        return postsResponseModel -> {
            if (isFirstPage) hitsList.clear();
            hitsList.addAll(postsResponseModel.getHits());
            hitsPage.setValue(hitsList);
            isLoading = false;
        };
    }

    @Override
    public LiveData<String> getErrorsMessageLiveData() {
        return errorsMessage;
    }

    @Override
    public LiveData<List<Hit>> getHitsLiveData() {
        return hitsPage;
    }
}
