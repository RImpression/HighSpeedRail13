package com.rail.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rail.adapter.QueryTicketAdapter;
import com.rail.entity.QueryTicketEntity;
import com.rail.https.TraverSearch;
import com.rail.https.TraverStationSearch;
import com.rail.interfaces.OnQueryTicketListener;
import com.rail.model.QueryTicketModel;
import com.rail.model.StationListResquestModel;
import com.rail.model.TraverResultModel;
import com.rail.model.TraverSearchResquestModel;
import com.rail.myview.MyListView;

import java.util.List;


public class ResultData extends Activity{
    private MyListView listview_message;
    private String date=null;
    private OnQueryTicketListener listener;
    private QueryTicketModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        initView();
        setdata();
    }


    public void initView() {
        listview_message = (MyListView) this.findViewById(R.id.lv_datalist);
        model=new QueryTicketModel();
    }



    private void setdata() {

        String from = getIntent().getStringExtra("from_station");
        String to = getIntent().getStringExtra("to_station");
        String date = getIntent().getStringExtra("dateString");
        model.query(from, to, date, new OnQueryTicketListener() {
            @Override
            public void onSuccess(Object o) {
                updateData((List<QueryTicketEntity> )o);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
            }
        });
    }





















    private void getdata() {
        String fromWhere = getIntent().getStringExtra("from_station");
        String toWhere = getIntent().getStringExtra("to_station");
        String dateString = getIntent().getStringExtra("dateString");
        date=dateString;
        TraverSearchResquestModel resquestmodel = new TraverSearchResquestModel();
        resquestmodel.setDate(dateString);
        resquestmodel.setFrom(fromWhere);
        resquestmodel.setTo(toWhere);
        new TraverSearch().HttpToGetData(this, resquestmodel);
        Log.i("ResultData",fromWhere+" "+toWhere+" "+dateString);
    }

    private void updateData(List<QueryTicketEntity> listdata) {
        listview_message.setAdapter(new QueryTicketAdapter(listdata, this));
        listview_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                TraverResultModel model = (TraverResultModel) listView.getItemAtPosition(position);
                StationListResquestModel stationListResquestModel=new StationListResquestModel();
                stationListResquestModel.setTrain_no(model.getTrain_no());
                if (date!=null)
                    stationListResquestModel.setDepart_data(date);
                stationListResquestModel.setFrom_station_telecode(model.getStart_station_telecode());
                stationListResquestModel.setTo_station_telecode(model.getEnd_station_telecode());
                new TraverStationSearch().Traverstationsercher(ResultData.this, stationListResquestModel);
            }
        });
    }

//
//    public  Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.arg1==0){
//                System.out.println(msg.obj.toString());
//                InitData((List<TraverResultModel>) msg.obj);
//            }
//            if(msg.arg1==1){
//
//            }
//        }
//    };


}
