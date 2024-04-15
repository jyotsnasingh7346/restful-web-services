package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;

public class MicroserviceClient {


	public static void main(String[] args) throws IOException, InterruptedException {
		
		// 1st way : Using Http client & request
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder()
							.uri(URI.create("http://localhost:8080/api/v1/microservice"))
							.GET()
							.build();
		
		HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		// 2nd way : Using RestTemplate
		RestTemplate rest = new RestTemplate();
		String uri = "http://localhost:8080/users/{id}";
		
		ResponseEntity<String> respons = rest.getForEntity(uri, String.class);
		
		String jsonResponse = null;
		if (respons.getStatusCode() == HttpStatus.OK) {
			jsonResponse = respons.getBody();
		}
		
		Gson gson = new Gson();
		User user = gson.fromJson(jsonResponse, User.class);
		
		System.out.println(user.getName());
		System.out.println(user.getId());
		
	}
	
}
