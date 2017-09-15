package com.example.demobootsecurity.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demobootsecurity.util.StringEncryptor;

@Controller
public class MyController {

	@GetMapping(path="external_app")
	public String redirect(HttpServletRequest request) {
		String redirectUrl = request.getScheme() + "://www.yahoo.com";
	    return "redirect:" + redirectUrl;	    		
	}
	
	@GetMapping("/external_app2")
	public ResponseEntity<Object> redirectToExternalUrl(HttpServletRequest request) throws URISyntaxException {
		String redirectUrl = request.getScheme() + "://www.google.com";
	    URI google = new URI(redirectUrl);
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(google);
	    httpHeaders.add("mmsUser", "admin");
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	/*
	@GetMapping("/external_app3")
	public ResponseEntity<Object> redirectToExternalUrl2(HttpServletRequest request) {
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHH");
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("login", "admin");
			map.put("connectionTime", sdf.format(new Date()));
			
			String[] encrypted = StringEncryptor.encryptObject(map);
			
			String redirectUrl = request.getScheme() + "://localhost:8181?d="+encrypted[0]+"&v="+encrypted[1];
			
			URI google = new URI(redirectUrl);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(google);
			
			
			return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
	}
	*/
	@GetMapping("/external_app3")
	public String redirectToExternalUrl2(HttpServletRequest request) {
		
		try {
			String connectionTimeFormat = "ddMMyyyyHH";
			SimpleDateFormat sdf = new SimpleDateFormat(connectionTimeFormat);
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("login", "'admin'");
			map.put("connectionTime", "'" + sdf.format(new Date()) + "'");
			map.put("connectionTimeFormat", "'" + connectionTimeFormat + "'");			
			
			String[] encrypted = StringEncryptor.encryptObject(map);
			
			String redirectUrl = request.getScheme() + "://localhost:8181?d="+encrypted[0]+"&v="+encrypted[1];
			
			return "redirect:" + redirectUrl;	  
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "";
		}
	    
	}
}
