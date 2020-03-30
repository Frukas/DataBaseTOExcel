package project.dynamic;

import java.text.ParseException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dbaRetrive db = new dbaRetrive();
		String head[] = {"Order Id","Customer Id","Order Date","Status","Comments","Shipped Date","Shipped ID"};
		db.dbaConnector();
		
		WriteExcel we = new WriteExcel("C:\\TesteCSV\\TesteXLSX\\TesteExcel.xlsx");
		try {
			we.writeBodyResultSet(db.getResultSet());
			we.writeHeaders(head);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnection();
		we.flushFile();
	}
}
