package com.bloodarg.auth;


import java.util.Calendar;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
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
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
   

   
    @Bean
    public static WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
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
