package com.greenwich.tutorvn.repository;

import com.greenwich.tutorvn.model.Notification;
import com.greenwich.tutorvn.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Test, Long> {

}
