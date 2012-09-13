package com.test.cptest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.cptest.helpers.AuthTask;
import com.test.cptest.clients.Auth;

public class Login extends Activity {
    /** Called when the activity is first created. */
    
    private EditText username;
    private EditText password;
    private Button signInButton;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Context context = this;
        username = (EditText)this.findViewById(R.id.UsernameET);
        password = (EditText)this.findViewById(R.id.PasswordET);
        signInButton = (Button)this.findViewById(R.id.SignInButton);
        url = this.getResources().getText(R.string.auth_url).toString();
        signInButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                Auth auth = new Auth(url, user, pass);
                new AuthTask(context, auth).launchTask();
            }
        });

    }

}