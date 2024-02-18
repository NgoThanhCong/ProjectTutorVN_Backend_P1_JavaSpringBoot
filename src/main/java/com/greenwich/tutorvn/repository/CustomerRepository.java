package com.greenwich.tutorvn.repository;

import com.greenwich.tutorvn.model.Customer;
import com.greenwich.tutorvn.model.Order;
import com.greenwich.tutorvn.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Optional<Customer> findByPhone(String phone);
    // *Optional laf kieu tra ve customer or null, de dung trong customerController
    Optional<Customer> findByName(String name);
    List<Customer> findAllByIsDelete(boolean isDelete);
    List<Customer> findAllByName(String keyword);
}

