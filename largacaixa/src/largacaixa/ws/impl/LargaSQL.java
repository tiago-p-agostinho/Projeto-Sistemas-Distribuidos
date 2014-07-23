package largacaixa.ws.impl;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

public class LargaSQL{

	private String _code ;

	private Connection _conn;

	public LargaSQL(String version){
	//jdbc:mysql://localhost:3306/largacaixa1
		_code=version;
		String name = "java:jboss/datasources/largacaixa"+_code;
		
		try{
		
			Context ctx = new InitialContext();
			DataSource ds = (javax.sql.DataSource) ctx.lookup(name);
		
			_conn = ds.getConnection();
		}catch(NamingException ne){
			System.out.println("--SQL_NAMING--(code is: '"+_code+ "') "+ne.getMessage());
		}catch(SQLException se){
			System.out.println("--SQL_EXCEPTION--(code is: '"+_code+"') "+se.getMessage());
		}
		
		
/*		try{
			XAConnection xaConn = xaDataSource.getXAConnection();
			_conn = xaConn.getConnection();
		}catch (SQLException e){
			System.out.println("[CONSOLE-SQL] Could not get connection: "+e.getMessage()+".");
		}*/
	}

	
/**
*	MySQLInit cria a base de dados "largacaixa1", se não existir, e 
*	cria uma nova tabela(se nao existir), com os campos necessários para guardar 
*	toda a informaçao necessaria.
*
*/
	public void mySQLInit(){
		
		mySQLUse();
		
		String sql =	"CREATE TABLE IF NOT EXISTS CAIXAS(\n"+
						"	CAIXA varchar(20) NOT NULL,\n"+
						"	PRIMARY KEY (CAIXA)"+
						"	);";
						
		try{				
			Statement s = _conn.createStatement();
			s.execute(sql);
		}catch(SQLException e){
			System.out.println("[CONSOLE-SQL] Nao conseguiu iniciar("+e.getMessage()+").");
		}
	}
	
	public void mySQLUse(){
		String sql = "USE largacaixa"+_code;
		try{
			Statement s = _conn.createStatement();
			s.execute(sql);
		}catch (SQLException e){
			System.out.println("[CONSOLE-SQL] Nao conseguiu usar base de dados("+e.getMessage()+").");
		}
	}
	
	/**	Cria uma caixa na base de dados (novo utilizador)
	*	Nao verifica se caixa ja existe ou nao (nao é necessario aqui)
	*
	*/
	public void mySQLCaixa(String caixa){
	
		mySQLUse();
	
		String sql = " INSERT INTO CAIXAS (CAIXA) VALUES (?) ON DUPLICATE KEY UPDATE CAIXA=CAIXA;";	//isto tá a criar problemas sempre que reinicia o servidor
		String sql1 ="	CREATE TABLE IF NOT EXISTS "+caixa+" (\n"+
					"		CONTEUDO varchar(20) NOT NULL,\n"+
					"		PRECO int NOT NULL,\n"+
					"		DATA MEDIUMBLOB NOT NULL\n"+
					"	);";
	
		try{
			PreparedStatement ps = _conn.prepareStatement(sql);
			ps.setString(1,caixa.toUpperCase());
			ps.executeUpdate();
			ps = _conn.prepareStatement(sql1);
			ps.execute();
		}catch (SQLException e){
			System.out.println("[CONSOLE-SQL] Nao foi possivel criar caixa ( "+e.getMessage()+" ).");
		}
	}
	
	public void mySQLCriar(String cid, String caixa,int preco,byte[] dados){
		
		mySQLUse();

			String sql = "INSERT INTO "+caixa+" (CONTEUDO,PRECO,DATA) VALUES (?,?,?);";
						 
		try{
			PreparedStatement ps = _conn.prepareStatement(sql);		
			ps.setString(1,cid);
			ps.setInt(2, preco);
			ps.setBytes(3, dados);  //suposto converter BLOB
			ps.executeUpdate();
			
			
		}catch (SQLException e){
			System.out.println("[CONSOLE-SQL] Erro na insercao de conteudo("+e.getMessage()+").");
		}
	}
	
