package com.example.aitestapp.ui.main;

import androidx.lifecycle.LiveData;

import com.example.aitestapp.retrofit.api.models.post.GetPostsResponseModel;
import com.example.aitestapp.retrofit.api.models.post.Hit;
import com.example.aitestapp.ui.base.viewModel.BaseViewModel;

import java.util.List;

public interface MainViewModel extends BaseViewModel {

    void onFullRefreshClick();

    void onNextPageRequest();

    LiveData<String> getErrorsMessageLiveData();

    LiveData<List<Hit>> getHitsLiveData();
}
