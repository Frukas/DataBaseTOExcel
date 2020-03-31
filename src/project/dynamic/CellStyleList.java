package project.dynamic;

//Classe responsavel por formatar as celulas da planilha
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellStyleList {
	
	private	XSSFWorkbook book;
	
	public CellStyleList(XSSFWorkbook workbook) {
		this.book = workbook;
	}
	//Fomatação dos Título
	public CellStyle HeadStyle() {
		CellStyle headStyle = book.createCellStyle();
		headStyle.setBorderBottom(BorderStyle.THICK	);
		headStyle.setBorderLeft(BorderStyle.THICK);
		headStyle.setBorderRight(BorderStyle.THICK);
		headStyle.setBorderTop(BorderStyle.THICK);
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		
		return headStyle;
	}
	//Formatação dos dados da planilha
	public CellStyle BodyStyle() {
		CellStyle bodyStyle = book.createCellStyle();
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		bodyStyle.setAlignment(HorizontalAlignment.RIGHT);
		
		return bodyStyle;
	}
	//Formatação especial para as celulas com data
	public CellStyle DataStyle() {
		CellStyle dataStyle = book.createCellStyle();
		DataFormat dform = book.createDataFormat();
		dataStyle.setDataFormat(dform.getFormat("yyyy/mm/dd"));
		dataStyle.setWrapText(true);
		dataStyle.setBorderBottom(BorderStyle.THIN);
		dataStyle.setBorderLeft(BorderStyle.THIN);
		dataStyle.setBorderRight(BorderStyle.THIN);
		dataStyle.setAlignment(HorizontalAlignment.RIGHT);
		
		return dataStyle;		
	}

}
