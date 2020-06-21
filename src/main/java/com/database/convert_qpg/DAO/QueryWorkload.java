package com.database.convert_qpg.DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryWorkload {
    int qNum;
    List<String> SelectProperties;
    List<String> FromProperties;
    List<String> WhereProperties;
    List<String> OrderProperties;

    public static void writeFile(String fileName, List<QueryWorkload> queryWorkloads) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (QueryWorkload queryWorkload : queryWorkloads) {
                fw.write(queryWorkload.toString());
                fw.write("\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<QueryWorkload> readFile(String fileName) {
        List<QueryWorkload> queryWorkloads = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                QueryWorkload queryWorkload = StringToClass(str);
                queryWorkloads.add(queryWorkload);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryWorkloads;
    }

    //SELECT Artifact.id, Artifact.title, Artifact.authors, Artifact.keywords, Venue.name
    //         FROM Artifact.features.Venue
    //         WHERE Artifact.author = “John Smith”
    //         ORDER BY Venue.year(DESC)
    public static QueryWorkload StringToClass(String str) {
        QueryWorkload queryWorkload = new QueryWorkload();
        int qNum = Integer.parseInt(str.substring(1, str.indexOf(" ")));
        queryWorkload.setqNum(qNum);
        int select = str.indexOf("SELECT");
        int from = str.indexOf("FROM", select);
        int where = str.indexOf("WHERE", from);
        int order = str.indexOf("ORDER BY", where); //可能为-1
        //trim() 去除头尾的空格
        String[] selectStr = str.substring(select + 7, from).trim().split(",");
        List<String> selectProperties = new ArrayList<>();
        List<String> fromProperties = new ArrayList<>();
        List<String> whereProperties = new ArrayList<>();
        List<String> orderProperties = new ArrayList<>();


        selectProperties.addAll(Arrays.asList(selectStr));
        String fromStr = str.substring(from + 5, where).trim();
        fromProperties.addAll(Arrays.asList(fromStr));
        String[] whereStr = str.substring(where + 6, order == -1 ? str.length() : order).trim().split(" AND ");
        whereProperties.addAll(Arrays.asList(whereStr));
        if (order != -1) {
            String[] orderStr = str.substring(order + 9).trim().split(",");
            orderProperties.addAll(Arrays.asList(orderStr));
        }

        queryWorkload.setSelectProperties(selectProperties);
        queryWorkload.setFromProperties(fromProperties);
        queryWorkload.setWhereProperties(whereProperties);
        queryWorkload.setOrderProperties(orderProperties);
        return queryWorkload;
    }

    public int getqNum() {
        return qNum;
    }

    public void setqNum(int qNum) {
        this.qNum = qNum;
    }

    public List<String> getSelectProperties() {
        return SelectProperties;
    }

    public void setSelectProperties(List<String> selectProperties) {
        SelectProperties = selectProperties;
    }

    public List<String> getFromProperties() {
        return FromProperties;
    }

    public void setFromProperties(List<String> fromProperties) {
        FromProperties = fromProperties;
    }

    public List<String> getWhereProperties() {
        return WhereProperties;
    }

    public void setWhereProperties(List<String> whereProperties) {
        WhereProperties = whereProperties;
    }

    public List<String> getOrderProperties() {
        return OrderProperties;
    }

    public void setOrderProperties(List<String> orderProperties) {
        OrderProperties = orderProperties;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Q").append(qNum).append(" - ");
        sb.append("SELECT ");
        for (String selectProperty : SelectProperties) {
            sb.append(selectProperty).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" FROM ");
        for (String fromProperty : FromProperties) {
            sb.append(fromProperty);
        }
        sb.append(" WHERE ");
        for (String whereProperty : WhereProperties) {
            sb.append(whereProperty).append(" AND ");
        }
        //删除多出的 " AND "
        sb.delete(sb.length() - 5, sb.length());
        if (!OrderProperties.isEmpty()) {
            sb.append(" ORDER BY ");
            for (String orderProperty : OrderProperties) {
                sb.append(orderProperty).append(",");
            }
            //删除多余的","
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
