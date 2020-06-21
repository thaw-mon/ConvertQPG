package com.database.convert_qpg.DAO;

import com.database.convert_qpg.Controller.Input;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edge {
    String startNode;
    String endNode;

    String relationShip;
    char cardinality;
    List<Integer> queryNumbers;

    public Edge() {
        queryNumbers = new ArrayList<>();
    }

    public Edge(String start, String end) {
        startNode = start;
        endNode = end;
        queryNumbers = new ArrayList<>();
    }

    public Edge(String start, String end, String relation) {
        startNode = start;
        endNode = end;
        relationShip = relation;
        queryNumbers = new ArrayList<>();
    }

    //根据input创建边数据
    public static List<Edge> createEdges(Input input) {
        List<Edge> edges = new ArrayList<>();
        Map<String, Integer> edgeIndexMap = new HashMap<>();
        List<QueryWorkload> queryWorkloads = input.getQueryWorkloads();
        for (QueryWorkload queryWorkload : queryWorkloads) {
            //1.获取起始节点，终止节点和边的属性relationShip
            for (String property : queryWorkload.getFromProperties()) {
                String[] arr = property.split("\\.");
                for (int i = 0; i + 2 < arr.length; i += 2) {
                    String start = arr[i];
                    String end = arr[i + 2];
                    String relationShip = arr[i + 1];

                    String edgeIdentify = "<" + start + "," + end + ">/" + relationShip;
                    if (!edgeIndexMap.containsKey(edgeIdentify)) {
                        Edge edge = new Edge(start, end, relationShip);
                        //获取边之间的Cardinality 和qNumber
                        char cardinality = input.getModelEntityByName(start).getCardinality(end, relationShip);
                        edge.setCardinality(cardinality);
                        edge.addQueryNumber(queryWorkload.getqNum());
                        //
                        edgeIndexMap.put(edgeIdentify, edges.size());
                        edges.add(edge);
                    } else {
                        Edge edge = edges.get(edgeIndexMap.get(edgeIdentify));
                        edge.addQueryNumber(queryWorkload.getqNum());
                    }
                }
            }
        }
        return edges;
    }

    public static void writeFile(String fileName, List<Edge> edges) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (Edge edge : edges) {
                fw.write(edge.toString());
                fw.write("\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addQueryNumber(int qNumber) {
        if (queryNumbers == null) {
            queryNumbers = new ArrayList<>();
        }
        if (queryNumbers.contains(qNumber)) {
            return false;
        }
        queryNumbers.add(qNumber);
        return true;
    }

    public String getStartNode() {
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public String getEndNode() {
        return endNode;
    }

    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public char getCardinality() {
        return cardinality;
    }

    public void setCardinality(char cardinality) {
        this.cardinality = cardinality;
    }

    public List<Integer> getQueryNumbers() {
        return queryNumbers;
    }

    public void setQueryNumbers(List<Integer> queryNumbers) {
        this.queryNumbers = queryNumbers;
    }

    @Override
    public String toString() {
        String edgeIdentify = "<" + startNode + "," + startNode + ">/" + relationShip;

        return "Edge{" +
                "QPG edge=" + edgeIdentify +
                ", queryNumbers=" + queryNumbers +
                ", cardinality=" + cardinality +
                '}';
    }
}
