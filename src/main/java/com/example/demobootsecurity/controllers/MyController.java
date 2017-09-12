package com.example.demobootsecurity.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	@GetMapping("/external_app3")
	public ResponseEntity<Object> redirectToExternalUrl2(HttpServletRequest request) throws URISyntaxException {
		String redirectUrl = request.getScheme() + "://localhost:8181";
	    URI google = new URI(redirectUrl);
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(google);
	    httpHeaders.add("mmsUser", "admin");
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
}
