package com.example.aitestapp.ui.main;

import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.aitestapp.R;
import com.example.aitestapp.retrofit.api.models.post.Hit;
import com.example.aitestapp.ui.base.view.BaseActivity;
import com.example.aitestapp.ui.main.adapter.PostsAdapter;

import java.util.List;

public class MainActivity extends BaseActivity implements PostsAdapter.OnPostClicked {

    private MainViewModel viewModel;
    private PostsAdapter adapter;

    private RecyclerView recyclerView;
    private TextView toolbarTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        viewModel = viewModels(MainViewModelImpl.class);
        recyclerView = findViewById(R.id.recycler);
        toolbarTextView = findViewById(R.id.toolbarTextView);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);

        initSwipeRefresh();
        initRecycleView();
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.onFullRefreshClick());
    }

    private void initRecycleView() {
        adapter = new PostsAdapter(viewModel::onNextPageRequest, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, new LinearLayoutManager(this).getOrientation()));

        viewModel.getHitsLiveData().observe(this, hits -> {
            swipeRefreshLayout.setRefreshing(false);
            adapter.setItems(hits);
        });
        viewModel.getErrorsMessageLiveData().observe(this, this::showToast);
    }

    @Override
    public void onPostClicked(int position) {
        int count = adapter.getSelectedItems().size();

        String text = getResources().getQuantityString(R.plurals.items_selected_format, count);
        toolbarTextView.setText(String.format(text, count));
    }
}
