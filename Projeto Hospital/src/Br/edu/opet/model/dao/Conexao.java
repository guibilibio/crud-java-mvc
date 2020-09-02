package Br.edu.opet.model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
public class Conexao { 	// Serializada


	public static Connection getConexao() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","system","system");

		return conn;  
 	} 
}
