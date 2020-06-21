package com.database.convert_qpg.DAO;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class QueryWorkloadTests {

    @Test
    public void demoStr() {
        String str = "aa select sss";
        System.out.println(str.indexOf(" ")); //2
        System.out.println(str.indexOf("select")); //3
        System.out.println(str.indexOf("order by", 3)); //-1
        String[] arr = str.substring(str.indexOf("select") + 7).split(" ");

        for (String s : arr) {
            System.out.println(s);
        }
    }

    public QueryWorkload createQ1() {
        QueryWorkload queryWorkload = new QueryWorkload();
        queryWorkload.setqNum(1);
        List<String> selectProperties = new ArrayList<>();
        String selectProperty1 = "Artifact.id";
        String selectProperty2 = "Artifact.title";
        String selectProperty3 = "Artifact.authors";
        String selectProperty4 = "Artifact.keywords";
        selectProperties.add(selectProperty1);
        selectProperties.add(selectProperty2);
        selectProperties.add(selectProperty3);
        selectProperties.add(selectProperty4);

        List<String> fromProperties = new ArrayList<>();
        String fromProperty1 = "Venue.features.Artifact";
        fromProperties.add(fromProperty1);

        List<String> whereProperties = new ArrayList<>();
        String whereProperty1 = "Venue.name = \"VLDB\"";
        String whereProperty2 = "Venue.year >= 2015";
        whereProperties.add(whereProperty1);
        whereProperties.add(whereProperty2);

        List<String> orderProperties = new ArrayList<>();
        String orderProperty1 = "Venue.year(DESC)";

        queryWorkload.setSelectProperties(selectProperties);
        queryWorkload.setFromProperties(fromProperties);
        queryWorkload.setWhereProperties(whereProperties);
        queryWorkload.setOrderProperties(orderProperties);

        return queryWorkload;
    }

    public QueryWorkload createQ2() {
        QueryWorkload queryWorkload = new QueryWorkload();
        queryWorkload.setqNum(1);
        List<String> selectProperties = new ArrayList<>();
        String selectProperty1 = "Artifact.id";
        String selectProperty2 = "Artifact.title";
        String selectProperty3 = "Artifact.authors";
        String selectProperty4 = "Artifact.keywords";
        selectProperties.add(selectProperty1);
        selectProperties.add(selectProperty2);
        selectProperties.add(selectProperty3);
        selectProperties.add(selectProperty4);

        List<String> fromProperties = new ArrayList<>();
        String fromProperty1 = "Venue.features.Artifact";
        fromProperties.add(fromProperty1);

        List<String> whereProperties = new ArrayList<>();
        String whereProperty1 = "Venue.name = \"VLDB\"";
        String whereProperty2 = "Venue.year >= 2015";
        whereProperties.add(whereProperty1);
        whereProperties.add(whereProperty2);

        List<String> orderProperties = new ArrayList<>();
        String orderProperty1 = "Venue.year(DESC)";

        queryWorkload.setSelectProperties(selectProperties);
        queryWorkload.setFromProperties(fromProperties);
        queryWorkload.setWhereProperties(whereProperties);
        queryWorkload.setOrderProperties(orderProperties);

        return queryWorkload;
    }


    @Disabled
    @Test
    public void createSQLStatement() {
        List<QueryWorkload> queryWorkloads = new ArrayList<>();
        queryWorkloads.add(createQ1());
        String path = "src/main/resources/";
        String fileName = "sql.txt";
        //
        QueryWorkload.writeFile(path + fileName, queryWorkloads);
    }

    @Test
    public void readDataToQueryLoads() {
        String path = "src/main/resources/";
        String fileName = "sql.txt";
        //
        List<QueryWorkload> queryWorkloads = QueryWorkload.readFile(path + fileName);
        assert queryWorkloads.size() == 9;
        for (QueryWorkload queryWorkload : queryWorkloads) {
            System.out.println(queryWorkload);
        }
    }
}
