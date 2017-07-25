package com.verizon.kafkaconnect.socketio;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.json.JSONObject;



public class RestClient {

	 
	 static { 
		 HttpsURLConnection.setDefaultHostnameVerifier(new  HostnameVerifier() 
		 		{ public boolean verify(String hostname, SSLSession  session)
		 			{	  if (hostname.equals("192.168.0.3")) return true; return false;
		 			 }
		 		}); 
	 		}
	

public  String getAuthToken(String authTokenURL, String userName, String password) {
	
	Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));	
	WebTarget webTarget = client.target(authTokenURL);

	Invocation.Builder invocationBuilder = webTarget
			.request(MediaType.APPLICATION_JSON);
		
	String body="{\"grant_type\": \"password\",\"username\": "+userName+",\"password\": "+password+"}";

	//respon :: { "access_token": "zRApShiOxoCcBiFGPRhISKAbaUACWQBRqMPmaq40/NU=","token_type": "Bearer"}
	//header "Authorization: 'Bearer zRApShiOxoCcBiFGPRhISKAbaUACWQBRqMPmaq40/NU='" 
	//"access_token": "zRApShiOxoCcBiFGPRhISKAbaUACWQBRqMPmaq40/NU=","token_type": "Bearer"
	
	Response response = invocationBuilder.post(Entity.json(body));
	String responseString = response.readEntity(String.class);
	JSONObject json=new JSONObject(responseString);
	
	String token =json.getString("access_token")	;
	String tokenType=json.getString("token_type")	;
	
	System.out.println(token+tokenType);
	
	
	return tokenType+" "+token;
}


}