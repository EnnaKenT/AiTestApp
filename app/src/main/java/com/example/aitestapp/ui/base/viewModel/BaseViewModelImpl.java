package com.example.aitestapp.ui.base.viewModel;

import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseViewModelImpl extends ViewModel implements BaseViewModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
}
