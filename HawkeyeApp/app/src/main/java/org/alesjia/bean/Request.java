package org.alesjia.bean;

import org.alesjia.hawkeye.HawkeyeConstant;

/**
 * Created by lxl on 2016/1/8.
 */
public class Request {
    private String ak;
    private Integer service_id;

    public Request() {
        ak = HawkeyeConstant.AK;
        service_id = HawkeyeConstant.SERVICE_ID;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }
}
