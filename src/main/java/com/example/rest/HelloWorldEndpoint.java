
package com.example.rest;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;



@Path("/hello")
public class HelloWorldEndpoint {

  @GET
  @Produces("text/plain")
  @Path("/endpoint")
  public Response doGet(  @QueryParam("url") String url, @QueryParam("timeOut") int timeOut ) {
  
      if( url == null ) {
        return Response.ok("Hello from WildFly Swarm Demo ! ").build() ;
      }
      
      String data = new CallEndPointCommand(url, timeOut).execute() ;
      return Response.ok(data ).build() ;
   }
  

  class CallEndPointCommand extends HystrixCommand<String> {
      
        // Default URL 
        String url = "http://localhost:8080/rest/hello";
        
        Integer timeOut ;
        
	public CallEndPointCommand( String url, Integer timeOut) {
	
            super(HystrixCommandGroupKey.Factory.asKey("endPoitnData"));
                        
            /* Programatic Configuration */
           
            /*
            ConfigurationManager.getConfigInstance()
                                .setProperty("hystrix.command.CallEndPointCommand.circuitBreaker.requestVolumeThreshold", 30) ;

            ConfigurationManager.getConfigInstance()
                                .setProperty("hystrix.command.CallEndPointCommand.execution.isolation.thread.timeoutInMilliseconds", 2000) ;
                        
            ConfigurationManager.getConfigInstance()
                                .setProperty("hystrix.threadpool.default.coreSize", 8) ;
 
            ConfigurationManager.getConfigInstance()
                                .setProperty("hystrix.command.default.metrics.rollingPercentile.numBuckets", 60) ;
        
            */
                       
            if(timeOut != null ) {
                System.out.println(" Overriding timeoutInMilliseconds... ");
                  ConfigurationManager.getConfigInstance()
                                     .setProperty("hystrix.command.CallEndPointCommand.execution.isolation.thread.timeoutInMilliseconds", timeOut ) ;
                  
            }
            
             Integer defTimeOut = ConfigurationManager.getConfigInstance()
                             .getInt("hystrix.command.CallEndPointCommand.execution.isolation.thread.timeoutInMilliseconds") ;
 
            System.out.println(" ++ timeOut for CallEndPointCommand = " + defTimeOut ) ;
           
            this.url = url         ;
            this.timeOut = timeOut ;
            
        }

        
	@Override
	protected String run() throws Exception {
		
           try {

               Builder request = ClientBuilder.newClient().target(url).request() ;
              
	       return request.get(new GenericType<String>(){});
                         
	   } catch ( Exception e) {
                throw new RuntimeException(" Oops, Something went wrong ! ") ;
	     }
	}
        
  
         @Override
        protected String getFallback() {
            return " Client   :  " + url +"   is unreachable !! \n"
                 + " Reason   :  TimeoutException \n " ;
        }
  }

}

