package com.lzokks04.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Recyclerview的adapter
 * Created by Liu on 2016/8/8.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {

    private Context context;
    private List<String> mDatas;
    private OnItemClickLitener mOnItemClickLitener;

    public RecyclerviewAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * 导入item布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false));
        return holder;
    }

    /**
     * 设置文字,监听器
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));

        if (mOnItemClickLitener != null) {
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    /**
     * 传入项目的大小
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 先创建viewholder继承RecyclerView.ViewHolder
     * 最后在RecyclerviewAdapter中继承MyViewHolder（RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>）
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textview);
        }
    }

    /**
     * 定义监听器
     */
    public interface OnItemClickLitener {
        //短按
        void onItemClick(View view, int position);

        //长按
        void onItemLongClick(View view, int position);
    }

}
