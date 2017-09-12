package com.example.demobootsecurity.api;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyApis {

	@GetMapping(path="connectedUser")
	public Object getConnectedUser(Principal principal) {
		return principal;
	}
}
