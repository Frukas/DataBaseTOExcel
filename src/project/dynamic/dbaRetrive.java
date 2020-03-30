package project.dynamic;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbaRetrive {

private ResultSet rs = null;
private Connection con;

public dbaRetrive(){
	dbaConnector();
	}
	
	
public void dbaConnector(){
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");  
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_store?useTimezone=true&serverTimezone=UTC","root","root");  
		
		PreparedStatement prst = con.prepareStatement("Select * from orders"); 
		rs = prst.executeQuery();		
	
	}catch(Exception e){
		
		System.out.println(e.toString());
	}	
}

public ResultSet getResultSet() {
	
	return rs;	
}

public void closeConnection() {
	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}




}
