
package pt.largacaixa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for partilharConteudoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partilharConteudoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caixaOrigem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caixaDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comprovativoPagamento" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partilharConteudoType", propOrder = {
    "cid",
    "caixaOrigem",
    "caixaDestino",
    "comprovativoPagamento"
})
public class PartilharConteudoType {

    @XmlElement(required = true)
    protected String cid;
    @XmlElement(required = true)
    protected String caixaOrigem;
    @XmlElement(required = true)
    protected String caixaDestino;
    @XmlElement(required = true)
    protected Object comprovativoPagamento;

    /**
     * Gets the value of the cid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCid() {
        return cid;
    }

    /**
     * Sets the value of the cid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCid(String value) {
        this.cid = value;
    }

    /**
     * Gets the value of the caixaOrigem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaixaOrigem() {
        return caixaOrigem;
    }

    /**
     * Sets the value of the caixaOrigem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaixaOrigem(String value) {
        this.caixaOrigem = value;
    }

    /**
     * Gets the value of the caixaDestino property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaixaDestino() {
        return caixaDestino;
    }

    /**
     * Sets the value of the caixaDestino property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaixaDestino(String value) {
        this.caixaDestino = value;
    }

    /**
     * Gets the value of the comprovativoPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getComprovativoPagamento() {
        return comprovativoPagamento;
    }

    /**
     * Sets the value of the comprovativoPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setComprovativoPagamento(Object value) {
        this.comprovativoPagamento = value;
    }

}
