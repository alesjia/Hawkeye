package org.alesjia.bean;

import java.io.Serializable;

/**
 * Created by lxl on 2016/1/8.
 */
public class Response implements Serializable {
    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "status = " + status + ", message = " + message;
    }
}
