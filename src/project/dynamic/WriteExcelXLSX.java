package project.dynamic;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelXLSX extends WriteExcel {
	
	private	XSSFWorkbook book;
	private	XSSFSheet sheet;
	private CellStyleList cstyle;
	
	//Construtor para deixar a planiha somente em mem�ria
	public WriteExcelXLSX(ByteArrayOutputStream bos) {
		super(bos);
		this.book = new XSSFWorkbook();
		this.sheet = book.createSheet("Teste");	
		this.cstyle = new CellStyleList(book);
	}
	
	//Construtor para gravar planilha em disco	
	public WriteExcelXLSX(String filePath) {
		super(filePath);
		this.book = new XSSFWorkbook();
		this.sheet = book.createSheet("Teste");
		this.cstyle = new CellStyleList(book);
	}	
	
	//Receber um array que s�o os t�tulos das colunas
	@Override
	public void writeHeaders(String headers[]) {
		XSSFRow rw = sheet.createRow(0);
		XSSFCell cl;
		for(int i = 0; i < headers.length; i++) {
			cl = rw.createCell(i);
			cl.setCellValue(headers[i]);
			cl.setCellStyle(cstyle.HeadStyle());
			sheet.autoSizeColumn(cl.getColumnIndex());
		}		
	}
	
	//Gera um excel baseado no resultado da query do banco de dados
	@Override
	public void writeBodyResultSet(ResultSet rs) throws ParseException {
		XSSFRow rw ;
		XSSFCell cl;
		int j = 1;
		try {			
			ResultSetMetaData rmdt = rs.getMetaData();
			//System.out.println("A quantidade de colunas �: " + rmdt.getColumnCount());
			rs.first();
			
			while(rs.next()) {
				rw = sheet.createRow(j);
				
				for(int i = 1; i<= rmdt.getColumnCount();i++ ) {
					cl = rw.createCell(i-1);
					if(rs.getObject(i) != null) {
						writeCellContent(cl,rs.getObject(i).getClass().toString(),rs.getObject(i).toString());
					}else {
						cl.setCellStyle(cstyle.BodyStyle());
					}						
				}
				j++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void flushByte() {
		try {
			book.write(bos);
			//book.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void flushFile() {
		try {
			book.write(fos);
			book.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private void writeCellContent(XSSFCell cell, String st,String value) throws ParseException{
		//System.out.println("O Objeto passado �: "+ st );
	
		if(st.contains("String")) {
			cell.setCellValue(value);
			cell.setCellStyle(cstyle.BodyStyle());
			sheet.autoSizeColumn(cell.getColumnIndex());
		}else if(st.contains("Integer")) {
			cell.setCellValue(Integer.parseInt(value));
			cell.setCellStyle(cstyle.BodyStyle());
			sheet.autoSizeColumn(cell.getColumnIndex());
		}else if (st.contains("BigDecimal")){
			cell.setCellValue(Double.parseDouble(value));
			cell.setCellStyle(cstyle.BodyStyle());
			sheet.autoSizeColumn(cell.getColumnIndex());
		}else if(st.contains("Date")){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			cell.setCellValue(df.parse(value));
			cell.setCellStyle(cstyle.DataStyle());			
			sheet.autoSizeColumn(cell.getColumnIndex());
		}else {
			System.out.println("Cell Type not found");
		}			
	}
}
