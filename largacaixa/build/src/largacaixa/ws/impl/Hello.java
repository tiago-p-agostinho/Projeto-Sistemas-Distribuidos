
package largacaixa.ws.impl;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Hello", targetNamespace = "http://impl.ws.largaCaixa/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Hello {


    /**
     * 
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "sayHelloOneway", targetNamespace = "http://impl.ws.largaCaixa/", className = "largacaixa.ws.impl.SayHelloOneway")
    public void sayHelloOneway();

}
