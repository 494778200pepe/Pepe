package com.pepe.retrofit.Bean;

import java.util.List;

/**
 * Created by pepe on 2016/4/21.
 * E_mail: 494778200@qq.com
 * Company:小知科技 http://www.zizizizizi.com/
 */
public class CategoryBean {

    /**
     * error_code : 0
     * reason : Success!
     * result : ["少年漫画","少年漫画","少年漫画","少年漫画"]
     */

    private int error_code;
    private String reason;
    private List<String> result;

    public static CategoryBean objectFromData(String str) {

        return new com.google.gson.Gson().fromJson(str, CategoryBean.class);
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
