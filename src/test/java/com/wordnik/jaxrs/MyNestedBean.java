package com.wordnik.jaxrs;

import io.swagger.annotations.ApiParam;
import jakarta.ws.rs.HeaderParam;

/**
 * Represents a {@code @BeanParam} target that is nested within another bean.
 */
public class MyNestedBean {

    @ApiParam("Header from nested bean")
    @HeaderParam("myNestedBeanHeader")
    private String myNestedBeanHeader;

    public String getMyNestedBeanHeader() {
        return myNestedBeanHeader;
    }

    public void setMyNestedBeanHeader(String myNestedBeanHeader) {
        this.myNestedBeanHeader = myNestedBeanHeader;
    }
}
