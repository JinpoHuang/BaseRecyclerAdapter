package com.jinpo.baserecycleradapter.baseRecyclerAdapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Autho: huang jinpo
 * User: 14214
 * Date: 2019/6/1 0:16
 * Function:
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mView = new SparseArray<>();
    }

    public static RecyclerViewHolder getViewHolder(View view) {
        return new RecyclerViewHolder(view);
    }

    public static RecyclerViewHolder getViewHolder(Context context, ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RecyclerViewHolder(view);
    }

    private <T extends View> T findViewById(int id) {
        View view = mView.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mView.put(id, view);
        }
        return (T) view;
    }

    public <T extends View> T getView(@IdRes int id) {
        return findViewById(id);
    }

    public TextView getTextView(int id) {
        return getView(id);
    }

    public ImageView getImageView(int id) {
        return getView(id);
    }

    public RecyclerViewHolder setTextViewText(int id, CharSequence text) {
        getTextView(id).setText(text);
        return this;
    }

}
