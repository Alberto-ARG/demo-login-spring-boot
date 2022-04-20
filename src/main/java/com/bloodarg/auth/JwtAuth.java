package com.bloodarg.auth;


import java.util.Calendar;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JwtAuth {

    public static void startJwt(){
        algorithm=Algorithm.HMAC256(secret);
    }
    public static String getToken() throws JWTCreationException {
        Calendar datenow = Calendar.getInstance();
        datenow.add(Calendar.HOUR, 1);//Set in Configuration File , this is the amount off time before expires the token
        return JWT.create()
        .withIssuer("BloodARG")
        .withExpiresAt(datenow.getTime())
        .sign(algorithm);
    }


    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    
    private static Algorithm algorithm;

    private   static  String secret ="notSecretAnymore";//move to sort off file o some type param in ejecution time
}
