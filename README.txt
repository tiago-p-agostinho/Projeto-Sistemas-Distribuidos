






WSDL - actualizado

lança dois web services da larga caixa. --> ant -p para ver as opções


ant db-build -cria bases de dados e cliente
ant builds deploys - compila e faz deploy de todos os serviços


create datasource






CONFIGURAR JNDI


name = largacaixa1
jndi = java:jboss/datasources/largacaixa1
>mysql
jdbc:mysql://localhost:3306/largacaixa1

largacaixauser
largacaixapass


name = largacaixa2
jndi = java:jboss/datasources/largacaixa2
>mysql
jdbc:mysql://localhost:3306/largacaixa2

largacaixauser
largacaixapass

 <datasource jta="false" jndi-name="java:jboss/datasources/largacaixa1" pool-name="largacaixa1" enabled="true" use-ccm="false">
                    <connection-url>jdbc:mysql://localhost:3306/largacaixa1</connection-url>
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <driver>mysql</driver>
                    <security>
                        <user-name>largacaixauser</user-name>
                        <password>largacaixapass</password>
                    </security>
                    <validation>
                        <validate-on-match>false</validate-on-match>
                        <background-validation>false</background-validation>
                    </validation>
                    <statement>
                        <share-prepared-statements>false</share-prepared-statements>
                    </statement>
                </datasource>
				
<datasource jta="false" jndi-name="java:jboss/datasources/largacaixa2" pool-name="largacaixa2" enabled="true" use-ccm="false">
                    <connection-url>jdbc:mysql://localhost:3306/largacaixa2</connection-url>
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <driver>mysql</driver>
                    <security>
                        <user-name>largacaixauser</user-name>
                        <password>largacaixapass</password>
                    </security>
                    <validation>
                        <validate-on-match>false</validate-on-match>
                        <background-validation>false</background-validation>
                    </validation>
                    <statement>
                        <share-prepared-statements>false</share-prepared-statements>
                    </statement>
                </datasource>
				
				
			