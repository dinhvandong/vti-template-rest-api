package com.vti.templaterestfulapi.database;//package com.btect.fpt.cmsbackend.database;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Service;
////import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//@Configuration
//@Service
//@Configurable
//public class MongoConfig {
//    //spring.data.mongodb.database=tutor-mongodb
//    @Bean
//    public static MongoClient mongo() {
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/vip88-mongodb");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    public static MongoTemplate instance = null;
//
//    @Bean
//    public static MongoTemplate mongoTemplate() throws Exception {
//        if(instance== null ){
//
//            instance = new MongoTemplate(mongo(), "vip88-mongodb");
//        }
//        return instance;
//    }
//
//}