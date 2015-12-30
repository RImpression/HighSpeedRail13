package com.rail.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rail.activity.R;
import com.rail.entity.QueryTicketEntity;
import com.rail.myview.MyLinearLayout;

import java.util.List;

public class QueryTicketAdapter extends BaseAdapter {
    private List<QueryTicketEntity> list_data = null;
    private Context context;

    public QueryTicketAdapter(List<QueryTicketEntity> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (list_data != null)
            return list_data.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        if (list_data != null) {
            return list_data.get(position);
        }
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    class ViewHolder {
        TextView tv_checi;
        TextView tv_chufa;
        TextView tv_dada;
        TextView tv_time_chufa;
        TextView tv_time_dada;
        TextView lishi;
        MyLinearLayout mylinearlayout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder vd;
        QueryTicketEntity entity = list_data.get(position);
        System.out.println(list_data.get(position).toString());
        LayoutInflater linflater = LayoutInflater.from(context);
        if (convertView == null) {
            vd = new ViewHolder();
            convertView = linflater.inflate(R.layout.list_item, null);
            vd.tv_checi = (TextView) convertView.findViewById(R.id.tv_listview_item_checi);
            vd.tv_chufa = (TextView) convertView.findViewById(R.id.tv_listview_item_chufa);
            vd.tv_dada = (TextView) convertView.findViewById(R.id.tv_listview_item_daozhan);
            vd.tv_time_chufa = (TextView) convertView.findViewById(R.id.tv_time_chufa);
            vd.tv_time_dada = (TextView) convertView.findViewById(R.id.tv_time_dada);
            vd.lishi = (TextView) convertView.findViewById(R.id.tv_time_jingguo);
            vd.mylinearlayout = (MyLinearLayout) convertView.findViewById(R.id.mylinearlayout);
            vd.mylinearlayout.setmCellHeight(50);
            vd.mylinearlayout.setmCellWidth(210);
            convertView.setTag(vd);
        } else {
            vd = (ViewHolder) convertView.getTag();
        }
        vd.mylinearlayout.removeAllViews();
        if (!entity.getGr_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "高级软卧:" + entity.getGr_num());
        }
        if (!entity.getSwz_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "商务座:" + entity.getSwz_num());
        }
        if (!entity.getQt_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "其他:" + entity.getQt_num());
        }
        if (!entity.getRw_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "软卧:" + entity.getRw_num());
        }
        if (!entity.getRz_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "软座:" + entity.getRz_num());
        }
        if (!entity.getTz_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "特等座:" + entity.getQt_num());
        }
        if (!entity.getWz_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "无座:" + entity.getWz_num());
        }
        if (!entity.getYw_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "硬卧:" + entity.getYw_num());
        }
        if (!entity.getYz_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "硬座:" + entity.getYz_num());
        }
        if (!entity.getZy_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "一等座:" + entity.getZy_num());
        }
        if (!entity.getZe_num().equalsIgnoreCase("--")) {
            setText(vd.mylinearlayout, "二等座:" + entity.getZe_num());
        }
        vd.tv_checi.setText(entity.getStation_train_code());
        vd.tv_chufa.setText(entity.getFrom_station_name());
        vd.tv_dada.setText(entity.getTo_station_name());
        vd.lishi.setText(entity.getLishi());
        vd.tv_time_chufa.setText(entity.getStart_time());
        vd.tv_time_dada.setText(entity.getArrive_time());
        return convertView;
    }

    public void setText(MyLinearLayout layout, String data) {
        System.out.println(data);
        TextView tv_text = new TextView(context);
        tv_text.setSingleLine();
        tv_text.setTextSize(10);
        tv_text.setText(data);
        tv_text.setPadding(0, 0, 5, 0);
        layout.addView(tv_text);
    }
}