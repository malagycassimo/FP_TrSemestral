 package modulo;

import java.sql.*;

public class ModuloConexao {
	//Metodo responsavel por restabelecar conexao com o banco 
   public static Connection conector() {
	 java.sql.Connection conexao = null;
	// A linha abaixo chama o drive ou o conector importado
	 String driver = "com.mysql.cj.jdbc.Driver";
	 // Armazenar informacao referente ao banco
	 String url = "jdbc:mysql://localhost:3306/dbschoold?characterEncoding=utf-8";
	 String user = "dbireport";
	 String password = "dbireport123"; 
	 //Estabelecendo a conexao com o banco
	 try {
		Class.forName(driver);
		conexao = DriverManager.getConnection(url, user, password);
		return conexao; 
	} catch (Exception e) {
		System.out.println(e);
		//A linha abaixo serve de apoio para esclarecer o erro 
		//System.out.println(e);
	    return null; 	
	}
	 
   } 	

}
