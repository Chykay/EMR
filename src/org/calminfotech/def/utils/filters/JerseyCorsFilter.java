package org.calminfotech.def.utils.filters;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * Servlet Filter implementation class JerseyCorsFilter
 */

public class JerseyCorsFilter implements  ContainerResponseFilter {
	/**
     * @see ContainerResponseFilter#filter(ContainerRequest, ContainerResponse)
     */
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        // TODO Auto-generated method stub
    		/*response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
    		response.getHttpHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    		response.getHttpHeaders().add("Access-Control-Max-Age", "3600");*/
			return response;
    }
}
