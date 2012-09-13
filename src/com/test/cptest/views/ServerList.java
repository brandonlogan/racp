package com.test.cptest.views;

import android.widget.TextView;

import com.test.cptest.domain.SimpleHttpResponse;

public class ServerList implements ControlPanelView<SimpleHttpResponse>{

    TextView list;
    
    public ServerList(TextView v){
        this.list = v;
    }
    
    @Override
    public void update(SimpleHttpResponse newData) {
        this.list.setText(newData.body);
    }

}
