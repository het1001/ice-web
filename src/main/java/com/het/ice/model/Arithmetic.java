package com.het.ice.model;

public class Arithmetic extends BaseModel {
    private static final long serialVersionUID = 9014652498742100561L;

    private String name;

    private String function;

    private String funcKeys;

    private String inParams;

    private String outParam;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFuncKeys() {
        return funcKeys;
    }

    public void setFuncKeys(String funcKeys) {
        this.funcKeys = funcKeys;
    }

    public String getInParams() {
        return inParams;
    }

    public void setInParams(String inParams) {
        this.inParams = inParams;
    }

    public String getOutParam() {
        return outParam;
    }

    public void setOutParam(String outParam) {
        this.outParam = outParam;
    }
}
