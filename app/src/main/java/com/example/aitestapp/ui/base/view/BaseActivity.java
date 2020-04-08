package com.example.aitestapp.ui.base.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import static android.widget.Toast.LENGTH_LONG;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initViews();
    }

    protected <Model extends ViewModel> Model viewModels(Class<Model> clazz) {
        return new ViewModelProvider(this).get(clazz);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, LENGTH_LONG).show();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initViews();
}
