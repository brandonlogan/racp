package com.test.cptest;

import com.test.cptest.clients.ServersClient;
import com.test.cptest.helpers.GetServerDetailsTask;
import com.test.cptest.helpers.ListServersTask;
import com.test.cptest.views.ServerDetails;
import com.test.cptest.views.ServerList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    
    private Button authButton;
    private Button listServersButton;
    private Button getServerButton;
    private TextView info;
    private String authToken;
    private String cloudServersAddress;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        authButton = (Button)findViewById(R.id.AuthButton);
        listServersButton = (Button)findViewById(R.id.ListServersButton);
        getServerButton = (Button)findViewById(R.id.GetServerButton);
        info = (TextView)findViewById(R.id.InfoTV);
        authToken = this.getIntent().getStringExtra("auth_token");
        cloudServersAddress = 
                    this.getIntent().getStringExtra("cloud_servers_address");
        authButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                String s = "Token: " + authToken + "\n\nCloud Servers URL: " + 
                            cloudServersAddress;
                info.setText(s);
            }

        });
        listServersButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                ServersClient client = new ServersClient(cloudServersAddress, authToken);
                ServerList list = new ServerList(info);
                new ListServersTask(MainActivity.this, client, list).launchTask();
            }
        });
        getServerButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                ServersClient client = new ServersClient(cloudServersAddress, authToken);
                ServerDetails details = new ServerDetails(info);
                new GetServerDetailsTask(MainActivity.this, client, details).launchTask();
            }
        });
    }
}
