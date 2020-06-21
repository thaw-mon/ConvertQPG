package com.database.convert_qpg.DAO;

import com.database.convert_qpg.Controller.Input;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueryKeysEntityTest {

    @Test
    void createQueryKeysEntities() {
        Input input = new Input();
        String path = "src/main/resources/";
        String fileName1 = "entities.txt";
        String fileName2 = "sql.txt";
        input.init(path + fileName1, path + fileName2);

        List<QueryKeysEntity> queryKeysEntities = QueryKeysEntity.createQueryKeysEntities(input);
        assert queryKeysEntities.size() == 9;
        for (QueryKeysEntity queryKeysEntity : queryKeysEntities) {
            System.out.println(queryKeysEntity);
        }
    }

    @Test
    void writeFile() {
        Input input = new Input();
        String path = "src/main/resources/";
        String fileName1 = "entities.txt";
        String fileName2 = "sql.txt";
        input.init(path + fileName1, path + fileName2);

        List<QueryKeysEntity> queryKeysEntities = QueryKeysEntity.createQueryKeysEntities(input);

        String fileName = "queryKeysEntity.txt";
        QueryKeysEntity.writeFile(path + fileName, queryKeysEntities);
    }
}