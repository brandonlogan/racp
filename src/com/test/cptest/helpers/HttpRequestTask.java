package com.test.cptest.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.test.cptest.domain.SimpleHttpResponse;

public class HttpRequestTask extends AsyncTask<String, String, SimpleHttpResponse>{

    Context mContext;
    ProgressDialog progressBar;
    private AsyncTaskCompleteListener<SimpleHttpResponse> callback;
    String message;
    
    public HttpRequestTask(Context context, 
                              AsyncTaskCompleteListener<SimpleHttpResponse> cb,
                              String m){
        super();
        this.mContext = context;
        this.callback = cb;
        this.message = m;
    }

    protected void onPreExecute(){
        progressBar = new ProgressDialog(mContext);
        progressBar.setCancelable(true);
        progressBar.setMessage(this.message);
        progressBar.show();
    }
    
    @Override
    protected SimpleHttpResponse doInBackground(String... s) {
        SimpleHttpResponse response = callback.doTask();
        return response;
    }
    
    protected void onProgressUpdate(String... progress){
        
    }
    
    protected void onPostExecute(SimpleHttpResponse result){
        if(progressBar.isShowing()){
            progressBar.hide();
        }
        callback.onTaskComplete(result);
    }

}
