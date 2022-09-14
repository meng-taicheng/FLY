package com.example.fly.model;

import android.view.View;

public class login {
    private long code;
    private data data;

    public login(View.OnClickListener onClickListener) {
    }
//    private Object data;

//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public com.example.fly.model.data getData() {
        return data;
    }

    public void setData(com.example.fly.model.data data) {
        this.data = data;
    }

    public login(long code, com.example.fly.model.data data) {
        this.code = code;
        this.data = data;
    }

}
