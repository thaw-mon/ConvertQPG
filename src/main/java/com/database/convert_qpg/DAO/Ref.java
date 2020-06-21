package com.database.convert_qpg.DAO;

import java.io.Serializable;

public class Ref implements Serializable {
    String name;
    char cardinality;
    String relationShip;

    public Ref(String name, char cardinality, String relationShip) {
        this.name = name;
        this.cardinality = cardinality;
        this.relationShip = relationShip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getCardinality() {
        return cardinality;
    }

    public void setCardinality(char cardinality) {
        this.cardinality = cardinality;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    @Override
    public String toString() {
        return "ref " + name + "[" + cardinality + "]" +
                " " + relationShip;
    }

}
