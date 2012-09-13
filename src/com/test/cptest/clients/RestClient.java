package com.test.cptest.clients;

import com.test.cptest.domain.SimpleHttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class RestClient extends BaseClient
{
	HttpClient client;
	
	public RestClient(){
		client = new DefaultHttpClient();
	}
	
	public SimpleHttpResponse get(String url, Header[] headers){
		HttpGet request = new HttpGet(url);
		return doRequest(request, headers);
	}
	
	public SimpleHttpResponse put(String url, Header[] headers, StringEntity body){
		HttpPut request = new HttpPut(url);
		request.setEntity(body);
		return doRequest(request, headers);
	}
	
	public SimpleHttpResponse post(String url, Header[] headers, StringEntity body){
		HttpPost request = new HttpPost(url);
		request.setEntity(body);
		return doRequest(request, headers);
	}
	
	public SimpleHttpResponse delete(String url, Header[] headers){
		HttpDelete request = new HttpDelete(url);
		return doRequest(request, headers);
	}
	
	public SimpleHttpResponse doRequest(HttpRequestBase request, Header[] headers){
		for(Header h : headers){
			request.addHeader(h);
		}
		HttpResponse response = null;
		try
		{
			response = client.execute(request);
		} catch (ClientProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StatusLine status = response.getStatusLine();
		// Pull content stream from response
	    HttpEntity entity = response.getEntity();
	    InputStream inputStream = null;
		try
		{
			inputStream = entity.getContent();
		} catch (IllegalStateException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    ByteArrayOutputStream content = new ByteArrayOutputStream();
	
	    // Read response into a buffered stream
	    int readBytes = 0;
	    byte[] sBuffer = new byte[512];
	    try
		{
			while ((readBytes = inputStream.read(sBuffer)) != -1) {
			    content.write(sBuffer, 0, readBytes);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleHttpResponse(status.getStatusCode(), new String(content.toByteArray()), response.getAllHeaders());
	}
}
