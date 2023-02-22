package com.wordnik.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author andrewb
 */
@Path("/")
@Api(value = "/")
public class RootPathResource {
    @GET
    @ApiOperation(value = "testingRootPathResource")
    public String testingRootPathResource() {
        return "testingRootPathResource";
    }
}
