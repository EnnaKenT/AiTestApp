package com.example.aitestapp.ui.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapter<ITEM, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    private List<ITEM> items = new ArrayList<>();

    protected View inflate(ViewGroup viewGroup, @LayoutRes int layOutRes) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return inflater.inflate(layOutRes, viewGroup, false);
    }

    public void setItems(List<ITEM> list) {
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    public ITEM getItem(int position) {
        return items.get(position);
    }

    public List<ITEM> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
