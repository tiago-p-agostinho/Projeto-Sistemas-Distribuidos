
package pt.largacaixa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listarConteudosType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listarConteudosType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="caixa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarConteudosType", propOrder = {
    "caixa"
})
public class ListarConteudosType {

    @XmlElement(required = true)
    protected String caixa;

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

}
