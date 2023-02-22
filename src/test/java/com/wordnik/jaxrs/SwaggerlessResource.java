package com.wordnik.jaxrs;

import org.springframework.web.bind.annotation.RequestParam;

import com.wordnik.sample.model.Pet;
import com.wordnik.sample.model.PetName;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/")
public class SwaggerlessResource {

    @GET
    @Path("/swaggerless/{petId : [0-9]}")
    public Pet getPetByName(@PathParam(value = "name") String name) {
        // Just create and return a new pet
        Pet pet = new Pet();
        pet.setName(new PetName(name));
        return pet;
    }

    public Pet notAnEndpoint(@PathParam(value = "name") String name) {
        // Just create and return a new pet
        Pet pet = new Pet();
        pet.setName(new PetName(name));
        return pet;
    }

    @Path("/swaggerless")
    public Pet notAnEndpointWithPath(@RequestParam(value = "name") String name) {
        // Just create and return a new pet
        Pet pet = new Pet();
        pet.setName(new PetName(name));
        return pet;
    }

    @Path("/swaggerless/subresource")
    public SwaggerlessSubresource subresourceEndpoint() {
        return new SwaggerlessSubresource();
    }
}
