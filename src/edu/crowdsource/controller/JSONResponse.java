package edu.crowdsource.controller;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONResponse {
	   
    public  JSONObject createSuccessResponse(){
    	JSONObject result = new JSONObject();
    	try {
			result.put("error_code", 0);
			result.put("message" , "success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return result;
    }
}
