package com.wordnik.jaxrs;

import java.util.List;

import com.wordnik.sample.model.ListItem;
import com.wordnik.sample.model.Pet;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Api(description = "Operations about pets")
@Produces({"application/json", "application/xml"})
public interface MyResource<T> {

    //contrived example test case for swagger-maven-plugin issue #358
    @GET
    @ApiOperation(value = "Find pet(s) by ID",
            notes = "This is a contrived example",
            response = Pet.class
    )
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Pet not found")})
    public abstract Response getPetsById(
            @ApiParam(value = "start ID of pets that need to be fetched", allowableValues = "range[1,99]", required = true)
            @QueryParam("startId") Long startId,
            @ApiParam(value = "end ID of pets that need to be fetched", allowableValues = "range[1,99]", required = true)
            @QueryParam("endId") Long endId)
            throws com.wordnik.sample.exception.NotFoundException;

    //contrived example test case for swagger-maven-plugin issue #505
    @GET
    @ApiOperation(value = "Get a list of items",
            notes = "This is a contrived example"
    )
    public abstract List<ListItem> getListOfItems();

    //contrived example test case for swagger-maven-plugin issue #504
    @GET
    @ApiOperation(value = "Get a response", notes = "This is a contrived example")
    Response testParamInheritance(
            @PathParam("firstParamInterface") String firstParam,
            @PathParam("secondParamInterface") String secondParam,
            @QueryParam("thirdParamInterface") String thirdParam);

    Response insertResource(T resource);
}
