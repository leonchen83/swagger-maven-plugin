package com.wordnik.springmvc;

import java.util.LinkedList;
import java.util.List;

import org.apache.maven.plugin.logging.Log;

import com.github.kongchen.swagger.docgen.reader.SpringMvcApiReader;
import com.wordnik.sample.TestVendorExtension;

import io.swagger.jaxrs.ext.SwaggerExtension;
import io.swagger.jaxrs.ext.SwaggerExtensions;
import io.swagger.models.Swagger;

public class VendorExtensionsSpringMvcReader extends SpringMvcApiReader {

    public VendorExtensionsSpringMvcReader(Swagger swagger, Log log) {
        super(swagger, log);

        List<SwaggerExtension> extensions = new LinkedList<SwaggerExtension>(SwaggerExtensions.getExtensions());
        extensions.add(new TestVendorExtension());
        SwaggerExtensions.setExtensions(extensions);
    }
}
