package largaCaixa.cli;

import java.util.*;
import javax.xml.registry.*;
import javax.xml.registry.infomodel.*;
import javax.naming.*;
import java.net.PasswordAuthentication;
public class Query{
    
	     public String search(){
		 try{
		  InitialContext context = new InitialContext();
          ConnectionFactory connFactory = ConnectionFactory.newInstance(); 
		 
  ////////////////////////////////////////////////////////
            // Connect to UDDI registry
            ////////////////////////////////////////////////////////

            // For a correct connection to the UDDI registry, several properties are required.
            Properties props = new Properties();
           // Location of connection configuration file,
            // should be available at WEB-INF/classes on the .war file
            props.setProperty("scout.juddi.client.config.file", "uddi.xml");
            // search URL of UDDI registry
            props.setProperty("javax.xml.registry.queryManagerURL", "http://localhost:8081/juddiv3/services/inquiry");
            // security manager URL of UDDI registry
            props.setProperty("javax.xml.registry.securityManagerURL", "http://localhost:8081/juddiv3/services/security");
            // version of UDDI registry
            props.setProperty("scout.proxy.uddiVersion", "3.0");
            // transport protocol used for communication with UDDI registry
            props.setProperty("scout.proxy.transportClass", "org.apache.juddi.v3.client.transport.JAXWSTransport");
            connFactory.setProperties(props);

            // Finally, establish connection to UDDI registry
            Connection connection = connFactory.createConnection();

            // Define authentication credentials for UDDI registry
            // Note: jUDDI is configured to accept anything as username/password;
            // this would not be the case in a real world deployment
            PasswordAuthentication passwdAuth = new PasswordAuthentication("username", "password".toCharArray());
            Set<PasswordAuthentication> creds = new HashSet<PasswordAuthentication>();
            creds.add(passwdAuth);
            connection.setCredentials(creds);

           // Get RegistryService object
            RegistryService rs = connection.getRegistryService();

            // Get QueryManager object (JAXR Business API)
            // (for queries)
            BusinessQueryManager businessQueryManager = rs.getBusinessQueryManager();
            // Get BusinessLifeCycleManager object (JAXR Business API)
            // (for registrations/changes of information at UDDI registry)
            BusinessLifeCycleManager businessLifeCycleManager = rs.getBusinessLifeCycleManager();


            ////////////////////////////////////////////////////////
            // Search for registered organization
            ////////////////////////////////////////////////////////
			
			Collection<String> findQualifiers = new ArrayList<String>();
            findQualifiers.add(FindQualifier.SORT_BY_NAME_DESC);
            String endpoint = "";
            String organizationName = "LargaCaixa";

            // We want to create a new organization if it does not exist
            // First, query for "My Organization"
            Organization org = null;

            Collection<String> namePatterns = new ArrayList<String>();
            namePatterns.add("%" + organizationName + "%");
            // Perform search
            BulkResponse r = businessQueryManager.findOrganizations(findQualifiers, namePatterns, null, null, null, null);
            
            Collection<Organization> orgs = r.getCollection();
            ServiceBinding sb = null;
			Iterator orgIter = orgs.iterator();
    
	
	        while (orgIter.hasNext()) {
                 Organization orgn = (Organization) orgIter.next();
                 Collection services = orgn.getServices();
                 Iterator svcIter = services.iterator();
                 while (svcIter.hasNext()) {
                       Service svc = (Service) svcIter.next();
                       Collection serviceBindings = svc.getServiceBindings();
                       Iterator sbIter = serviceBindings.iterator();
                       while (sbIter.hasNext()) 
                              sb = (ServiceBinding) sbIter.next();
                        endpoint = sb.getAccessURI();
					    return endpoint;
                 }
            } 
			
			
		} catch (NamingException e) {
            System.err.println("UDDI Error obtaining ConnectionFactory.");
            e.printStackTrace();
        } catch (JAXRException e) {
            System.err.println("UDDI Error contacting Registry.");
            e.printStackTrace();
		}
      return null;		
    }
}