package largacaixa.cli;

import pt.largacaixa.ws.*; // classes generated from WSDL

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;

import javax.xml.ws.*;
import javax.imageio.ImageIO;
import javax.annotation.PostConstruct;
import java.lang.Thread;
import static javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY;

/**
*	Classe LargaCaixaCliente
*		Permite a transferência de ficheiros do cliente para o servidor e do servidor para o cliente
*		Implementa uma consola para a interaccao do cliente
*
*/
public class LargaCaixaCliSec {
    
	private String endpointAddress; 
	private LargaCaixaPortType lc;
	
	private boolean primary=false;
	
	private String myAddress;
	
	private String _id;
	
	private String otherId;
	
	private String otherAddress;
	
	private LargaCaixaPortType port;
	
	private LargaCaixaService service;

	/**
	*	Construtor do cliente que trata da ligacao com o servidor
	*
	*/
	public LargaCaixaCliSec(String id) {
		
		_id=id;
		
		otherId=_id.equals("1")?"2":"1";
		
		myAddress = "http://localhost:8080/LargaCaixa"+id+"/endpoint";	//endereço deste serviço
		
		otherAddress = "http://localhost:8080/LargaCaixa"+otherId+"/endpoint"; //endereço do outro servico
		
		
		//binding + port
		service = new LargaCaixaService();
			
		port = service.getLargaCaixaPort();
	}
		
	/**
	verifica o endereço registado no uddi e se é deste serviço
	*/	
	public boolean isPrimary(String id){ 
		
		
		//procura o endereço registado
		Query query = new Query();
		endpointAddress = query.search();
		
		
		System.out.println("[LARGACAIXA_"+id+"] Registered Address is:"+endpointAddress);
		
		
		if (endpointAddress.equals(myAddress)){
			
			System.out.println("[LARGACAIXA_"+id+"] Eu sou primario										");
			
			//get endpoint adress
			BindingProvider bindingProvider = (BindingProvider) port;
			   
			Map<String, Object> requestContext = bindingProvider.getRequestContext();
				
			System.out.println("Secondary Web Service endpoint address:");
			 // retrieve old address
			String oldEndpointAddress = (String) requestContext.get(ENDPOINT_ADDRESS_PROPERTY);	
			System.out.println(otherAddress);
			
			// set endpoint address
			requestContext.put(ENDPOINT_ADDRESS_PROPERTY, otherAddress);	
			
			lc = port;
			
			primary = true;
			return true;
		
		}else{
		
			System.out.println("[LARGACAIXA_"+id+"] o endereço que existe nao e' o meu(sou secundario)					");
			
			primary = false;
			return false;
			
		}
				
			
	}


	
	public LargaCaixaPortType getPort(){
           return lc;
    }
	public String getEndpoint(){
		return myAddress;
	}
		 
}

 
 
