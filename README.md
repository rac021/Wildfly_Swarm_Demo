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
         
         -> Hello from WildFly Swarm Demo !
       
        ab -n 100 -c 2 "http://localhost:8080/rest/hello/endpoint?timeOut=2000&url=http://www.google.fr"
        
        http://172.17.0.3:8080/rest/hello/endpoint?timeOut=2000&url=http://172.17.0.2:8081/sleep/10
```     

 - ***Note :*** The default HTTP Port is **8080**

--------------------------------------------------------------

 - **Create Docker image**

<ol>  <B><i> 1 - From Dockerfile : </i></B></ol>

```
❯       $  docker build -t pinger-service .

        $  docker run -it -d -p 8080 --name myPingerService pinger-service
   
        $  docker inspect --format '{{ .NetworkSettings.Ports }}'  pinger-service

        $  curl $(hostname --all-ip-addresses | awk '{print $1}'):LOCAL_PORT/rest/hello/endpoint
         
```

<ol>  <B><i> 2 - From Docker Hub ( https://hub.docker.com/r/rac021/pinger-service) :</i></B></ol> 

```
❯       $  docker run -it -d -p 8080 --name myPingerService rac021/pinger-service

        $  docker inspect --format '{{ .NetworkSettings.Ports }}'  myPingerService
 
        $  curl $(hostname --all-ip-addresses | awk '{print $1}'):LOCAL_PORT//rest/hello/endpoint
   
```

--------------------------------------------------------------


 - **Links** 

   * [WildFly Swarm User’s Guide]( https://wildfly-swarm.gitbooks.io/wildfly-swarm-users-guide/content/index.html)

   * [WildFly Swarm Project Generator]( http://wildfly-swarm.io/generator)


