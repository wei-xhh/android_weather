package com.example.xhhweather;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CityAdapter extends BaseAdapter {
    //
    class ViewHolder{
        TextView TVcityName;
        //删除城市
        ImageView ivDelCity;
        //选择城市后返回天气界面
        ImageView ivQuyCity;
    }
    private List<CityItem> list;

    public CityAdapter(List<CityItem> list){
        this.list=list;
    }
    @Override
    public View getView(final int position, View covertView, final ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;
        if(covertView==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_item,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.TVcityName=view.findViewById(R.id.tv_city_name);
            viewHolder.ivQuyCity=view.findViewById(R.id.btn_move);
            viewHolder.ivDelCity=view.findViewById(R.id.iv_del_city);
            //将viewHolder存入在view中
            view.setTag(viewHolder);
        }else{
            view=covertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        CityItem cityItem= list.get(position);
        viewHolder.TVcityName.setText(cityItem.getCityName());

        //删除城市
        viewHolder.ivDelCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();//本身就是adapter
            }
        });

        //移动城市
        viewHolder.ivQuyCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityItem city=list.get(position);
                Toast.makeText(viewGroup.getContext(),city.getCityPinYin(),Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}
