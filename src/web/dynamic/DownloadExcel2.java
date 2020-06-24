package web.dynamic;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dynamic.ConnectionFactory;
import project.dynamic.CustomQuery;
import project.dynamic.WriteExcel;
import project.dynamic.WriteExcelXLS;
import project.dynamic.WriteExcelXLSX;


/**
 * Servlet implementation class DownloadExcel
 */
@WebServlet("/DownloadExcel2")
public class DownloadExcel2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadExcel2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMM");
		WriteExcel wrl = null;
		ResultSet rsl;
		
		response.setContentType("application/octet-stream");
		//Aqui pode mudar o nome do arquivo do download.		
		ServletOutputStream out = response.getOutputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ByteArrayInputStream  bis;			
		
		
		
		if(request.getParameter("formato").contains("XLSX")) {			
		
			wrl = new WriteExcelXLSX(bos);
			response.setHeader("Content-Disposition", "attachment; filename="+ sdf.format(cl.getTime()) + "_carga_apontamentos_8hs.xlsx");
		}else {
			
			wrl = new WriteExcelXLS(bos);
			response.setHeader("Content-Disposition", "attachment; filename="+ sdf.format(cl.getTime()) + "_carga_apontamentos_8hs.xls");
		}		
				
		String Name = request.getParameter("uname");
		String StData = request.getParameter("StData");
		String EnData = request.getParameter("EnData");
				
		ConnectionFactory con = new ConnectionFactory();
		CustomQuery cmq = new CustomQuery(con.getConnection());
		
		String head[] = {"Chamado","Responável","Data", "Tempo", "Descrição", "Detalhamento"};// Importante!! mudar para o titulo das colunas
		
		if(request.getParameter("toogleUser") != null) {
			rsl = cmq.getCustomQuery(StData, EnData);			
		}else {
			rsl = cmq.getCustomQuery(StData, EnData, Name); //Importante colocar o resultado da Query do banco de dados
		}
		int i;
		
		try {			
				wrl.writeBodyResultSet(rsl);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
