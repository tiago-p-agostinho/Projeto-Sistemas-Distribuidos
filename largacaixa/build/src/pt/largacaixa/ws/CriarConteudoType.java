
package pt.largacaixa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for criarConteudoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="criarConteudoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="caixa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preco" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dados" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "criarConteudoType", propOrder = {
    "cid",
    "caixa",
    "preco",
    "dados"
})
public class CriarConteudoType {

    @XmlElement(required = true)
    protected String cid;
    @XmlElement(required = true)
    protected String caixa;
    protected int preco;
    @XmlElement(required = true)
    protected byte[] dados;

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
     * Gets the value of the caixa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaixa() {
        return caixa;
    }

    /**
     * Sets the value of the caixa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaixa(String value) {
        this.caixa = value;
    }

    /**
     * Gets the value of the preco property.
     * 
     */
    public int getPreco() {
        return preco;
    }

    /**
     * Sets the value of the preco property.
     * 
     */
    public void setPreco(int value) {
        this.preco = value;
    }

    /**
     * Gets the value of the dados property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDados() {
        return dados;
    }

    /**
     * Sets the value of the dados property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDados(byte[] value) {
        this.dados = value;
    }

}
