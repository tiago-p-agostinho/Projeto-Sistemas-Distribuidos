package largacaixa.ws.impl;

import java.lang.Thread;
import largacaixa.ws.impl.*;
import largacaixa.cli.*;


class PrimeThread extends Thread {
        LargaCaixaImpl box = new LargaCaixaImpl();
	    HelloCli yellow = new HelloCli(box.getServiceNumber());
	    boolean k = yellow.primario(box.getServiceNumber());
             public void run() {
			    if ( k == true){
                    Hello porto = yellow.getPort();
                    yellow.CallSayHelloOneway(porto);
				}else{
                    yellow.verifica();				
			 }
		}
}