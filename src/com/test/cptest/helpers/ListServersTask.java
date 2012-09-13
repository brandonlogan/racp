package com.test.cptest.helpers;

import com.test.cptest.clients.ServersClient;
import com.test.cptest.domain.SimpleHttpResponse;
import com.test.cptest.views.ControlPanelView;
import android.content.Context;

public class ListServersTask implements 
                                AsyncTaskCompleteListener<SimpleHttpResponse>{

    private Context mContext;
    private ServersClient mClient;
    private ControlPanelView<SimpleHttpResponse> cpv;
    
    public ListServersTask(Context context, 
                                                ServersClient client,
                                                ControlPanelView<SimpleHttpResponse> viewToUpdate){
        this.mContext = context;
        this.mClient = client;
        this.cpv = viewToUpdate;
    }
    
    @Override
    public SimpleHttpResponse doTask(){
        SimpleHttpResponse response = mClient.listServers();
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