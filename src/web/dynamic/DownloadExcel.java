package web.dynamic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=TExt.xlsx");
		String head[] = {"Order Id","Customer Id","Order Date","Status","Comments","Shipped Date","Shipped ID"};
		ServletOutputStream out = response.getOutputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ByteArrayInputStream  bis;
		WriteExcel wrl = new WriteExcel(bos);
		dbaRetrive dbr = new dbaRetrive();
		int i;
		
		try {
			wrl.writeBodyResultSet(dbr.getResultSet());
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
