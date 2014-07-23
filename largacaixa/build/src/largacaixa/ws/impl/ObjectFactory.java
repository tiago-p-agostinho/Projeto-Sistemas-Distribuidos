
package largacaixa.ws.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the largacaixa.ws.impl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayHelloOneway_QNAME = new QName("http://impl.ws.largaCaixa/", "sayHelloOneway");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: largacaixa.ws.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHelloOneway }
     * 
     */
    public SayHelloOneway createSayHelloOneway() {
        return new SayHelloOneway();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloOneway }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.ws.largaCaixa/", name = "sayHelloOneway")
    public JAXBElement<SayHelloOneway> createSayHelloOneway(SayHelloOneway value) {
        return new JAXBElement<SayHelloOneway>(_SayHelloOneway_QNAME, SayHelloOneway.class, null, value);
    }

}
