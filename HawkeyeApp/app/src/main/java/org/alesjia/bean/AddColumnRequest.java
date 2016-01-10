package org.alesjia.bean;

/**
 * Created by lxl on 2016/1/9.
 */
public class AddColumnRequest extends Request {
    private String column_key;
    private String column_desc;
    private Integer column_type;

    public String getColumn_key() {
        return column_key;
    }

    public void setColumn_key(String column_key) {
        this.column_key = column_key;
    }

    public String getColumn_desc() {
        return column_desc;
    }

    public void setColumn_desc(String column_desc) {
        this.column_desc = column_desc;
    }

    public Integer getColumn_type() {
        return column_type;
    }

    public void setColumn_type(Integer column_type) {
        this.column_type = column_type;
    }
}
