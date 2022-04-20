package com.bloodarg.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


    public class MySQLlite
    {
      

      public static void startDb() throws SQLException{
        try
        {
          // create a database connection
          connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        }
        catch(SQLException e){
          System.out.println(e);
          throw e;
        }
        //TODO add create tables to db if no exist

      }
      public static ArrayList<String> getUsers(){
        var list = new ArrayList<String>();

        try (Statement statement = connection.createStatement()) 
        {
          statement.setQueryTimeout(30);
          ResultSet rs = statement.executeQuery("SELECT *  FROM users;");

          while(rs.next())
          {
            list.add(rs.getString(1));
          }

          return list;
         

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

      }
      public static boolean isUserName(String name) {

        try (Statement statement = connection.createStatement()) 
        {
          statement.setQueryTimeout(30);
          ResultSet rs = statement.executeQuery("SELECT *  FROM users where username='"+name+"';");
          int counter =0;
          while(rs.next())//change this entire block, is not good practice.
          {
            counter=counter+1;
          }
          if(counter==0){
            return false;
          }
          else{
            return true;
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        
        return false;
      }
      public static String getPasswordUserFromDB(String name) {
        try (Statement statement = connection.createStatement()) 
        {
          statement.setQueryTimeout(30);
          ResultSet rs = statement.executeQuery("SELECT *  FROM users where username='"+name+"';");

          if(rs.next())
          {
            return  rs.getString(2);
          }
          else{
            return null;
          }

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
       
      }
      public static int setUser(String username,String password){
        String consulta = "INSERT INTO users (username,password) VALUES (?,?);";
        try  
        {
          PreparedStatement sentencia = connection.prepareStatement(consulta);
          sentencia.setString(1, username);
          sentencia.setString(2, password);
         
          return  sentencia.executeUpdate();

        } catch (SQLException e) {
          e.printStackTrace();
          return -1;
        }
      }

     
      




      
      static Connection connection;








    
    }