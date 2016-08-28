
package com.example.rest;

import java.io.File;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 *
 * @author ryahiaoui
 */
public class Main {
    
     public static void main(String[] args) throws Exception {
 
        Swarm swarm = new Swarm() ;
 
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class, "my-app.war") ;
        deployment.addClass(RestApplication.class) ;
        deployment.addClass(HelloWorldEndpoint.class) ;
        deployment.addClass(MyHystrixMetricsStreamServlet.class) ;
        deployment.addAllDependencies() ;
        
        File file = new File("config.properties");
        
         if(file.exists()) {
            deployment.addAsResource(file);
         }
         
         swarm.start().deploy(deployment);
    }
}
