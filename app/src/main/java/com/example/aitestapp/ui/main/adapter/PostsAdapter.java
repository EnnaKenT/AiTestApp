package com.example.aitestapp.ui.main.adapter;

import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aitestapp.R;
import com.example.aitestapp.retrofit.api.models.post.Hit;
import com.example.aitestapp.ui.base.adapter.RecyclerViewAdapter;
import com.example.aitestapp.ui.utils.PostsUtil;

import java.util.LinkedList;
import java.util.List;

public class PostsAdapter extends RecyclerViewAdapter<Hit, PostsAdapter.Holder> {

    @NonNull
    private Runnable onNextPageRequest;
    @NonNull
    private OnPostClicked onItemClicked;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private static final int POSTS_OFFSET = 5;

    public PostsAdapter(@NonNull Runnable onNextPageRequest, @NonNull OnPostClicked listener) {
        this.onNextPageRequest = onNextPageRequest;
        this.onItemClicked = listener;
    }

    public List<Hit> getSelectedItems() {
        List<Hit> result = new LinkedList<>();
        for (int i = 0; i < selectedItems.size(); i++) {
            int key = selectedItems.keyAt(i);
            if (selectedItems.get(key)) {
                result.add(getItem(key));
            }
        }
        return result;
    }

    private void onCheckboxClicked(int position) {
        if (selectedItems.indexOfKey(position) >= 0) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(inflate(parent, R.layout.item_post), onItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(getItem(position), selectedItems.get(position));
        if (position + POSTS_OFFSET > getItemCount()) {
            onNextPageRequest.run();
        }
    }


    static class Holder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView titleTv;
        private TextView authorTv;
        private TextView createdAtTv;

        Holder(View view, OnPostClicked onItemClicked) {
            super(view);
            checkBox = view.findViewById(R.id.checkbox);
            titleTv = view.findViewById(R.id.titleTv);
            authorTv = view.findViewById(R.id.authorTv);
            createdAtTv = view.findViewById(R.id.createdAtTv);

            checkBox.setOnClickListener(v -> onItemClicked.onPostClicked(getAdapterPosition()));
            itemView.setOnClickListener(v -> onItemClicked.onPostClicked(getAdapterPosition()));
        }

        void bind(Hit hit, boolean isSelected) {
            checkBox.setChecked(isSelected);
            String title = String.format(itemView.getContext().getString(R.string.title_format), hit.getTitle());
            titleTv.setText(title);
            String author = String.format(itemView.getContext().getString(R.string.author_format), hit.getAuthor());
            authorTv.setText(author);
            createdAtTv.setText(PostsUtil.getFormattedData(hit, itemView.getContext()));
        }
    }

    public interface OnPostClicked {
        void onPostClicked(int position);
    }
}
