package com.database.convert_qpg.DAO;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ModelEntityTests {

    public ModelEntity createUser() {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setName("User");
        List<Property> properties = new ArrayList<>();
        Property property1 = new Property("id", "int", true);
        Property property2 = new Property("name", "string", false);
        Property property3 = new Property("expertises", "string[]", false);
        Property property4 = new Property("email", "string", false);
        properties.add(property1);
        properties.add(property2);
        properties.add(property3);
        properties.add(property4);
        modelEntity.setProperties(properties);
        List<Ref> refs = new ArrayList<>();
        Ref ref1 = new Ref("Artifact", '*', "likes");
        Ref ref2 = new Ref("Venue", '*', "likes");
        Ref ref3 = new Ref("Review", '*', "posts");
        Ref ref4 = new Ref("Review", '*', "likes");
        refs.add(ref1);
        refs.add(ref2);
        refs.add(ref3);
        refs.add(ref4);
        modelEntity.setRefList(refs);
        return modelEntity;
    }

    public ModelEntity createReview() {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setName("Review");
        List<Property> properties = new ArrayList<>();
        Property property1 = new Property("id", "int", true);
        Property property2 = new Property("title", "string", false);
        Property property3 = new Property("body", "string", false);
        Property property4 = new Property("rating", "int", false);
        Property property5 = new Property("date", "string", false);
        properties.add(property1);
        properties.add(property2);
        properties.add(property3);
        properties.add(property4);
        properties.add(property5);
        modelEntity.setProperties(properties);
        List<Ref> refs = new ArrayList<>();
        Ref ref1 = new Ref("User", '1', "posts");
        Ref ref2 = new Ref("User", '*', "likes");
        Ref ref3 = new Ref("Artifact", '1', "rates");
        refs.add(ref1);
        refs.add(ref2);
        refs.add(ref3);
        modelEntity.setRefList(refs);
        return modelEntity;
    }

    public ModelEntity createArtifact() {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setName("Artifact");
        List<Property> properties = new ArrayList<>();
        Property property1 = new Property("id", "int", true);
        Property property2 = new Property("title", "string", false);
        Property property3 = new Property("authors", "string[]", false);
        Property property4 = new Property("keywords", "string[]", false);
        properties.add(property1);
        properties.add(property2);
        properties.add(property3);
        properties.add(property4);
        modelEntity.setProperties(properties);
        List<Ref> refs = new ArrayList<>();
        Ref ref1 = new Ref("Review", '*', "rates");
        Ref ref2 = new Ref("User", '*', "likes");
        Ref ref3 = new Ref("Venue", '1', "features");
        refs.add(ref1);
        refs.add(ref2);
        refs.add(ref3);
        modelEntity.setRefList(refs);
        return modelEntity;
    }

    public ModelEntity createVenue() {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setName("Venue");
        List<Property> properties = new ArrayList<>();
        Property property1 = new Property("name", "string", true);
        Property property2 = new Property("year", "int", true);
        Property property3 = new Property("topics", "string[]", false);
        Property property4 = new Property("country", "string", false);
        Property property5 = new Property("homepage", "string", false);
        properties.add(property1);
        properties.add(property2);
        properties.add(property3);
        properties.add(property4);
        properties.add(property5);
        modelEntity.setProperties(properties);
        List<Ref> refs = new ArrayList<>();
        Ref ref1 = new Ref("Artifact", '*', "features");
        Ref ref2 = new Ref("User", '*', "likes");
        refs.add(ref1);
        refs.add(ref2);
        modelEntity.setRefList(refs);
        return modelEntity;
    }

    @Test
    public void serializeTest() {
        ModelEntity user = createUser();
        try {
            serializeModelEntity(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ModelEntity demo = deserializeModelEntity();
            System.out.println(demo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createEntities() {
        //创建4个实体
        ModelEntity user = createUser();
        ModelEntity review = createReview();
        ModelEntity artifact = createArtifact();
        ModelEntity venue = createVenue();

        //把实体写入文件
        List<ModelEntity> modelEntities = new ArrayList<>();
        modelEntities.add(user);
        modelEntities.add(review);
        modelEntities.add(artifact);
        modelEntities.add(venue);

        String path = "src/main/resources/";
        String fileName = "entities.txt";
        //
        ModelEntity.writeFile(path + fileName, modelEntities);

    }

    //把文本数据转换为类数据
    @Test
    public void readDataToEntities(){
        String path = "src/main/resources/";
        String fileName = "entities.txt";
        //
        List<ModelEntity> modelEntities = ModelEntity.readFile(path + fileName);
        for(ModelEntity modelEntity : modelEntities){
            System.out.println(modelEntity);
        }
    }

    //序列化
    private static void serializeModelEntity(ModelEntity modelEntity) throws IOException {
        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("demo.txt")));
        oos.writeObject(modelEntity);
        System.out.println("modelEntity 对象序列化成功！");
        oos.close();
    }

    //反序列化
    private static ModelEntity deserializeModelEntity() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("demo.txt")));
        ModelEntity modelEntity = (ModelEntity) ois.readObject();
        System.out.println("modelEntity 对象反序列化成功！");
        return modelEntity;
    }
}
