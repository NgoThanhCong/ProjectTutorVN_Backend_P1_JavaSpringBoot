//package com.greenwich.tutorvn.service;
//
//import com.greenwich.tutorvn.database.MongoConfig;
//import com.greenwich.tutorvn.model.Test;
//import com.greenwich.tutorvn.repository.TestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//@Configurable
//public class TestService {
//    @Autowired
//    TestRepository testRepository;
//    @Autowired
//    MongoConfig mongoConfig;
//    public List<Test> getList(Date startDate, Date endDate) throws Exception {
//        Criteria publishedDateCriteria = Criteria
//                                .where("createdDate").gte(startDate)
//                                .lte(endDate);
//            Query query = new Query(publishedDateCriteria);
//        return mongoConfig.mongoTemplate().find(query, Test.class);
//          //  return testRepository.findAll(query);
//    }
//}
