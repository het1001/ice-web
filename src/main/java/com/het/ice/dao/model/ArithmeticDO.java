package com.het.ice.dao.model;

/**
 * Created by Administrator on 2017/9/16.
 */
public class ArithmeticDO extends BaseModel {

    private static final long serialVersionUID = 5253306001483733416L;

    private String name;

    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
