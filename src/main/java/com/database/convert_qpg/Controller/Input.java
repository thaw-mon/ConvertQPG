package com.database.convert_qpg.Controller;

import com.database.convert_qpg.DAO.ModelEntity;
import com.database.convert_qpg.DAO.QueryWorkload;

import java.util.ArrayList;
import java.util.List;

public class Input {
    List<ModelEntity> modelEntities;
    List<QueryWorkload> queryWorkloads;

    //通过workLoads的From部分获取accessPoint信息
    public List<String> getAccessPoints() {
        List<String> accessPoints = new ArrayList<>();
        for (QueryWorkload queryWorkload : queryWorkloads) {
            for (String property : queryWorkload.getFromProperties()) {
                //获取QPG path的第一个节点
                String[] arr = property.split("\\.");
                if (!accessPoints.contains(arr[0])) {
                    accessPoints.add(arr[0]);
                }
            }
        }
        return accessPoints;
    }

    //获取节点的QueryNumber 当前仅当当前节点为查询路径的accessPoint
    public List<Integer> getQueryNumber(String nodeName) {
        List<Integer> queryNumbers = new ArrayList<>();
        for (QueryWorkload queryWorkload : queryWorkloads) {
            for (String property : queryWorkload.getFromProperties()) {
                //获取QPG path的第一个节点
                String[] arr = property.split("\\.");
                if (nodeName.equals(arr[0])) {
                    queryNumbers.add(queryWorkload.getqNum());
                }
            }
        }
        return queryNumbers;
    }

    //根据QPG path获取 outNeighbors
    public List<String> getOutNeighbors(String nodeName) {
        List<String> outNeighbors = new ArrayList<>();
        for (QueryWorkload queryWorkload : queryWorkloads) {
            for (String property : queryWorkload.getFromProperties()) {
                //获取QPG path的第一个节点
                String[] arr = property.split("\\.");
                for (int i = 0; i + 2 < arr.length; i += 2) {
                    if (nodeName.equals(arr[i])) {
                        if (!outNeighbors.contains(arr[i + 2]))
                            outNeighbors.add(arr[i + 2]);
                    }
                }
            }
        }
        return outNeighbors;
    }

    public ModelEntity getModelEntityByName(String name) {
        for (ModelEntity modelEntity : modelEntities) {
            if (modelEntity.getName().equals(name)) {
                return modelEntity;
            }
        }
        return null;
    }

    public void init(String entityFile, String queryFile) {
        setModelEntities(ModelEntity.readFile(entityFile));
        setQueryWorkloads(QueryWorkload.readFile(queryFile));

    }

    public List<ModelEntity> getModelEntities() {
        return modelEntities;
    }

    public void setModelEntities(List<ModelEntity> modelEntities) {
        this.modelEntities = modelEntities;
    }

    public List<QueryWorkload> getQueryWorkloads() {
        return queryWorkloads;
    }

    public void setQueryWorkloads(List<QueryWorkload> queryWorkloads) {
        this.queryWorkloads = queryWorkloads;
    }

    @Override
    public String toString() {
        return "Input{" +
                "modelEntities=" + modelEntities +
                ", queryWorkloads=" + queryWorkloads +
                '}';
    }
}
