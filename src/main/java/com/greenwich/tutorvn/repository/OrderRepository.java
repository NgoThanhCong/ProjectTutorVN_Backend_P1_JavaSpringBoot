package com.greenwich.tutorvn.repository;

import com.greenwich.tutorvn.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {


}
