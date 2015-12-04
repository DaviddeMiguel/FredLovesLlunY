/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trov.fred.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trov.fred.R;

import org.altbeacon.beacon.Beacon;

import java.util.List;

public class BeaconListAdapter extends RecyclerView.Adapter<BeaconListAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<Beacon> mItems;
    private OnItemClickListener mOnItemClickListener;

    public BeaconListAdapter(List<Beacon> items) {
        this.mItems = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycler, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Beacon item = mItems.get(position);

        String id = item.getId1().toString();
        String distance = String.valueOf(item.getDistance());

        holder.id.setText("ID: " + id);
        holder.distance.setText("Distance: " + distance.substring(0, 4));

        //Picasso.with(holder.image.getContext()).load(item.getImage()).into(holder.image);
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (mOnItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mOnItemClickListener.onItemClick(v, (Beacon) v.getTag());
                }
            }, 200);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Beacon item);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView distance;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            distance = (TextView) itemView.findViewById(R.id.distance);
        }
    }
}
