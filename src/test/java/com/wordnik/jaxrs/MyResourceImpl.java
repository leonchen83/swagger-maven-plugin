/**
 * Copyright 2014 Reverb Technologies, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wordnik.jaxrs;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import com.wordnik.sample.JavaRestResourceUtil;
import com.wordnik.sample.data.PetData;
import com.wordnik.sample.model.ListItem;
import com.wordnik.sample.model.Pet;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/myResourceImpl")
public class MyResourceImpl extends MyResourceAbstract<String> {
    static PetData petData = new PetData();
    static JavaRestResourceUtil ru = new JavaRestResourceUtil();

    //contrived example test case for swagger-maven-plugin issue #358
    /* (non-Javadoc)
     * @see com.wordnik.jaxrs.MyResource#getPetsById(java.lang.Long, java.lang.Long)
	 */
    @Override
    public Response getPetsById(Long startId, Long endId)
            throws com.wordnik.sample.exception.NotFoundException {
        Pet pet = petData.getPetbyId(startId);
        if (pet != null) {
            return Response.ok().entity(pet).build();
        } else {
            throw new com.wordnik.sample.exception.NotFoundException(404, "Pet not found");
        }
    }

    //contrived example test case for swagger-maven-plugin issue #505
    /* (non-Javadoc)
	 * @see com.wordnik.jaxrs.MyResource#getListOfItems()
	 */
    @Path("list")
    @Override
    public List<ListItem> getListOfItems() {
        return new ArrayList();
    }

    //contrived example test case for swagger-maven-plugin issue #504
    /* (non-Javadoc)
     * @see com.wordnik.jaxrs.MyResource#testParamInheritance(java.lang.String, java.lang.String, java.lang.String)
     */
    @Path("{firstParamConcrete}/properties")
    @Override
    public Response testParamInheritance(
            @PathParam("firstParamConcrete") String firstParam,
            String secondParam,
            String thirdParam) {
        return Response.ok().build();
    }

    @POST
    @ApiOperation(value = "Insert a response", notes = "This is a contrived example")
    @Override
    public Response insertResource(@ApiParam(value = "Resource to insert", required = true) String resource) {
        return Response.ok().build();
    }
}
