# Wildfly_Swarm_Demo


--------------------------------------------------------------


 - **Package**
 
``` 
     ❯  mvn package

```    
 - **Deployment**
 
<ol>  <B> 1. Using Java command : </B></ol>

``` 
     ❯   java -cp com.example.rest.Main -jar target/orleans-swarm.jar 

         java -jar target/orleans-swarm.jar 
```  
<ol>  <B> 2. Using Maven command : </B></ol>

``` 
     ❯  mvn -Dswarm.http.port=9090 wildfly-swarm:run

```    

 - **Test**
 
``` 
     ❯  curl http://localhost:9090/rest/hello
         
        $ Hello from WildFly Swarm Demo !
         
```     

 - ***Note :*** The default HTTP Port is **8080**


--------------------------------------------------------------


### *Link* : [WildFly Swarm Project Generator]( http://wildfly-swarm.io/generator)


