package com.database.convert_qpg.DAO;

import com.database.convert_qpg.Controller.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void createNodes() {
        Input input = new Input();
        String path = "src/main/resources/";
        String fileName1 = "entities.txt";
        String fileName2 = "sql.txt";
        input.init(path+fileName1,path+fileName2);
        List<Node> nodeList = Node.createNodes(input);
        assert nodeList.size() == 4;
        for(Node node : nodeList){
            System.out.println(node);
        }
    }

    @Test
    void writeFile(){
        Input input = new Input();
        String path = "src/main/resources/";
        String fileName1 = "entities.txt";
        String fileName2 = "sql.txt";
        input.init(path+fileName1,path+fileName2);

        List<Node> nodes  = Node.createNodes(input);

        String fileName = "node.txt";
        Node.writeFile(path + fileName,nodes);
    }
}