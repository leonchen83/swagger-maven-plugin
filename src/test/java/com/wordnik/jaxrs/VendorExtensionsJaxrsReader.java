package com.wordnik.jaxrs;

import java.util.LinkedList;
import java.util.List;

import org.apache.maven.plugin.logging.Log;

import com.github.kongchen.swagger.docgen.reader.JaxrsReader;
import com.wordnik.sample.TestVendorExtension;

import io.swagger.jaxrs.ext.SwaggerExtension;
import io.swagger.jaxrs.ext.SwaggerExtensions;
import io.swagger.models.Swagger;

public class VendorExtensionsJaxrsReader extends JaxrsReader {

    public VendorExtensionsJaxrsReader(Swagger swagger, Log LOG) {
        super(swagger, LOG);

        List<SwaggerExtension> extensions = new LinkedList<SwaggerExtension>(SwaggerExtensions.getExtensions());
        extensions.add(new TestVendorExtension());
        SwaggerExtensions.setExtensions(extensions);
    }
}
