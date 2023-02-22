package com.github.kongchen.swagger.docgen;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.maven.plugin.logging.SystemStreamLog;
import org.testng.annotations.Test;

import com.github.kongchen.swagger.docgen.jaxrs.BeanParamInjectParamExtension;
import com.github.kongchen.swagger.docgen.jaxrs.JaxrsParameterExtension;
import com.github.kongchen.swagger.docgen.reader.JaxrsReader;
import com.github.kongchen.swagger.docgen.spring.SpringSwaggerExtension;
import com.google.common.collect.Lists;

import io.swagger.jaxrs.ext.AbstractSwaggerExtension;
import io.swagger.jaxrs.ext.SwaggerExtension;
import io.swagger.models.parameters.Parameter;

/**
 * Test class which ensures common functionality across all of the currently included Swagger Extensions, namely </p>
 * <ul>
 *     <li>@{@link com.github.kongchen.swagger.docgen.spring.SpringSwaggerExtension}</li>
 *     <li>{@link com.github.kongchen.swagger.docgen.jaxrs.JaxrsParameterExtension}</li>
 *     <li>{@link BeanParamInjectParamExtension}</li>
 * </ul>
 */
public class IncludedSwaggerExtensionTest {
    private static final List<AbstractSwaggerExtension> SWAGGER_EXTENSIONS = Lists.newArrayList();

    static {
        //TODO: Maybe use a Classpath Scanner to automatically figure out the included extensions?
        SWAGGER_EXTENSIONS.add(new JaxrsParameterExtension());
        SWAGGER_EXTENSIONS.add(new SpringSwaggerExtension(new SystemStreamLog()));
        SWAGGER_EXTENSIONS.add(new BeanParamInjectParamExtension(mock(JaxrsReader.class)));
    }

    @Test
    /**
     * This tests acts more like an integration test than a real unit test. It tests whether the extensions return
     * generally correct values.
     */
    public void testExtractParametersReturnsEmptyList() {
        for (AbstractSwaggerExtension swaggerExtension : SWAGGER_EXTENSIONS) {
            Set<Type> typesToSkip = Collections.emptySet();
            List<Annotation> annotations = Lists.newArrayList(AnnotationBearer.class.getAnnotation(Deprecated.class));
            AbstractSwaggerExtension extension = mock(AbstractSwaggerExtension.class, CALLS_REAL_METHODS);
            doReturn(new ArrayList<Parameter>()).when(extension).extractParameters(any(), any(), any(), any());

            Iterator<SwaggerExtension> iterator = Lists.<SwaggerExtension>newArrayList(extension).iterator();

            // Not possible to add any parameters for the extensions, since no annotation / field is given to the extensions
            // only the previously created mock AbstractSwaggerExtension is in the chain
            // This allows to test if first the chain is called, and only then empty, modifiable lists are returned as last resort
            List<Parameter> parameters = swaggerExtension.extractParameters(
                    annotations,
                    Void.class,
                    typesToSkip,
                    iterator);
            // Has to return a collection we can later modify.
            try {
                parameters.add(null);
            } catch (Exception e) {
                throw new IllegalStateException("Extension " + swaggerExtension.getClass().getName() + " did not return a modifiable list.", e);
            }

            // Test if the next extension in the chain was called
            try {
                List<Annotation> list = anyList();
                Set<Type> set = anySet();
                verify(extension).extractParameters(
                        list,
                        any(Type.class),
                        set,
                        eq(iterator)
                );
            } catch (Throwable t) {
                // Catch everything here.
                // We need to output the currently tested extension here
                // so that the reason why the test failed can be easier recognized later on
                // Still need to rethrow the exception though, to make the test fail

                // TODO: Is there any better wrapper exception type?
                throw new IllegalStateException("Extension " + swaggerExtension.getClass().getName() + " failed this Test.", t);
            }
        }
    }

    // Class specificly for holding default value annotations
    @Deprecated
    private static class AnnotationBearer {
    }
}
