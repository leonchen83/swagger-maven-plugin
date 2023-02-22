package com.github.kongchen.swagger.docgen.reader;

import java.util.Set;

import com.github.kongchen.swagger.docgen.GenerateException;

import io.swagger.models.Swagger;

/**
 * @author chekong on 15/4/28.
 */
public interface ClassSwaggerReader {
    Swagger read(Set<Class<?>> classes) throws GenerateException;
}
