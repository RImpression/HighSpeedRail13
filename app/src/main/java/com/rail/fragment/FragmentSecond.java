package com.rail.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rail.activity.CheakActivity;
import com.rail.activity.R;

public class FragmentSecond extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        Button button= (Button) view.findViewById(R.id.button_check);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(FragmentSecond.this.getActivity(), CheakActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}