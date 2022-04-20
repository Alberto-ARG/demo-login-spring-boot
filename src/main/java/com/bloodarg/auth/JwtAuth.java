package com.bloodarg.auth;


import java.util.Calendar;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;


public class JwtAuth {
   
    public static DecodedJWT validateToken(String token) {
        
       try {
        return JWT.require(algorithm).build().verify(token);
       } catch (Exception e) {
           e.printStackTrace();
            return null;
       }
   
        
    }

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

    public static String saltPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }
    public static boolean comparePassword(String candidate,String password){
      return BCrypt.checkpw(candidate, password);
    }
    


  
   
   
    /*
    @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/register").permitAll()
        .antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
        .antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
		.antMatchers("/addNewEmployee").hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
		.loginPage("/login").permitAll().and().logout().permitAll();
		http.csrf().disable();
	}*/
   

    
    private static Algorithm algorithm;

    private   static  String secret ="notSecretAnymore";//move to sort off file o some type param in ejecution time

   
  
}
