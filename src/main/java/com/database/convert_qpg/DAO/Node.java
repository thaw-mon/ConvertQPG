package com.database.convert_qpg.DAO;

import com.database.convert_qpg.Controller.Input;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * QPG node
 */
public class Node {
    String nodeName; //可以作为唯一标识符
    List<Property> scalarProperties;
    boolean isAccessPoint;

    List<Integer> queryNumbers;
    List<String> outNeighbors; //出度节点

    public Node(String name) {
        nodeName = name;
        scalarProperties = new ArrayList<>();
        queryNumbers = new ArrayList<>();
        isAccessPoint = false;
        outNeighbors = new ArrayList<>();
    }

    //根据input创建node
    public static List<Node> createNodes(Input input) {
        List<Node> nodes = new ArrayList<>();
        List<ModelEntity> modelEntities = input.getModelEntities();
        List<String> accessPoints = input.getAccessPoints();
        for (ModelEntity modelEntity : modelEntities) {
            Node node = new Node(modelEntity.getName());
            node.setScalarProperties(modelEntity.getProperties());
            // 判断是否为accessPoint
            node.setAccessPoint(accessPoints.contains(node.getNodeName()));
            // 获取queryNumbers
            node.setQueryNumbers(input.getQueryNumber(node.getNodeName()));
            // 获取出度节点
            node.setOutNeighbors(input.getOutNeighbors(node.getNodeName()));
            nodes.add(node);
        }
        return nodes;
    }

    public static void writeFile(String fileName, List<Node> nodes) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (Node node : nodes) {
                fw.write(node.toString());
                fw.write("\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<Property> getScalarProperties() {
        return scalarProperties;
    }

    public void setScalarProperties(List<Property> scalarProperties) {
        this.scalarProperties = scalarProperties;
    }

    public List<Integer> getQueryNumbers() {
        return queryNumbers;
    }

    public void setQueryNumbers(List<Integer> queryNumbers) {
        this.queryNumbers = queryNumbers;
    }

    public boolean isAccessPoint() {
        return isAccessPoint;
    }

    public void setAccessPoint(boolean accessPoint) {
        isAccessPoint = accessPoint;
    }

    public List<String> getOutNeighbors() {
        return outNeighbors;
    }

    public void setOutNeighbors(List<String> outNeighbors) {
        this.outNeighbors = outNeighbors;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeName='" + nodeName + '\'' +
                ", scalarProperties=" + scalarProperties +
                ", isAccessPoint=" + isAccessPoint +
                ", queryNumbers=" + queryNumbers +
                ", outNeighbors=" + outNeighbors +
                '}';
    }
}
