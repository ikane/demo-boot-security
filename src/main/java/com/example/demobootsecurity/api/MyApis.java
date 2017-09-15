package com.example.demobootsecurity.api;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demobootsecurity.util.StringEncryptor;

@RestController
public class MyApis {

	@GetMapping(path="connectedUser")
	public Object getConnectedUser(Principal principal) {
		return principal;
	}
	
	@GetMapping(value = "urlCrypted/{login}")
	public String getEncryptedUrl(@PathVariable String login) {
		
		String connectionTimeFormat = "ddMMyyyyHH";
		SimpleDateFormat sdf = new SimpleDateFormat(connectionTimeFormat);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("login",login);
		map.put("connectionTime",sdf.format(new Date()));
		map.put("connectionTimeFormat",connectionTimeFormat);
		
		
		try {		
			String[] encrypted = StringEncryptor.encryptObject(map);	
            // url may differ.. based upon project initial context
			String urlString = "http://localhost:8080/view?d="+encrypted[0]+"&v="+encrypted[1];
			System.out.println(urlString);
			return urlString;
		}catch(Exception e) {
			//logger.debug("Unable to encrypt view id: "+id, e);
		}	
		return "";
	}
	
	@GetMapping(path = "view")
	public String getDencryptedUrlView(@RequestParam String d, @RequestParam String v) throws Exception {
		// The data goes out urlEncoded.. but comes in decoded (No Need to url decode on the request)		
		Object obj = StringEncryptor.decryptObject(d, v);
		System.out.println("Object Decrypted: "+obj.toString());
		return obj.toString();
	}
}
