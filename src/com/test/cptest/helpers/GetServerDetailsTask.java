package com.test.cptest.helpers;

import android.content.Context;

import com.test.cptest.clients.ServersClient;
import com.test.cptest.domain.SimpleHttpResponse;
import com.test.cptest.views.ControlPanelView;

public class GetServerDetailsTask implements 
                    AsyncTaskCompleteListener<SimpleHttpResponse>{

    private Context mContext;
    private ServersClient mClient;
    private ControlPanelView<SimpleHttpResponse> cpv;

    public GetServerDetailsTask(Context context, 
                        ServersClient client,
                        ControlPanelView<SimpleHttpResponse> viewToUpdate){
        this.mContext = context;
        this.mClient = client;
        this.cpv = viewToUpdate;
    }

    @Override
    public SimpleHttpResponse doTask(){
        SimpleHttpResponse response = mClient.getServer(20720520);
        return response;
    }

    @Override
    public void onTaskComplete(SimpleHttpResponse result) {
        cpv.update(result);
    }

    public void launchTask(){
        HttpRequestTask task = new HttpRequestTask(mContext, this, "Loading...");
        task.execute();
    }
}
