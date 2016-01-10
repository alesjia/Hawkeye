package org.alesjia.bean;

/**
 * Created by lxl on 2016/1/9.
 */
public class AddEntityRequest extends Request {
    private String entity_name;

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }
}
