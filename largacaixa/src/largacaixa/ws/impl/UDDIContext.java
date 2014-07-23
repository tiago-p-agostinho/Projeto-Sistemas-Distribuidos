package largacaixa.ws.impl;

import java.net.PasswordAuthentication;
import java.util.*;
import largacaixa.cli.HelloCli;
import javax.naming.*;
import javax.servlet.*;
import javax.xml.registry.*;
import javax.xml.registry.infomodel.*;
import largacaixa.ws.impl.*;
import java.lang.Thread;


public class UDDIContext {

	


    
    public void contextInitialized(String URL) {
	
        try {
            InitialContext context = new InitialContext();
            ConnectionFactory connFactory = (ConnectionFactory) context.lookup("java:jboss/jaxr/ConnectionFactory");

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
            // publication URL of UDDI registry
            props.setProperty("javax.xml.registry.lifeCycleManagerURL", "http://localhost:8081/juddiv3/services/publish");
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

            String organizationName = "LargaCaixa";
            String serviceName = "largaCaixa";
            String bindingDescription = "Information for accessing the largaCaixa service";
            String bindingURL = URL;  

			
			
            // We want to create a new organization if it does not exist
            // First, query for "My Organization"
            Organization org = null;
            Service serv = null;
			ServiceBinding servB = null;
            Collection<String> namePatterns = new ArrayList<String>();
            namePatterns.add("%" + organizationName + "%");
            // Perform search
            BulkResponse r = businessQueryManager.findOrganizations(findQualifiers, namePatterns, null, null, null, null);
            @SuppressWarnings("unchecked")
            Collection<Organization> orgs = r.getCollection();

            for (Organization o : orgs) {
                if (o.getName().getValue().equals(organizationName)) {
				    for (Object s: o.getServices())
					serv = (Service) s;
                    serv.setDescription(businessLifeCycleManager.createInternationalString(serviceName));
					for (Object sb: serv.getServiceBindings())
					servB =(ServiceBinding) sb;
					servB.setDescription(businessLifeCycleManager.createInternationalString(bindingDescription));
                    servB.setValidateURI(false);
                // Define the Web Service endpoint address here
                    servB.setAccessURI(bindingURL);
				    org = o;
					
                }
            }


            ////////////////////////////////////////////////////////
            // Register new entities/change existing entities
            // at UDDI registry
            ////////////////////////////////////////////////////////

            if (org==null) {
                // No organization found, create it
                org = businessLifeCycleManager.createOrganization(organizationName);
                // Create service
                Service service = businessLifeCycleManager.createService(serviceName);
                service.setDescription(businessLifeCycleManager.createInternationalString(serviceName));
                // Add service to organization
                org.addService(service);

                // Create serviceBinding
                ServiceBinding serviceBinding = businessLifeCycleManager.createServiceBinding();
                serviceBinding.setDescription(businessLifeCycleManager.
                createInternationalString(bindingDescription));
                serviceBinding.setValidateURI(false);
                // Define the Web Service endpoint address here
                serviceBinding.setAccessURI(bindingURL);
                // Add serviceBinding to service
                service.addServiceBinding(serviceBinding);
            }

            Collection<Organization> orgs2 = new ArrayList<Organization>();
            orgs2.add(org);

            // At last, register new organization/service/serviceBinding
            // (or the changes) on UDDI registry
            BulkResponse br = businessLifeCycleManager.saveOrganizations(orgs2);

            if (br.getStatus() == JAXRResponse.STATUS_SUCCESS)
                System.out.println("Registration completed successfully.");
            else
                System.out.println("Error during UDDI registration.");
			
				
        } catch (NamingException e) {
            System.err.println("UDDI Error obtaining ConnectionFactory.");
            e.printStackTrace();
        } catch (JAXRException e) {
            System.err.println("UDDI Error contacting Registry.");
            e.printStackTrace();
        }

 }
}