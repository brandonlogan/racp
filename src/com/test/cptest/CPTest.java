package com.test.cptest;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.test.cptest.clients.RestClient;
import com.test.cptest.clients.ServersClient;
import com.test.cptest.domain.SimpleHttpResponse;
import com.test.cptest.clients.Auth;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CPTest extends Activity {
    /** Called when the activity is first created. */
    private Button authButton;
    private Button listServersButton;
    private Button getServerButton;
    private TextView info;
    private EditText username;
    private EditText password;
    private Button signInButton;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText)this.findViewById(R.id.UsernameET);
        password = (EditText)this.findViewById(R.id.PasswordET);
        signInButton = (Button)this.findViewById(R.id.SignInButton);
        url = this.getResources().getText(R.string.auth_url).toString();
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                setContentView(R.layout.main2);
                authButton = (Button)findViewById(R.id.AuthButton);
                listServersButton = (Button)findViewById(R.id.ListServersButton);
                getServerButton = (Button)findViewById(R.id.GetServerButton);
                info = (TextView)findViewById(R.id.InfoTV);
                authButton.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View v)
                    {
                        Tester.Holder h = Tester.GetInfo(url, username.getText().toString(), password.getText().toString());
                        String s = "Token: " + h.authToken + "\n\nCloud Servers URL: " + h.productURL;
                        info.setText(s);
                    }

                });
                listServersButton.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View v)
                    {
                        Tester.Holder h = Tester.GetInfo(url, username.getText().toString(), password.getText().toString());
                        info.setText(h.listServersBody);
                    }
                });
                getServerButton.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View v)
                    {
                        Tester.Holder h = Tester.GetInfo(url, username.getText().toString(), password.getText().toString());
                        info.setText(h.serversDetailBody);
                    }
                });
            }
        });

    }

}