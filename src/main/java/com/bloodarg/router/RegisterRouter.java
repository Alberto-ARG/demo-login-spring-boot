package com.bloodarg.router;


import java.util.HashMap;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bloodarg.model.User;
import com.bloodarg.db.MySQLlite;
import com.bloodarg.auth.JwtAuth;


@RestController
@RequestMapping(  
    path = "auth",
    consumes = {MediaType.APPLICATION_JSON_VALUE}// MediaType.APPLICATION_FORM_URLENCODED_VALUE for other php for example will be good
)
public class RegisterRouter {
    
    @PostMapping("/register")
	public ResponseEntity<HashMap<String, String>> registerUser(@RequestBody User user) {
        HashMap<String, String> map = new HashMap<>();
       

        boolean existe = MySQLlite.isUserName(user.name);
        if(existe==true){
            map.put("status", "User  Registered");
            return new ResponseEntity<HashMap<String, String>>(map,HttpStatus.BAD_REQUEST);
             
        }
        var result = MySQLlite.setUser(user.name,JwtAuth.saltPassword(user.password));
        if(result==-1){
            map.put("status", "Error");
            return new ResponseEntity<HashMap<String, String>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
             
        }
        map.put("status", "ok");
	    return new ResponseEntity<HashMap<String, String>>(map,HttpStatus.OK);
	}



    
}
