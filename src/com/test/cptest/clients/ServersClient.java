package com.test.cptest.clients;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;
import org.json.JSONArray;

import com.test.cptest.domain.SimpleHttpResponse;

public class ServersClient
{
	public static final String SUFFIX = "/servers";
	private String url;
	private String authToken;
	private RestClient client;
	private ArrayList<Header> headers;
	
	public ServersClient(String url, String authToken){
		this.url = url + this.SUFFIX;
		this.authToken = authToken;
		this.client = new RestClient();
		this.headers = new ArrayList<Header>();
		this.headers.add(new BasicHeader("X-Auth-Token", authToken));
	}
	
	public SimpleHttpResponse listServers(){
		Header[] h_array = new Header[this.headers.size()];
		return this.client.get(this.url, this.headers.toArray(h_array));
	}
	
	public SimpleHttpResponse getServer(int serverId){
		Header[] h_array = new Header[this.headers.size()];
		return this.client.get(this.url + "/" + String.valueOf(serverId), 
							   this.headers.toArray(h_array));
	}
	
	public SimpleHttpResponse createServer(String body){
		Header[] h_array = new Header[this.headers.size()];
		StringEntity bodyEntity = null;
		try
		{
			bodyEntity = new StringEntity(body);
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.client.post(this.url, 
								this.headers.toArray(h_array), 
								bodyEntity);
	}
	
	public SimpleHttpResponse updateServer(int serverId, String body){
		Header[] h_array = new Header[this.headers.size()];
		StringEntity bodyEntity = null;
		try
		{
			bodyEntity = new StringEntity(body);
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.client.put(this.url + "/" + String.valueOf(serverId), 
							   this.headers.toArray(h_array), 
							   bodyEntity);
	}
	
	public SimpleHttpResponse deleteServer(int serverId){
		Header[] h_array = new Header[this.headers.size()];
		return this.client.get(this.url + "/" + String.valueOf(serverId), 
							   this.headers.toArray(h_array));
	}
}
