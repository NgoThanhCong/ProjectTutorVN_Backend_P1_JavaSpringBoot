package com.greenwich.tutorvn.service;


import com.greenwich.tutorvn.model.Customer;
import com.greenwich.tutorvn.model.Tutor;
import com.greenwich.tutorvn.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public Customer insert(Customer customer){
        customer.setDelete(false);
        return customerRepository.save(customer);
    }

    public Optional<Customer> searchPhone(String phoneNumber){
        return customerRepository.findByPhone(phoneNumber);
    }

    public  Optional<Customer> searchName(String name){
        return customerRepository.findByName(name);
    }

    public List<Customer> findAll()
    {
        return customerRepository.findAll();
    }

    public Customer update(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> findByKeyword(String keyword){

        List<Customer> customerList = new ArrayList<>();
        Optional<Customer> findByName = searchName(keyword);
        Optional<Customer> findByPhone = searchPhone(keyword);
        if(findByName.isPresent()){
            customerList.add(findByName.get());
            return customerList ;
        }

        if(findByPhone.isPresent()){
            customerList.add(findByPhone.get());
            return customerList ;
        }
        return null;

    }
}
