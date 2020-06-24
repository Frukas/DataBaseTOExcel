package project.dynamic;

//Classe responsavel por criar e preencher o arquivo excel.
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class WriteExcel {
	

	protected FileOutputStream fos;
	protected ByteArrayOutputStream bos;
		
	//Construtor para deixar a planiha somente em mem�ria
	public WriteExcel(ByteArrayOutputStream bos) {
			this.bos = bos;			
	}
	
	//Construtor para gravar planilha em disco	
	public WriteExcel(String filePath) {
		try {
			this.fos = new FileOutputStream(filePath);
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	//Receber um array que s�o os t�tulos das colunas
	public void writeHeaders(String headers[]) {}
	
	//Gera um excel baseado no resultado da query do banco de dados
	public void writeBodyResultSet(ResultSet rs) throws ParseException {}
	
	public void flushByte() {}
	
	public void flushFile() {}
	
	private void writeCellContent(XSSFCell cell, String st,String value) throws ParseException{}	
		
}

