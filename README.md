# Wildfly_Swarm_Demo


--------------------------------------------------------------


 - **Package**
 
``` 
     ❯  mvn package

```    
 - **Deployment**
 
<ol>  <B> 1. Using Java command : </B></ol>

``` 
     ❯   java -cp com.example.rest.Main -jar target/wildflyDemo-swarm.jar

         java -jar target/wildflyDemo-swarm.jar
         
         java -Darchaius.configurationSource.additionalUrls=file:config.properties \
         -jar target/wildflyDemo-swarm.jar # Overriding Hystrix properties
         
```  
<ol>  <B> 2. Using Maven command : </B></ol>

``` 
     ❯  mvn -Dswarm.http.port=9090 wildfly-swarm:run

```    

 - **Description**
 
   * The service takes two QueryParams :
      
      * **url**     : url of the service tha will be invoked .
      
      * **timeOut** : if the response delay exceeds the timeout an exception is raised.
 
 - **Test**
 
``` 
     ❯  curl http://localhost:8080/rest/hello/endpoint
         
        $  Hello from WildFly Swarm Demo !
    
        ab -n 100 -c 2 "http://localhost:8080/rest/hello/endpoint?timeOut=2000&url=http://www.google.fr"
```     

 - ***Note :*** The default HTTP Port is **8080**


--------------------------------------------------------------


 - **Links** 

   * [WildFly Swarm User’s Guide]( https://wildfly-swarm.gitbooks.io/wildfly-swarm-users-guide/content/index.html)

   * [WildFly Swarm Project Generator]( http://wildfly-swarm.io/generator)


