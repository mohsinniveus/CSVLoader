package com.niveus.CSVLoader.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBUtility {
	
	
	@Value("${spring.datasource.url}")
    private String dbUrl;
	
	@Value("${spring.datasource.username}")
    private String dbUser;
	
	
	@Value("${spring.datasource.password}")
    private String dbPassword;
	
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    
		return conn;
		
	}
	
public void getPendingJobsDetails() {
	System.out.println("test111111");
		
		try {
				Connection conn = this.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT column_name, data_type \n"
						+ "FROM information_schema.columns\n"
						+ "WHERE table_name = 'emp' AND table_schema = 'public'");
	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	            	System.out.println("test22222");
	            	System.out.println(resultSet.getInt("column_name"));
	            }
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
