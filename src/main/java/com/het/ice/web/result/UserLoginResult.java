package com.het.ice.web.result;

/**
 * Created by Administrator on 2017/5/29.
 */
public class UserLoginResult {

    private String state;

    private String token;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
