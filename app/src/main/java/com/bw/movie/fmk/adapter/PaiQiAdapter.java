package com.bw.movie.fmk.adapter;

import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
=======
>>>>>>> 59e708dc3952f7c5e1933c5e7030f97ac62ed13a
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
<<<<<<< HEAD
import com.bw.movie.fmk.activity.GouMaiActivity;
import com.bw.movie.fmk.activity.XiangQingActivity;
import com.bw.movie.fmk.activity.YuanZuoActivity;
=======
>>>>>>> 59e708dc3952f7c5e1933c5e7030f97ac62ed13a
import com.bw.movie.fmk.bean.GouPiaoBean;
import com.bw.movie.fmk.bean.PaiQiBeam;

import java.util.List;

/**
 * @Auther: 付明锟
 * @Date: 2019/5/20 16:54
 * @Description:
 */
public class PaiQiAdapter extends RecyclerView.Adapter<PaiQiAdapter.Holder> {

    private List<PaiQiBeam.ResultBean> data;
    private Context mContext;

    public PaiQiAdapter(List<PaiQiBeam.ResultBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.paiqi_item, null);

        return new Holder(view);
    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        final String screeningHall = data.get(i).getScreeningHall();
        holder.pai_item_name.setText(screeningHall);

        final String beginTime = data.get(i).getBeginTime();
        holder.pai_item_kai.setText(beginTime);

        final String endTime = data.get(i).getEndTime();
        holder.pai_item_jie.setText(endTime);

        final double price = data.get(i).getPrice();
        holder.pai_item_qian.setText(price+"");

        final String id = data.get(i).getId()+"";


        //点击
        /****** 给XuanZuoActiviy传值 *******/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,YuanZuoActivity.class);
                intent.putExtra("gou_name",screeningHall);
                intent.putExtra("gou_beginTime",beginTime);
                intent.putExtra("gou_endTime",endTime);
                intent.putExtra("gou_price",price);
                //排期Id
                intent.putExtra("scheduleId",id);
                mContext.startActivity(intent);
            }
        });
=======
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        String screeningHall = data.get(i).getScreeningHall();
        holder.pai_item_name.setText(screeningHall);

        String beginTime = data.get(i).getBeginTime();
        holder.pai_item_kai.setText(beginTime);

        String endTime = data.get(i).getEndTime();
        holder.pai_item_jie.setText(endTime);

        double price = data.get(i).getPrice();
        holder.pai_item_qian.setText(price+"");
>>>>>>> 59e708dc3952f7c5e1933c5e7030f97ac62ed13a
    }

    @Override
    public int getItemCount() {
<<<<<<< HEAD
=======
        Log.e("tab","data"+data);
>>>>>>> 59e708dc3952f7c5e1933c5e7030f97ac62ed13a
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView pai_item_name,pai_item_kai,pai_item_jie,pai_item_qian;

        public Holder(@NonNull View itemView) {
            super(itemView);
            pai_item_name = itemView.findViewById(R.id.pai_item_name);
            pai_item_kai = itemView.findViewById(R.id.pai_item_kai);
            pai_item_jie = itemView.findViewById(R.id.pai_item_jie);
            pai_item_qian = itemView.findViewById(R.id.pai_item_qian);
        }
    }
<<<<<<< HEAD

    //接口
//    public interface OnItem{
//        void onClick(int position,String movieId);
//    }
//
//    private OnItem onItem;
//
//    //一个公共的方法
//    public void setOnItem(OnItem onItem) {
//        this.onItem = onItem;
//    }

=======
>>>>>>> 59e708dc3952f7c5e1933c5e7030f97ac62ed13a
}
