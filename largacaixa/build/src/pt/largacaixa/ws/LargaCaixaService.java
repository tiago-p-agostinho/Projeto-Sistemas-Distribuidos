
package pt.largacaixa.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "LargaCaixaService", targetNamespace = "http://ws.largacaixa.pt/", wsdlLocation = "file:/C:/Users/Karan/Desktop/Projecto2(9_MAIO)%20-HelloS%20-%202/largacaixa/WebContent/LargaCaixa.wsdl")
public class LargaCaixaService
    extends Service
{

    private final static URL LARGACAIXASERVICE_WSDL_LOCATION;
    private final static WebServiceException LARGACAIXASERVICE_EXCEPTION;
    private final static QName LARGACAIXASERVICE_QNAME = new QName("http://ws.largacaixa.pt/", "LargaCaixaService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/Karan/Desktop/Projecto2(9_MAIO)%20-HelloS%20-%202/largacaixa/WebContent/LargaCaixa.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LARGACAIXASERVICE_WSDL_LOCATION = url;
        LARGACAIXASERVICE_EXCEPTION = e;
    }

    public LargaCaixaService() {
        super(__getWsdlLocation(), LARGACAIXASERVICE_QNAME);
    }

    public LargaCaixaService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LARGACAIXASERVICE_QNAME, features);
    }

    public LargaCaixaService(URL wsdlLocation) {
        super(wsdlLocation, LARGACAIXASERVICE_QNAME);
    }

    public LargaCaixaService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LARGACAIXASERVICE_QNAME, features);
    }

    public LargaCaixaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LargaCaixaService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns LargaCaixaPortType
     */
    @WebEndpoint(name = "LargaCaixaPort")
    public LargaCaixaPortType getLargaCaixaPort() {
        return super.getPort(new QName("http://ws.largacaixa.pt/", "LargaCaixaPort"), LargaCaixaPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LargaCaixaPortType
     */
    @WebEndpoint(name = "LargaCaixaPort")
    public LargaCaixaPortType getLargaCaixaPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.largacaixa.pt/", "LargaCaixaPort"), LargaCaixaPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LARGACAIXASERVICE_EXCEPTION!= null) {
            throw LARGACAIXASERVICE_EXCEPTION;
        }
        return LARGACAIXASERVICE_WSDL_LOCATION;
    }

}
