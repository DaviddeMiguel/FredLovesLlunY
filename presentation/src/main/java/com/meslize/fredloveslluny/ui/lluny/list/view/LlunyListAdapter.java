package com.meslize.fredloveslluny.ui.lluny.list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.meslize.fredloveslluny.model.LlunyModel;
import com.meslize.fredloveslluny.util.BaseRecyclerViewAdapter;
import java.util.List;

public class LlunyListAdapter
    extends BaseRecyclerViewAdapter<LlunyModel, LlunyListAdapter.DataObjectHolder> {

  public LlunyListAdapter() {
    super();
  }

  public LlunyListAdapter(List<LlunyModel> items) {
    super(items);
  }

  @Override public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(android.R.layout.simple_list_item_2, parent, false);

    return new DataObjectHolder(view, getOnItemClickListener());
  }

  @Override public void onBindViewHolder(DataObjectHolder holder, int position) {
    holder.textView1.setText(getItem(position).getName());
    holder.textView2.setText("Distance: " + getItem(position).getDistance());
  }

  public static class DataObjectHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {
    OnItemClickListener mOnItemClickListener;
    TextView textView1;
    TextView textView2;

    public DataObjectHolder(View itemView, OnItemClickListener listener) {
      super(itemView);
      textView1 = (TextView) itemView.findViewById(android.R.id.text1);
      textView2 = (TextView) itemView.findViewById(android.R.id.text2);

      mOnItemClickListener = listener;
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      if (mOnItemClickListener == null) {
        return;
      }

      mOnItemClickListener.onClickItem(getAdapterPosition(), v);
    }
  }
}
