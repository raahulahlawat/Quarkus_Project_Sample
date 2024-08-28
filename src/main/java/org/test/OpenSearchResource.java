// package org.test;

// import jakarta.inject.Inject;
// import jakarta.ws.rs.GET;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.Produces;
// import jakarta.ws.rs.core.MediaType;

// @Path("/opensearch")
// public class OpenSearchResource {

//     @Inject
//     OpenSearchService openSearchService;

//     @GET
//     @Path("/health")
//     @Produces(MediaType.TEXT_PLAIN)
//     public String getHealth() {
//         return openSearchService.checkHealth();
//     }
// }

package org.test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/opensearch")
public class OpenSearchResource {

    private final OpenSearchService openSearchService;

    // Constructor injection
    public OpenSearchResource(OpenSearchService openSearchService) {
        this.openSearchService = openSearchService;
    }

    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHealth() {
        return openSearchService.checkHealth();
    }
}