	/**
	*	Apaga uma linha da tabela de conteudos da respectiva caixa
	*	
	*/
	public void mySQLApagar(String cid, String caixa){

		mySQLUse();
		
		String sql = "DELETE FROM "+caixa+" WHERE CONTEUDO = ?  ";		//preciso de algo mais para DELetE?
		try{
			PreparedStatement ps = _conn.prepareStatement(sql);
			ps.setString(1,cid);
			ps.executeUpdate();
		}catch (SQLException e){
			System.out.println("[CONSOLO-SQL] Nao foi apagado ("+e.getMessage()+").");
		}
	}

	
	
public byte[] mySQLObter(String cid,String caixa){

	mySQLUse();

	String sql = "SELECT DATA FROM "+caixa+" WHERE CONTEUDO = ? ";
	try{
		PreparedStatement ps = _conn.prepareStatement(sql);
		ps.setString(1,cid);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getBytes("DATA");
	}catch(SQLException e){
		System.out.println("[CONSOLO-SQL] Nao foi obtido ("+e.getMessage()+").");
		return null;
	}

}
	
	
	
	public List<String> mySQLListar (String caixa){
	
		mySQLUse();
	
		List<String> conts = new ArrayList<String>();
		String sql = "SELECT * FROM "+caixa+" ;";
		try{
			Statement stmt = _conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
				conts.add(rs.getString("CONTEUDO"));
			return conts;
			
		}catch (SQLException e){
			System.out.println("[CONSOLE-SQL] Erro na listagem da caixa ( "+e.getMessage()+" ).");
			return null;
		}
	}


	public void mySQLPartilhar(String cid, String caixaOrigem, String caixaDestino){
	
		mySQLUse();
	
		String sql = "SELECT * FROM "+caixaOrigem+" WHERE CONTEUDO = ? ;";
		try{
			PreparedStatement ps = _conn.prepareStatement(sql);
			ps.setString(1,cid);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			String conteudo = rs.getString("CONTEUDO");
			int preco = rs.getInt("PRECO");
			byte[] dados =rs.getBytes("DATA");
			mySQLCriar(conteudo,caixaDestino,preco,dados);

		}catch (SQLException e){
			System.out.println("[CONSOLE-SQL] Erro na listagem da caixa ( "+e.getMessage()+" ).");
		}	
	
	}
	
	public boolean existeConteudo(String cid,String caixa){
		
		mySQLUse();
	
		String sql = "SELECT * FROM "+caixa+" WHERE CONTEUDO=?;";
		
		try{
			PreparedStatement ps = _conn.prepareStatement(sql);
			ps.setString(1,cid);
			ResultSet rs = ps.executeQuery();
			
			/*rs.next();
			return (rs.getString("CONTEUDO") != null)		//penso que só existe uma linha e se nao existir, a linha é nula(nao sei se o rs.next permite)
			*/
			/*while(rs.next)			//-> outra possibilidade se o rs.next nao der logo falso entao existe um conteudo
				return true;
			return false;*/
			
			return rs.next();
			
		}catch (SQLException e){
			System.out.println("[CONSOLE-SQL] Erro na verificacao de conteudo( "+e.getMessage()+" ).");
			return false;
		}
	
	}
	/**
	*	Verifica se o conteudo existe para todas as caixas existentes 
	*
	*/
	public boolean existeConteudoEmTodos(String cid){
	
		mySQLUse();
	
		String sql = "SELECT * FROM CAIXAS;";
		try{
			Statement stmt = _conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next())
				if (existeConteudo(cid,rs.getString("CAIXA")))
					return true;
			return false;
		}catch (SQLException e){
			System.out.println("[CONSOLE-SQL] Erro na verificacao de conteudos( "+e.getMessage()+" ).");
			return false;
		}
	}
	
	
	public boolean existeCaixa(String caixa){
	
		mySQLUse();
	
		String sql = "SELECT * FROM CAIXAS WHERE CAIXA = ?;";
		try{
			PreparedStatement ps = _conn.prepareStatement(sql);
			ps.setString(1,caixa);
			ResultSet rs = ps.executeQuery();
			/*
			while(rs.next)			
				return true;
			return false;
			*/
			return rs.next();	//deve chegar
		}catch (SQLException  e){
			System.out.println("[CONSOLE-SQL] Erro na verificacao de caixas( "+e.getMessage()+" ).");
			return false;
		}
	}
	
	
	//NOTA: o add dá a extensao no cliente, o resto nao
	/*public String daExtensao(String cid){
	
	
	
	}	*/
}