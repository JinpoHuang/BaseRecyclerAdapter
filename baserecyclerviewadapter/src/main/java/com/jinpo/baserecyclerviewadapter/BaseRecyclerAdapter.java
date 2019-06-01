package com.jinpo.baserecyclerviewadapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Autho: huang jinpo
 * User: 14214
 * Date: 2019/6/1 0:14
 * Function:
 */

public abstract class BaseRecyclerAdapter<D> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected List<D> mData;
    protected Context mContext;
    protected Activity mActivity;
    protected int mLayoutId;

    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public BaseRecyclerAdapter(Context ctx, List<D> list){
        mData = (list != null) ? list : new ArrayList<D>();
        mContext = ctx;
        mActivity = (Activity) ctx;
        mLayoutId = getLayoutID();
    }

    //获取列表布局
    protected abstract int getLayoutID();

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewHolder holder = RecyclerViewHolder.getViewHolder(mContext,parent,mLayoutId);
        addItemClick(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        bindData(holder,mData.get(position),position);
    }

    protected void addItemClick(final RecyclerViewHolder holder) {
        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v,holder.getLayoutPosition());
                }
            });
        }
        if (mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(v,holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    protected abstract void bindData(RecyclerViewHolder holder, D item, int position);

    public List<D> getmData() {
        return mData;
    }

    public void setmData(List<D> mData) {
        this.mData = mData;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public D getItem(int position) {
        return mData.get(position);
    }

    public void add(int pos, D item){
        if (pos > getItemCount()){
            pos = getItemCount();
        }
        if (pos < 0){
            pos = 0;
        }
        mData.add(pos,item);
        notifyItemInserted(pos);
    }

    public void add(D item){
        add(getItemCount(),item);
    }

    public void add(List<D> items){
        if (items != null){
            for (D item : items) {
                add(item);
            }
        }
    }

    public void addStart(D item){
        add(0,item);
    }

    public void remove(int pos){
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    public void remove(){
        int pos = getItemCount()-1;
        remove(pos);
    }

    public void change(int pos, D item){
        mData.set(pos,item);
        notifyItemChanged(pos,item);
    }

    public void clear(){
        mData = new ArrayList<D>();
        notifyDataSetChanged();
    }

    public void move(int fromPos, int toPos){
        D temp = mData.get(fromPos);

        mData.remove(fromPos);
        mData.add(toPos,temp);

        notifyItemMoved(fromPos,toPos);
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int pos);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int pos);
    }
}
