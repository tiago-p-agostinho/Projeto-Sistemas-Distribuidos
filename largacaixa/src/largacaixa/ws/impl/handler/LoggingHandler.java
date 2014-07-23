package largacaixa.ws.impl.handler;

import java.io.PrintStream;
import java.util.Set;
import java.util.Collections;


import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/*
 * This simple SOAPHandler will output the contents of incoming
 * and outgoing messages.
 */
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    
	public Set getHeaders(){
		return Collections.emptySet();
	
	}
	
    public boolean handleMessage(SOAPMessageContext smc) {
        log(smc);
        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        log(smc);
        return true;
    }

    // nothing to clean up
    public void close(MessageContext messageContext) {
    }

    /*
     * Check the MESSAGE_OUTBOUND_PROPERTY in the context
     * to see if this is an outgoing or incoming message.
     * Write a brief message to the print stream and
     * output the message. The writeTo() method can throw
     * SOAPException or IOException
     */
    private void log(SOAPMessageContext smc) {

        SOAPMessage message = smc.getMessage();
        try {
            message.writeTo(System.out);
            System.out.println("\n\n\n\n\n");
        } catch (Exception e) {
            System.out.println("Exception in handler: " + e);
        }
    }

}
