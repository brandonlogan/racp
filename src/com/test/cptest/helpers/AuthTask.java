package com.test.cptest.helpers;

import com.test.cptest.MainActivity;
import com.test.cptest.clients.Auth;
import com.test.cptest.domain.AuthData;
import com.test.cptest.domain.SimpleHttpResponse;

import android.content.Context;
import android.content.Intent;

public class AuthTask implements 
                                AsyncTaskCompleteListener<SimpleHttpResponse>{

    private Context mContext;
    private Auth mClient;
    
    public AuthTask(Context context, Auth client){
        this.mContext = context;
        this.mClient = client;
    }
    
    @Override
    public SimpleHttpResponse doTask(){
        SimpleHttpResponse response = mClient.get_auth();
        return response;
    }
    
    @Override
    public void onTaskComplete(SimpleHttpResponse result) {
        Intent intent = new Intent(mContext, MainActivity.class);
        AuthData data = new AuthData(result);
        intent.putExtra("auth_token", data.authToken);
        intent.putExtra("cloud_servers_address", data.cloudServersAddress);
        mContext.startActivity(intent);
        
    }
    
    public void launchTask(){
        HttpRequestTask task = new HttpRequestTask(mContext, this, "Authenticating...");
        task.execute();
    }

}
