package com.wordnik.jaxrs;

import java.util.List;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import com.wordnik.sample.exception.NotFoundException;
import com.wordnik.sample.model.ListItem;

import io.swagger.annotations.ApiParam;

/**
 * @author daniele.orler
 */
public abstract class MyResourceAbstract<T> implements MyResource<T> {
    @Override
    public abstract Response getPetsById(Long startId, Long endId) throws NotFoundException;

    @Override
    public abstract List<ListItem> getListOfItems();

    @Override
    public abstract Response testParamInheritance(
            @PathParam("firstParamAbstract") String firstParam,
            @ApiParam(required = true) @QueryParam("secondParamAbstract") String secondParam,
            String thirdParam);
}
