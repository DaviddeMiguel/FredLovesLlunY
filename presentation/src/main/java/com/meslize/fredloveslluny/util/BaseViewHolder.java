package com.meslize.fredloveslluny.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder<V extends View> extends RecyclerView.ViewHolder {

  private final V mView;

  public BaseViewHolder(V itemView) {
    super(itemView);
    mView = itemView;
  }

  public V getView() {
    return mView;
  }
}
