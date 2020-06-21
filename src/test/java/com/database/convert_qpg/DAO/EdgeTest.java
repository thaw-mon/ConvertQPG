package com.database.convert_qpg.DAO;

import com.database.convert_qpg.Controller.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    @Test
    void createEdges() {
        Input input = new Input();
        String path = "src/main/resources/";
        String fileName1 = "entities.txt";
        String fileName2 = "sql.txt";
        input.init(path+fileName1,path+fileName2);

        List<Edge> edgeList  = Edge.createEdges(input);
        assert edgeList.size() == 7;
        for(Edge edge : edgeList){
            System.out.println(edge);
        }
    }

    @Test
    void addQueryNumber() {
    }

    @Test
    void writeFile(){
        Input input = new Input();
        String path = "src/main/resources/";
        String fileName1 = "entities.txt";
        String fileName2 = "sql.txt";
        input.init(path+fileName1,path+fileName2);

        List<Edge> edgeList  = Edge.createEdges(input);

        String fileName = "edge.txt";
        Edge.writeFile(path + fileName,edgeList);
    }
}