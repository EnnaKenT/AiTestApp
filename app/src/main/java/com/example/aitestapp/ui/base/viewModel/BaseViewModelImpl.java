package com.example.aitestapp.ui.base.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseViewModelImpl extends ViewModel implements BaseViewModel {

    protected MutableLiveData<String> errorsMessage = new MutableLiveData<>(null);
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public LiveData<String> getErrorsMessageLiveData() {
        return errorsMessage;
    }

    protected void showError(Throwable throwable) {
        errorsMessage.setValue(throwable != null ? throwable.getMessage() : "Error is null");
    }

}
