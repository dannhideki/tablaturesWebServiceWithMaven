package com.project;

import static spark.Spark.get;
import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
import static spark.Spark.staticFileLocation;

import org.json.JSONException;
import org.json.JSONObject;

import spark.Request;
import spark.Response;
import spark.Route;



public class Server {
	private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8080;

	public static void main(String[] args) {
		setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public/");
		final MusicList list = new MusicList();
		
		list.addMusic(new Music("Note:Capo in the 3rd -- [G] How many special people change [F] How many lives...", new Specification("oasis", "champagnesupernova")));
		list.addMusic(new Music("Note:Capo in the 2rd -- [Em] Today is ...", new Specification("oasis", "wonderwall")));
		
		
		get(new Route("/tablature/:band/:music") {
	         @Override
	         public Object handle(Request request, Response response) {

	            response.header("Access-Control-Allow-Origin", "*");
	        	 
	     	    Music result = list.searchMusic(new Specification(request.params(":band"), request.params(":music")));

	     	    JSONObject jsonObj = new JSONObject();
	     	   
	     	    if(result!=null){
	     	    	
	     	    	
	     	    	try {
						jsonObj.put("chords", result.getChords());
						
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
	     	    	
	     	    	return jsonObj;
	     	    	
	     	    } else {
	     	    	try {
						jsonObj.put("status", "erro");
						
					} catch (JSONException e) {
						
						e.printStackTrace();
					}
	     	    	return jsonObj;
	     	    }
	     	    
	     	    
		   	}
    	});
		
	}
	
}