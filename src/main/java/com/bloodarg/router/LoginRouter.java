package com.bloodarg.router;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.net.http.HttpHeaders;
import java.util.HashMap;

import com.bloodarg.auth.JwtAuth;
import com.bloodarg.db.MySQLlite;
import com.bloodarg.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(  
    path = "auth",
    consumes = {MediaType.APPLICATION_JSON_VALUE}// MediaType.APPLICATION_FORM_URLENCODED_VALUE for other php for example will be good
)
public class LoginRouter {

	@PostMapping("/login")
	public ResponseEntity<HashMap<String, String>> login(@RequestBody User user,HttpServletResponse response) {
        HashMap<String, String> map = new HashMap<>();
		boolean existe = MySQLlite.isUserName(user.name);
        if(existe==false){
            map.put("status", "User name not found or incorrect password");
            return new ResponseEntity<HashMap<String, String>>(map,HttpStatus.BAD_REQUEST);
        }
		String password = MySQLlite.getPasswordUserFromDB(user.name);
		var result = JwtAuth.comparePassword(user.password, password);
		if(result==false){
            map.put("status", "User name not found or incorrect password");
            return new ResponseEntity<HashMap<String, String>>(map,HttpStatus.BAD_REQUEST);
        }
        Cookie cookie = new Cookie("Authentication",JwtAuth.getToken());

       
        cookie.setMaxAge(60 * 60);//1h
    
        cookie.setPath("/");
    
        // add cookie to response
        response.addCookie(cookie);    

        map.put("status", "ok");

	    return new ResponseEntity<HashMap<String, String>>(map,HttpStatus.OK) ;
	}

}
