package com.example.lgwordapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lgwordapp.info.HistoryInfo;
import com.example.lgwordapp.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.Myholder> {

    private List<HistoryInfo>mHistoryInfo = new ArrayList<>();
    private Context mContext;
    public void setListData(List<HistoryInfo>listData){
        this.mHistoryInfo = listData;
        //must
        notifyDataSetChanged();
    }

    public HistoryListAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historylist_item, null);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        //绑定数据
        HistoryInfo historyInfo = mHistoryInfo.get(position);

        //设置数据
//        holder.yuanwen.setText("原文："+historyInfo.getYword());
////        holder.eye.setImageResource;
//        holder.eye.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 在这里调用数据库查询并显示译文
//                String tword = HistoryDbHelper.getInstance(mContext).queryHistoryListData(historyInfo.getYword()).toString();
//                // 显示译文
//                holder.yiwen.setText("译文："+historyInfo.getTword());
//            }
//        });
        holder.eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换显示原文和译文的状态
                holder.isShowingYword = !holder.isShowingYword;
                // 更新文本视图
                if (holder.isShowingYword) {
                    holder.yuanwen.setText("原文：" + historyInfo.getYword());
                } else {
                    holder.yuanwen.setText("译文：" + historyInfo.getTword());
                }
            }
        });

        // 根据初始状态设置文本
        if (holder.isShowingYword) {
            holder.yuanwen.setText("原文：" + historyInfo.getYword());
        } else {
            holder.yuanwen.setText("译文：" + historyInfo.getTword());
        }

    }

    @Override
    public int getItemCount() {
        return mHistoryInfo.size();//集合大小
    }

    static class Myholder extends RecyclerView.ViewHolder{
        ImageView eye;
        TextView yuanwen;
        TextView yiwen;
        boolean isShowingYword = true;

        public Myholder(@NonNull View itemView) {
            super(itemView);
            eye = itemView.findViewById(R.id.eye);
            yuanwen = itemView.findViewById(R.id.yuanwen);
            yiwen = itemView.findViewById(R.id.yuanwen);
        }
    }
}
