package com.meslize.fredloveslluny.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  private OnItemClickListener mOnItemClickListener;
  private List<T> mItems;

  public BaseRecyclerViewAdapter() {
  }

  public BaseRecyclerViewAdapter(List<T> items) {
    mItems = items;
  }

  public OnItemClickListener getOnItemClickListener() {
    return mOnItemClickListener;
  }

  public void setOnItemClickListener(OnItemClickListener listener) {
    mOnItemClickListener = listener;
  }

  public void setItems(List<T> items) {
    mItems = items;
    notifyDataSetChanged();
  }

  public void addItems(List<T> items) {
    mItems.addAll(items);
    notifyDataSetChanged();
  }

  public void addItem(T item, int index) {
    mItems.add(item);
    notifyItemInserted(index);
  }

  public void deleteItem(int index) {
    mItems.remove(index);
    notifyItemRemoved(index);
  }

  public T getItem(int position) {
    return mItems.get(position);
  }

  @Override public int getItemCount() {
    if (mItems == null) {
      return 0;
    }

    return mItems.size();
  }

  public interface OnItemClickListener {
    void onClickItem(int position, View v);
  }
}
