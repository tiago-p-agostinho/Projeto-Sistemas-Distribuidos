package largacaixa.ws.impl;

import largacaixa.cli.*;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.WebServiceContext;
import java.util.Enumeration;
import largacaixa.ws.impl.*;
import javax.jws.*;
import javax.annotation.PostConstruct;
import pt.largacaixa.ws.*; // classes generated from WSDL
import java.util.*;
import javax.naming.*;



@WebService( endpointInterface="pt.largacaixa.ws.LargaCaixaPortType",
	wsdlLocation="LargaCaixa.wsdl",
    name="LargaCaixaPortType",
    portName="LargaCaixaPort",
    targetNamespace="http://ws.largacaixa.pt/",
    serviceName="LargaCaixaService"
	)
@HandlerChain(file = "/handler-chain.xml")
/**
*	Implemetacao da interface do WebService que aplica o servico LargaCaixa  
*	
*
*
*
*/	
public class LargaCaixaImpl implements LargaCaixaPortType {
	

	private static final String address = "localhost:8080/Largacaixa1/endpoint";
	
	
	private boolean primary=false;
	
	private LargaSQL sql;
	
    private LargaCaixaCliSec localClient;
	
	private LargaCaixaPortType port;
	
	private String id;
	
	private String otherId;
	
	private String HelloAdress="localhost:8080/Largacaixa"+otherId+"/endpoint";
	
	
	@PostConstruct
	private void init(){
	
		id = getServiceNumber();
	
		otherId=id.equals("1")?"2":"1";
	
		sql = new LargaSQL(id);
		
		
		sql.mySQLInit();
	
		String[] s = {"Alice","Bruno","Carlos","mariazinha","zeninguem","ist"};

		
		for (String caixaId:s)
			sql.mySQLCaixa(caixaId);
			
		System.out.println("[LARGACAIXA_"+id+"] a");	
		primary=amPrimary(id); //cria o cliente local e verifica se é primário
		port=localClient.getPort();
		System.out.println("[LARGACAIXA_"+id+"] b --primary?"+primary);
		
		
		/*if (!primary)
			setSecundary();*/
			
		
	}
	
	
	
	
  private static String name;

  public static void setName (String name) {
    LargaCaixaImpl.name = name;
  }
	
	
	public void criarConteudo(String cid, String caixa, int preco,byte[] dados) 
		throws CaixaInexistente, ConteudoInvalido{
			
			
		
			if (sql.existeConteudoEmTodos(cid))
				throw new ConteudoInvalido(cid, new ConteudoInvalidoType());
			if (!sql.existeCaixa(caixa))
				throw new CaixaInexistente(caixa, new CaixaInexistenteType());		
			if (preco<0)
				throw new ConteudoInvalido(cid, new ConteudoInvalidoType());
			if (dados == null || dados.length==0)
				throw new ConteudoInvalido(cid,new ConteudoInvalidoType());
				
			sql.mySQLCriar(cid,caixa,preco,dados);
			if (primary){
				port.criarConteudo(cid,caixa,preco,dados);
				System.out.println("[CONSOLE_DEBUG_"+id+"]  im primary: updated");
			}else
				System.out.println("[CONSOLE_DEBUG_"+id+"]  im secondary: not updated");
			
				
	}
	
	public void apagarConteudo(String cid, String caixa) throws CaixaInexistente, ConteudoInexistenteNaCaixa{
		if (!sql.existeCaixa(caixa))
			throw new CaixaInexistente(caixa, new CaixaInexistenteType());		
		if (!sql.existeConteudo(cid,caixa))
			throw new ConteudoInexistenteNaCaixa(caixa, new ConteudoInexistenteNaCaixaType());
			
		sql.mySQLApagar(cid,caixa);
		
		if (primary){
				port.apagarConteudo(cid,caixa);
				System.out.println("[CONSOLE_DEBUG_"+id+"]  im primary: apagado");
			}else
				System.out.println("[CONSOLE_DEBUG_"+id+"]  im secondary: nao apagado");
		
	}
	
	public List<String> listarConteudos(String caixa) throws CaixaInexistente{
		if (!sql.existeCaixa(caixa))
				throw new CaixaInexistente(caixa, new CaixaInexistenteType());	
		  
		return sql.mySQLListar(caixa);
	}
	
	
	
	public byte[] obterConteudo(String cid,String caixa) throws ConteudoInexistenteNaCaixa, CaixaInexistente{
		if (!sql.existeCaixa(caixa))
				throw new CaixaInexistente(caixa, new CaixaInexistenteType());	
		if (!sql.existeConteudo(cid,caixa))
			throw new ConteudoInexistenteNaCaixa(caixa, new ConteudoInexistenteNaCaixaType());
			
		return sql.mySQLObter(cid,caixa);
	}
	
	public void partilharConteudo(String cid, String caixaOrigem, String caixaDestino, Object comprovativoPagamento)
		throws CaixaInexistente, ConteudoInexistenteNaCaixa, ComprovativoRejeitado{
		if (!sql.existeCaixa(caixaOrigem))
			throw new CaixaInexistente(caixaOrigem, new CaixaInexistenteType());	
		if (!sql.existeCaixa(caixaDestino))
			throw new CaixaInexistente(caixaDestino, new CaixaInexistenteType());	
		if (!sql.existeConteudo(cid,caixaOrigem))
			throw new ConteudoInexistenteNaCaixa(caixaOrigem, new ConteudoInexistenteNaCaixaType());			
		try{
			if(Decrypto.descipher("priv.key", "pub.key", cid, comprovativoPagamento))
				throw new ComprovativoRejeitado(cid, new ComprovativoRejeitadoType());
			}
			catch(Exception e){
				System.out.println(e); 
			
			
		sql.mySQLPartilhar(cid,caixaOrigem,caixaDestino);
		if (primary){
				port.partilharConteudo(cid,caixaOrigem,caixaDestino,comprovativoPagamento);
				System.out.println("[CONSOLE_DEBUG_"+id+"]  im primary: partilhado");
			}else
				System.out.println("[CONSOLE_DEBUG_"+id+"]  im secondary: nao partilhado");
	
	}
}
	
	
	
	
	
	
	/*----------------------------------------------------------------------*/
	/*SERVER PART*/
	
	
	
	
	public String getServiceNumber(){
		//	System.out.println(MessageContext.SERVLET_CONTEXT);
		// Get the base naming context
		try{
		Context env = (Context)new InitialContext().lookup("java:comp/env");

		// Get a single value
		return (String)env.lookup("LargaCaixa");
		}catch(Exception e){
			return null;
		}
	}
	
	
	public boolean amPrimary(String id){
	
		localClient = new LargaCaixaCliSec(getServiceNumber());
		return localClient.isPrimary(id);
		
	
	}

		
	
}