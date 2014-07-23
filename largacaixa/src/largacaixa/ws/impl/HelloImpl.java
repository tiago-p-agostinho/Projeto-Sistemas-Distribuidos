package largacaixa.ws.impl;

import javax.jws.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.Thread;
import largacaixa.ws.impl.*;

@WebService( endpointInterface="largacaixa.ws.impl.Hello",
	wsdlLocation="Hello.wsdl",
    name="Hello",
    portName="HelloImplPort",
    targetNamespace="http://impl.ws.largaCaixa/",
    serviceName="HelloImplService"
	)



public class HelloImpl implements Hello {

public static int n;	
    public void sayHelloOneway() {
      n = 1;
	  System.out.println("Ta a enviar");
    }
}
