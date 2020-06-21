package com.database.convert_qpg.Controller;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {
    Input input;
    String path = "src/main/resources/";
    String fileName1 = "entities.txt";
    String fileName2 = "sql.txt";

    @Test
    public void initInput(){
        input = new Input();
        input.init(path+fileName1,path+fileName2);

        System.out.println(input);
    }

    @Test
    void getAccessPoints() {
        input = new Input();
        input.init(path+fileName1,path+fileName2);
        List<String> accessPoints = input.getAccessPoints();
        assert accessPoints.size() == 3;
        System.out.println(accessPoints);
    }

    @Test
    void getQueryNumber() {
        input = new Input();
        input.init(path+fileName1,path+fileName2);
        String nodeName = "Artifact";
        List<Integer> queryNumbers = input.getQueryNumber(nodeName);
        assert queryNumbers.size() == 5;
        System.out.println(queryNumbers);
    }

    @Test
    void getOutNeighbors() {
        input = new Input();
        input.init(path+fileName1,path+fileName2);
        String nodeName = "Artifact";
        List<String> outNeighbors = input.getOutNeighbors(nodeName);
        assert outNeighbors.size() == 3;
        System.out.println(outNeighbors);
    }
}