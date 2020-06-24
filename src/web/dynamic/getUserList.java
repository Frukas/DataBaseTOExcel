package web.dynamic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dynamic.ConnectionFactory;

/**
 * Servlet implementation class getUserList
 */
@WebServlet("/getUserList")
public class getUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getUserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html" );
		
		ConnectionFactory cf = new ConnectionFactory();
		Connection con = cf.getConnection();
		String sqlquery = "SELECT DISTINCT usuario FROM test.ga_apontamentos";
		String nomes = "";
		
		
		try {
			PreparedStatement st = con.prepareStatement(sqlquery);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				nomes = nomes + rs.getString("usuario") + ",";
								
			}
			response.getWriter().write(nomes);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
