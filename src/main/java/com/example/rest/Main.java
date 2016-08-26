
package com.example.rest;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 *
 * @author ryahiaoui
 */
public class Main {
    
     public static void main(String[] args) throws Exception {
 
        Container container = new Container() ;
 
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class, "my-app.war") ;
        deployment.addClass(RestApplication.class) ;
        deployment.addClass(HelloWorldEndpoint.class) ;
        deployment.addAllDependencies() ;
        container.start().deploy(deployment) ;
    }
}
