package com.test.cptest;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.test.cptest.clients.RestClient;
import com.test.cptest.clients.ServersClient;
import com.test.cptest.domain.SimpleHttpResponse;
import com.test.cptest.clients.Auth;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tester
{
	public static class Holder{
		public String authToken;
		public String productURL;
		public String listServersBody;
		public String serversDetailBody;
	}
	
	public static Holder GetInfo(String url, String username, String password){
		Tester.Holder h = new Tester.Holder();
        Auth auth = new Auth(url, username, password);
        SimpleHttpResponse test = auth.get_auth();
        String authToken = "";
        try
		{
			authToken = test.jsonBody.getJSONObject("access").getJSONObject("token").getString("id");
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cloudServersAddress = "";
		JSONArray serviceCatalog = null;
		JSONArray cloudServersEndpoints = null;
		try
		{
			serviceCatalog = test.jsonBody.getJSONObject("access").getJSONArray("serviceCatalog");
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < serviceCatalog.length(); i++){
			String productName = "";
	        try
			{
				productName = serviceCatalog.getJSONObject(i).getString("name");
			} catch (JSONException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(productName.equalsIgnoreCase("cloudServers")){
		        try
				{
					cloudServersAddress = serviceCatalog.getJSONObject(i).getJSONArray("endpoints").getJSONObject(0).getString("publicURL");
				} catch (JSONException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		ServersClient s_client = new ServersClient(cloudServersAddress, authToken);
		SimpleHttpResponse r = s_client.listServers();
		SimpleHttpResponse r2 = s_client.getServer(20720520);
		h.authToken = authToken;
		h.productURL = cloudServersAddress;
		h.listServersBody = r.body;
		h.serversDetailBody = r2.body;
		return h;
	}
}
