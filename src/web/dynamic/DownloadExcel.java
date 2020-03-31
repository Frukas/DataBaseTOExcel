package web.dynamic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dynamic.CustomQuery;
import project.dynamic.WriteExcel;
import project.dynamic.dbaRetrive;

/**
 * Servlet implementation class DownloadExcel
 */
@WebServlet("/DownloadExcel")
public class DownloadExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=Text.xlsx");//Aqui pode mudar o nome do arquivo do download.
		String head[] = {"Order Id","Customer Id","Order Date","Status","Comments","Shipped Date","Shipped ID"};// Importante!! mudar para o título das colunas
		ServletOutputStream out = response.getOutputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ByteArrayInputStream  bis;		
		WriteExcel wrl = new WriteExcel(bos);
		dbaRetrive dbr = new dbaRetrive(); //<-comentar essa linha caso mude para ConnectionFactory
		//ConnectionFactory cof = new ConnectionFactory(); //<-Para  usar a classe que está acostumada
		
		
		// Importante!!!  Mudar a classe CustomQuery 
		CustomQuery cuq = new CustomQuery(dbr.getConnection()); //<-comentar essa linha caso mude para ConnectionFactory
		//CustomQuery cuq = new CustomQuery(cof.getConnection());//<- usar a classe que está acostumada
		
		String dataStart = request.getParameter("StData");
		String dataEnd = request.getParameter("EnData");
		//String uname = request.getParameter("uname"); //Caso acrescente nome operador
		int i;
		
		try {			
				wrl.writeBodyResultSet(cuq.getCustomQuery(dataStart, dataEnd));
				//wrl.writeBodyResultSet(cuq.getCustomQuery(dataStart, dataEnd, uname)); //Caso acrescente nome operador
				wrl.writeHeaders(head);
				wrl.flushByte();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bis = new ByteArrayInputStream(bos.toByteArray());
		
		while ((i=bis.read()) != -1) {  
			out.write(i);   
		} 
		bis.close();
		out.close();		
	}
}
