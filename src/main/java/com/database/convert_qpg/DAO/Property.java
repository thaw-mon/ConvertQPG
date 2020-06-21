package com.database.convert_qpg.DAO;

import java.io.Serializable;

public class Property implements Serializable {
    String name;
    String type;
    boolean isPrimaryKey;

    public Property(String name, String type, boolean isPrimaryKey) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(type + " " + name);
        if(isPrimaryKey){
            ret.append(" [K]");
        }
        return ret.toString();
    }

    public String toNodeString() {
        return name + "/" + type;
    }
}
