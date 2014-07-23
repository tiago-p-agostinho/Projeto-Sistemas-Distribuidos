
package pt.largacaixa.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.largacaixa.ws package. 
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

    private final static QName _CriarConteudo_QNAME = new QName("http://ws.largacaixa.pt/", "criarConteudo");
    private final static QName _CaixaInexistente_QNAME = new QName("http://ws.largacaixa.pt/", "CaixaInexistente");
    private final static QName _ComprovativoRejeitado_QNAME = new QName("http://ws.largacaixa.pt/", "ComprovativoRejeitado");
    private final static QName _ConteudoInvalido_QNAME = new QName("http://ws.largacaixa.pt/", "ConteudoInvalido");
    private final static QName _ApagarConteudo_QNAME = new QName("http://ws.largacaixa.pt/", "apagarConteudo");
    private final static QName _ObterConteudoResponse_QNAME = new QName("http://ws.largacaixa.pt/", "obterConteudoResponse");
    private final static QName _ApagarConteudoResponse_QNAME = new QName("http://ws.largacaixa.pt/", "apagarConteudoResponse");
    private final static QName _ListarConteudos_QNAME = new QName("http://ws.largacaixa.pt/", "listarConteudos");
    private final static QName _CriarConteudoResponse_QNAME = new QName("http://ws.largacaixa.pt/", "criarConteudoResponse");
    private final static QName _ListarConteudosResponse_QNAME = new QName("http://ws.largacaixa.pt/", "listarConteudosResponse");
    private final static QName _PartilharConteudo_QNAME = new QName("http://ws.largacaixa.pt/", "partilharConteudo");
    private final static QName _ObterConteudo_QNAME = new QName("http://ws.largacaixa.pt/", "obterConteudo");
    private final static QName _PartilharConteudoResponse_QNAME = new QName("http://ws.largacaixa.pt/", "partilharConteudoResponse");
    private final static QName _ConteudoInexistenteNaCaixa_QNAME = new QName("http://ws.largacaixa.pt/", "ConteudoInexistenteNaCaixa");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.largacaixa.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApagarConteudoResponseType }
     * 
     */
    public ApagarConteudoResponseType createApagarConteudoResponseType() {
        return new ApagarConteudoResponseType();
    }

    /**
     * Create an instance of {@link ListarConteudosType }
     * 
     */
    public ListarConteudosType createListarConteudosType() {
        return new ListarConteudosType();
    }

    /**
     * Create an instance of {@link CriarConteudoResponseType }
     * 
     */
    public CriarConteudoResponseType createCriarConteudoResponseType() {
        return new CriarConteudoResponseType();
    }

    /**
     * Create an instance of {@link CriarConteudoType }
     * 
     */
    public CriarConteudoType createCriarConteudoType() {
        return new CriarConteudoType();
    }

    /**
     * Create an instance of {@link ListarConteudosResponseType }
     * 
     */
    public ListarConteudosResponseType createListarConteudosResponseType() {
        return new ListarConteudosResponseType();
    }

    /**
     * Create an instance of {@link CaixaInexistenteType }
     * 
     */
    public CaixaInexistenteType createCaixaInexistenteType() {
        return new CaixaInexistenteType();
    }

    /**
     * Create an instance of {@link ComprovativoRejeitadoType }
     * 
     */
    public ComprovativoRejeitadoType createComprovativoRejeitadoType() {
        return new ComprovativoRejeitadoType();
    }

    /**
     * Create an instance of {@link PartilharConteudoType }
     * 
     */
    public PartilharConteudoType createPartilharConteudoType() {
        return new PartilharConteudoType();
    }

    /**
     * Create an instance of {@link ObterConteudoType }
     * 
     */
    public ObterConteudoType createObterConteudoType() {
        return new ObterConteudoType();
    }

    /**
     * Create an instance of {@link PartilharConteudoResponseType }
     * 
     */
    public PartilharConteudoResponseType createPartilharConteudoResponseType() {
        return new PartilharConteudoResponseType();
    }

    /**
     * Create an instance of {@link ConteudoInvalidoType }
     * 
     */
    public ConteudoInvalidoType createConteudoInvalidoType() {
        return new ConteudoInvalidoType();
    }

    /**
     * Create an instance of {@link ConteudoInexistenteNaCaixaType }
     * 
     */
    public ConteudoInexistenteNaCaixaType createConteudoInexistenteNaCaixaType() {
        return new ConteudoInexistenteNaCaixaType();
    }

    /**
     * Create an instance of {@link ObterConteudoResponseType }
     * 
     */
    public ObterConteudoResponseType createObterConteudoResponseType() {
        return new ObterConteudoResponseType();
    }

    /**
     * Create an instance of {@link ApagarConteudoType }
     * 
     */
    public ApagarConteudoType createApagarConteudoType() {
        return new ApagarConteudoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CriarConteudoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "criarConteudo")
    public JAXBElement<CriarConteudoType> createCriarConteudo(CriarConteudoType value) {
        return new JAXBElement<CriarConteudoType>(_CriarConteudo_QNAME, CriarConteudoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CaixaInexistenteType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "CaixaInexistente")
    public JAXBElement<CaixaInexistenteType> createCaixaInexistente(CaixaInexistenteType value) {
        return new JAXBElement<CaixaInexistenteType>(_CaixaInexistente_QNAME, CaixaInexistenteType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprovativoRejeitadoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "ComprovativoRejeitado")
    public JAXBElement<ComprovativoRejeitadoType> createComprovativoRejeitado(ComprovativoRejeitadoType value) {
        return new JAXBElement<ComprovativoRejeitadoType>(_ComprovativoRejeitado_QNAME, ComprovativoRejeitadoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConteudoInvalidoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "ConteudoInvalido")
    public JAXBElement<ConteudoInvalidoType> createConteudoInvalido(ConteudoInvalidoType value) {
        return new JAXBElement<ConteudoInvalidoType>(_ConteudoInvalido_QNAME, ConteudoInvalidoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApagarConteudoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "apagarConteudo")
    public JAXBElement<ApagarConteudoType> createApagarConteudo(ApagarConteudoType value) {
        return new JAXBElement<ApagarConteudoType>(_ApagarConteudo_QNAME, ApagarConteudoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterConteudoResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "obterConteudoResponse")
    public JAXBElement<ObterConteudoResponseType> createObterConteudoResponse(ObterConteudoResponseType value) {
        return new JAXBElement<ObterConteudoResponseType>(_ObterConteudoResponse_QNAME, ObterConteudoResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApagarConteudoResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "apagarConteudoResponse")
    public JAXBElement<ApagarConteudoResponseType> createApagarConteudoResponse(ApagarConteudoResponseType value) {
        return new JAXBElement<ApagarConteudoResponseType>(_ApagarConteudoResponse_QNAME, ApagarConteudoResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarConteudosType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "listarConteudos")
    public JAXBElement<ListarConteudosType> createListarConteudos(ListarConteudosType value) {
        return new JAXBElement<ListarConteudosType>(_ListarConteudos_QNAME, ListarConteudosType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CriarConteudoResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "criarConteudoResponse")
    public JAXBElement<CriarConteudoResponseType> createCriarConteudoResponse(CriarConteudoResponseType value) {
        return new JAXBElement<CriarConteudoResponseType>(_CriarConteudoResponse_QNAME, CriarConteudoResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarConteudosResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "listarConteudosResponse")
    public JAXBElement<ListarConteudosResponseType> createListarConteudosResponse(ListarConteudosResponseType value) {
        return new JAXBElement<ListarConteudosResponseType>(_ListarConteudosResponse_QNAME, ListarConteudosResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartilharConteudoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "partilharConteudo")
    public JAXBElement<PartilharConteudoType> createPartilharConteudo(PartilharConteudoType value) {
        return new JAXBElement<PartilharConteudoType>(_PartilharConteudo_QNAME, PartilharConteudoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterConteudoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "obterConteudo")
    public JAXBElement<ObterConteudoType> createObterConteudo(ObterConteudoType value) {
        return new JAXBElement<ObterConteudoType>(_ObterConteudo_QNAME, ObterConteudoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartilharConteudoResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "partilharConteudoResponse")
    public JAXBElement<PartilharConteudoResponseType> createPartilharConteudoResponse(PartilharConteudoResponseType value) {
        return new JAXBElement<PartilharConteudoResponseType>(_PartilharConteudoResponse_QNAME, PartilharConteudoResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConteudoInexistenteNaCaixaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.largacaixa.pt/", name = "ConteudoInexistenteNaCaixa")
    public JAXBElement<ConteudoInexistenteNaCaixaType> createConteudoInexistenteNaCaixa(ConteudoInexistenteNaCaixaType value) {
        return new JAXBElement<ConteudoInexistenteNaCaixaType>(_ConteudoInexistenteNaCaixa_QNAME, ConteudoInexistenteNaCaixaType.class, null, value);
    }

}
