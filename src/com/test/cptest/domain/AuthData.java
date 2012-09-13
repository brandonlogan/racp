package com.test.cptest.domain;

import org.json.JSONArray;
import org.json.JSONException;

public class AuthData {
    public String authToken;
    public String cloudServersAddress;
    
    public AuthData(SimpleHttpResponse resp){
        authToken = "";
        try
        {
            authToken = resp.jsonBody.getJSONObject("access").getJSONObject("token").getString("id");
        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        cloudServersAddress = "";
        JSONArray serviceCatalog = null;
        JSONArray cloudServersEndpoints = null;
        try
        {
            serviceCatalog = resp.jsonBody.getJSONObject("access").getJSONArray("serviceCatalog");
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
    }
}
