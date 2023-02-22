package com.wordnik.jaxrs;

import java.util.Set;

import org.apache.maven.plugin.logging.Log;

import io.swagger.models.Swagger;

/**
 * @author Igor Gursky
 * 11.12.2015.
 */
public class CustomJaxrsReader extends VendorExtensionsJaxrsReader {

    public CustomJaxrsReader(Swagger swagger, Log LOG) {
        super(swagger, LOG);
    }

    @Override
    public Swagger read(Set<Class<?>> classes) {
        Swagger swagger = super.read(classes);
        swagger.getInfo().setDescription("Processed with CustomJaxrsReader");
        return swagger;
    }
}
