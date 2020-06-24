package project.dynamic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomQuery {
	
	Connection con;
	String dataStart;
	
	
	//Para instanciar é necessário receber a conexão com o banco de dados.
	public CustomQuery (Connection con) {
		this.con = con;
	}
	
	
	//Retorna um query com data e fim informados
	public ResultSet getCustomQuery(String dataStart, String dataEnd) {
		String sql = "select chamado  , nome , data , tempo  , descricao  , detalhamento  from xxxxx  where data >= ? and data <= ?";// mudar essa query para refletir a tabela sendo usado. O simbolo ? representa as datas.
		ResultSet rs = null;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, dataStart);
			st.setString(2, dataEnd);
			rs = st.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;			
	}
	//Caso acrescente nome operador
	public ResultSet getCustomQuery(String dataStart, String dataEnd, String Username) {
		String sql = "select chamado  , nome , data , tempo  , descricao  , detalhamento  from xxxxx  where data  > ? and data < ? and usuario = ?";// mudar essa query para refletir a tabela sendo usada
		ResultSet rs = null;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, dataStart);
			st.setString(2, dataEnd);
			st.setString(3, Username);
			rs = st.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;			
	}
}
