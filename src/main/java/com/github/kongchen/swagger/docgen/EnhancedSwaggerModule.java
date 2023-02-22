package com.github.kongchen.swagger.docgen;

import java.io.Serial;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class EnhancedSwaggerModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = 1L;

    public EnhancedSwaggerModule() {
        super("1.0.0");
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.insertAnnotationIntrospector(new EnhancedSwaggerAnnotationIntrospector());
    }
}
