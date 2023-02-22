package com.wordnik.jaxrs;

import java.util.List;

import io.swagger.annotations.ApiParam;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

/**
 * @author chekong on 15/5/9.
 */
public class MyBean extends MyParentBean {

    @ApiParam(value = "testIntegerAllowableValues", defaultValue = "25", allowableValues = "25, 50, 100")
    @QueryParam("testIntegerAllowableValues")
    public Integer testIntegerAllowableValues;
    @ApiParam(value = "ID of pet that needs to be updated", required = true)
    @PathParam("petId")
    private String petId;
    @ApiParam(value = "Updated name of the pet", required = false, defaultValue = "defaultValue")
    @FormParam("name")
    private String name;
    @ApiParam(value = "Updated status of the pet", required = false, allowableValues = "value1, value2")
    @FormParam("status")
    private String status;
    @HeaderParam("myHeader")
    private String myHeader;
    @HeaderParam("intValue")
    private int intValue;
    @ApiParam(value = "hidden", hidden = true)
    @QueryParam(value = "hiddenValue")
    private String hiddenValue;
    @QueryParam(value = "listValue")
    private List<String> listValue;
    @BeanParam
    private MyNestedBean nestedBean;
    /**
     * This field is to test that bean params using constructor injection behave
     * correctly. It's also nested just to avoid adding too much test code.
     */
    @BeanParam
    private MyConstructorInjectedNestedBean constructorInjectedNestedBean;
    /**
     * This field's allowableValues, required, pattern, and defaultValue should
     * be derived based on its JAX-RS and validation annotations.
     */
    @QueryParam("constrainedField")
    @Min(25L)
    @Max(75L)
    @NotNull
    @Pattern(regexp = "[0-9]5")
    @DefaultValue("55")
    private int constrainedField;

    public String getMyheader() {
        return myHeader;
    }

    public void setmyHeader(String myHeader) {
        this.myHeader = myHeader;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public List<String> getListValue() {
        return listValue;
    }

    public void setListValue(List<String> listValue) {
        this.listValue = listValue;
    }

    public MyNestedBean getNestedBean() {
        return nestedBean;
    }

    public void setNestedBean(MyNestedBean nestedBean) {
        this.nestedBean = nestedBean;
    }

    public MyConstructorInjectedNestedBean getConstructorInjectedNestedBean() {
        return constructorInjectedNestedBean;
    }

    public void setConstructorInjectedNestedBean(MyConstructorInjectedNestedBean constructorInjectedNestedBean) {
        this.constructorInjectedNestedBean = constructorInjectedNestedBean;
    }

    public int getConstrainedField() {
        return constrainedField;
    }

    public void setConstrainedField(int constrainedField) {
        this.constrainedField = constrainedField;
    }
}
