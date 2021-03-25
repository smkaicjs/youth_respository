package com.shimk.Txgc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shimk.Txgc.R;
import com.shimk.Txgc.bean.ClassmateContent;

import java.util.ArrayList;
import java.util.List;

public class Recyclerview_adapter_liuyance extends RecyclerView.Adapter<Recyclerview_adapter_liuyance.myholder> {

    private List<ClassmateContent> mList = new ArrayList<>();

    public Recyclerview_adapter_liuyance(List<ClassmateContent> mList) {
        this.mList = mList;
    }

    class myholder extends RecyclerView.ViewHolder{
        ImageView mImageview;
        TextView mTextview;
        TextView timeTextView;
        public myholder(@NonNull View itemView) {
            super(itemView);
            mImageview = itemView.findViewById(R.id.liuyace_item_imageview);
            mTextview = itemView.findViewById(R.id.liuyace_item_textview_content);
            timeTextView = itemView.findViewById(R.id.liuyace_item_textview_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null!=itemClick)
                    itemClick.onClick(v,getAdapterPosition());
                }
            });
        }
    }
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        myholder holder = new myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.liiyance_recycler_item,null));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, int position) {
        if (null!=mList){
            holder.mImageview.setBackgroundResource(R.drawable.nav_icon);
            holder.timeTextView.setText(mList.get(position).getPosttime());
            holder.mTextview.setText(mList.get(position).getContent());
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    private onItemClick itemClick;
    public interface onItemClick{
        void onClick(View view,int postion);
    }
    public void setOnItemClick(onItemClick click){
        this.itemClick = click;
    }
}
