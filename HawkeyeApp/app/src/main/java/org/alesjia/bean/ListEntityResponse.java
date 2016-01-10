package org.alesjia.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxl on 2016/1/8.
 */
public class ListEntityResponse extends Response {
    private int total;
    private int size;
    private List<Entities> entities;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Entities> getEntities() {
        return entities;
    }

    public void setEntities(List<Entities> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return super.toString() + ", total = " + total + ", size = " + size + ", entities = " + entities;
    }

    public static class Entities implements Serializable {

        private String entity_name;
        private String create_time;
        private String modify_time;
        private RealTimePoint realtime_point;

        public RealTimePoint getRealtime_point() {
            return realtime_point;
        }

        public void setRealtime_point(RealTimePoint realtime_point) {
            this.realtime_point = realtime_point;
        }

        public String getEntity_name() {
            return entity_name;
        }

        public void setEntity_name(String entity_name) {
            this.entity_name = entity_name;
        }

        public String getModify_time() {
            return modify_time;
        }

        public void setModify_time(String modify_time) {
            this.modify_time = modify_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        @Override
        public String toString() {
            return "realtime_point = " + realtime_point + ", entity_name = " + entity_name
                    + ", modify_time = " + modify_time + ", create_time = " + create_time;
        }
    }

    public static class RealTimePoint implements Serializable{
        private long loc_time;
        private List<Double> location;
        private Integer direction;
        private double speed;
        private double radius;
        private String power;

        public List<Double> getLocation() {
            return location;
        }

        public void setLocation(List<Double> location) {
            this.location = location;
        }

        public long getLoc_time() {
            return loc_time;
        }

        public void setLoc_time(long loc_time) {
            this.loc_time = loc_time;
        }

        public Integer getDirection() {
            return direction;
        }

        public void setDirection(Integer direction) {
            this.direction = direction;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        @Override
        public String toString() {
            return "location = " + location + ", loc_time = " + loc_time
                    + ", direction = " + direction + ", speed = " + speed
                    + ", radius = " + radius + ", power = " + power;
        }
    }

}
