package project.dynamic;

//Classe responsavel por formatar as celulas da planilha
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public class CellStyleList {
	
	private	Workbook book;	
	private CellStyle headStyle;
	private CellStyle bodyStyle;
	private CellStyle dataStyle;
	private 
	int in = 0;
	
	public CellStyleList(Workbook workbook) {
		this.book = workbook;
		setHeadStyle();
		setBodyStyle();
		setDataStyle();
		
	}
	
	private void setHeadStyle() {
		
		headStyle = book.createCellStyle();
		headStyle.setBorderBottom(BorderStyle.THICK	);
		headStyle.setBorderLeft(BorderStyle.THICK);
		headStyle.setBorderRight(BorderStyle.THICK);
		headStyle.setBorderTop(BorderStyle.THICK);
		headStyle.setAlignment(HorizontalAlignment.CENTER);
	}
	
	private void setBodyStyle() {	
		
		bodyStyle = book.createCellStyle();
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		bodyStyle.setAlignment(HorizontalAlignment.RIGHT);
	}
	
	private void setDataStyle(){
		
		dataStyle = book.createCellStyle();
		DataFormat dform = book.createDataFormat();
		dataStyle.setDataFormat(dform.getFormat("yyyy/mm/dd"));
		dataStyle.setWrapText(true);
		dataStyle.setBorderBottom(BorderStyle.THIN);
		dataStyle.setBorderLeft(BorderStyle.THIN);
		dataStyle.setBorderRight(BorderStyle.THIN);
		dataStyle.setAlignment(HorizontalAlignment.RIGHT);		
	}
	//Fomata��o dos T�tulo
	public CellStyle HeadStyle() {		
		
		return headStyle;
	}
	//Formata��o dos dados da planilha
	public CellStyle BodyStyle() {	
		
		return bodyStyle;
	}
	//Formata��o especial para as celulas com data
	public CellStyle DataStyle() {	
		
		return dataStyle;		
	}

}
