package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Article;
import domain.Magazine;

public class SQLController {
	
	private Connection con;
	
	public SQLController() {}
	
	public boolean createDBConnection(String dbName, String dbUserName, String dbPassword) {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	//	Database name: cnudb, User name: root, User password: dbpassword
        	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false", dbUserName, dbPassword); 
        	        	
        	return true;
        } catch (Exception e) {
            e.printStackTrace();            
        }
    	return false;
	}
	
	public List<Magazine> getMagazines() {
		List<Magazine> magazines = new ArrayList<>();
    	ResultSet rs;
    	Statement stmt;
    	String selectQuery = "select * from magazines";

    	try {
    		stmt = con.createStatement();
    		
			rs = stmt.executeQuery(selectQuery);
			
	    	while(rs.next())
	    		magazines.add(new Magazine(rs.getLong(1), rs.getString(2), rs.getInt(3), getArticles(rs.getLong(1))));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return magazines;
	}
	
	public List<Article> getArticles(long magazineId) {
		
		List<Article> articles = new ArrayList<>();
		Statement stmt;
    	ResultSet rs;
    	String selectQuery = "select * from articles where magazine_id=" + magazineId;

    	try {
    		stmt = con.createStatement();

			rs = stmt.executeQuery(selectQuery);
			
	    	while(rs.next())
	    		articles.add(new Article(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5)));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
		
	}
	
	public boolean closeDBConnection() {
		try {
			con.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
	}
}
