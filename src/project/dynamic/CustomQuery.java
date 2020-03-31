package project.dynamic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomQuery {
	
	Connection con;
	String dataStart;
	
	public CustomQuery (Connection con) {
		this.con = con;
	}
	
	public ResultSet getCustomQuery(String dataStart) {
		String sql = "select* from orders where order_date > ?";
		ResultSet rs = null;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, dataStart);
			rs = st.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rs;		
	}
}
