package com.example.aitestapp.ui.base.viewModel;

import androidx.lifecycle.LiveData;

public interface BaseViewModel {

    LiveData<String> getErrorsMessageLiveData();

}
