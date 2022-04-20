package com.bloodarg.router;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import com.bloodarg.auth.JwtAuth;
import com.bloodarg.db.MySQLlite;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping(  
    path = "api"
)
public class ListUserRouter {


    @GetMapping("/v1/userlist")
    public ResponseEntity<HashMap<String, Object>> readUserList(@CookieValue(value = "Authentication", defaultValue = "null") String token) {
        HashMap<String, Object> map = new HashMap<>();
        if (token.equals("null")) {
            map.put("status", "UNAUTHORIZED");
            return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
        }
        var tokenV= JwtAuth.validateToken(token);     
        if (tokenV==null) {
            return new ResponseEntity<>(map,HttpStatus.UNAUTHORIZED);
        }
        var list = MySQLlite.getUsers();

        map.put("status", "ok");
        map.put("users", list);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    
}
