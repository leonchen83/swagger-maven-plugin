package com.wordnik.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import com.wordnik.sample.model.Pet;
import com.wordnik.sample.model.PetName;

public class SwaggerlessSubresource {

    @GET
    @Path("/{name}")
    public Pet getPetByNameSubresource(@PathParam(value = "name") String name) {
        // Just create and return a new pet
        Pet pet = new Pet();
        pet.setName(new PetName(name));
        return pet;
    }
}
