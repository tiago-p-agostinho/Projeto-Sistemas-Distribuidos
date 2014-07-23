package largacaixa.cli;

import largacaixa.ws.impl.*; // classes generated from WSDL
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import largacaixa.ws.impl.UDDIContext;
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
public class HelloCli {
    
	private String endpointAddress; 
	private Hello lc;
	private Hello port;
	private boolean primary=false;
	private HelloImplService service;
	private String myAddress;
	private String myAddressEnd;
	private String _id;
	
	private String otherId;
	
	private String otherAddress;
	
	
	/**
	*	Construtor do cliente que trata da ligacao com o servidor
	*
	*/
	public HelloCli(String id) {
		
		_id=id;
		
		otherId=_id.equals("1")?"2":"1";
		
		myAddress = "http://localhost:8080/LargaCaixa"+id+"/Hello";	//endereço deste serviço
		myAddressEnd = "http://localhost:8080/LargaCaixa"+id+"/endpoint";
		otherAddress = "http://localhost:8080/LargaCaixa"+otherId+"/Hello"; //endereço do outro servico
		
		//binding + port
		 service = new HelloImplService();
			
		 port = service.getHelloImplPort();
		}
		
		public boolean primario(String id){
		
		//procura o endereço registado
		Query query = new Query();
		endpointAddress = query.search();
		
	   String delimiter = "/";
	   String[] temp;
	   String[] line;
	   temp = endpointAddress.split(delimiter);
	   line = myAddress.split(delimiter);
	   
		System.out.println("[LARGACAIXA_"+id+"] Registered Address is:"+endpointAddress);
		
		if (temp[3].equals(line[3])){
			System.out.println("[LARGACAIXA_"+id+"] Eu sou primario");
			
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
		
			System.out.println("[LARGACAIXA_"+id+"] o endereço que existe nao e' o meu(sou secundario)");
			
			
			primary = false;
			return false;
		}	
			
	}
	
	
	public Hello getPort(){
            return lc;
    }
	
	public void CallSayHelloOneway(Hello port) {
     while(true){
      try{	 
     	port.sayHelloOneway();
       }
         catch(Exception e){
		   
		 }
       	// pause for 7 seconds	 
      try{		
		Thread.sleep(7000);
		} catch (IllegalArgumentException iae) {
          System.out.println("ERROR: In HelloImpl.sayHelloOneway, sleep called with invalid argument.");
        } catch (InterruptedException ie) {
          System.out.println("ERROR: In HelloImpl.sayHelloOneway, sleep interrupted by another thread.");
        }
       }  
    }

	
	public void verifica(){
	
	while(true){
	
	 if(HelloImpl.n == 1){
	    HelloImpl.n = 0;
		}
	try{		
		Thread.sleep(11000);
		} catch (IllegalArgumentException iae) {
          System.out.println("ERROR: In HelloImpl.sayHelloOneway, sleep called with invalid argument.");
        } catch (InterruptedException ie) {
          System.out.println("ERROR: In HelloImpl.sayHelloOneway, sleep interrupted by another thread.");
        }
		if(HelloImpl.n == 0){
           UDDIContext c = new UDDIContext();
            c.contextInitialized(myAddressEnd);
           			
	}
   }
  }
   
}


 
 
