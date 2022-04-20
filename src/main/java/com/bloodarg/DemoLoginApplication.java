package com.bloodarg;

import java.sql.SQLException;

import com.bloodarg.auth.JwtAuth;
import com.bloodarg.db.MySQLlite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoLoginApplication {

	public static void main(String[] args) {
		
		try {
			MySQLlite.startDb();
			JwtAuth.startJwt();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		SpringApplication.run(DemoLoginApplication.class, args);
	}
	



}