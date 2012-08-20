package com.test.cptest.domain;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SimpleHttpResponse
{
	public int statusCode;
	public String body;
	public Header[] headers;
	public JSONObject jsonBody;
	
	public SimpleHttpResponse(int statusCode, String body, Header[] headers){
		this.statusCode = statusCode;
		this.body = body;
		this.headers = headers;
		this.jsonBody = this.getJSON(this.body);
	}
	
	private JSONObject getJSON(String str){
		if(str == null || str.length() == 0){
			return null;
		}
		JSONObject temp = null;
		try
		{
			temp = new JSONObject(str);
		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return temp;
	}
}
