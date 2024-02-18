package com.greenwich.tutorvn.controller;
import com.greenwich.tutorvn.model.Customer;
import com.greenwich.tutorvn.model.Order;
import com.greenwich.tutorvn.model.ResponseObject;
import com.greenwich.tutorvn.repository.CustomerRepository;
import com.greenwich.tutorvn.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// * them customer, list customer, delete by id, search by id// ! xoa bang ten,
@RestController  // khai bao anotation restcontroller de tao ra 1 class Rest API
@RequestMapping(path = "/api/customer")
@CrossOrigin(origins = "http://localhost:3000,http://localhost:3000,http://localhost:3002", maxAge = 3600)
@Validated
public class CustomerController
{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insert ( @Valid @RequestBody Customer customer)
    {
//hekk
        Optional<Customer> optionalCustomer = customerRepository.findByPhone(customer.getPhone());
        if(optionalCustomer.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new
                    ResponseObject(204,"Cannot insert customer. Duplicate phone ", optionalCustomer.get()));

        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",customerService.insert(customer)));
    }

    @GetMapping("/findAll")
    ResponseEntity<ResponseObject> getListCustomerAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                "Success",customerRepository.findAll()));
    }
    @DeleteMapping("/delete/{id}")
    void delete (@PathVariable Long id) {customerRepository.deleteById(id);}

//    @GetMapping("/searchCustomerById/{id}")
//    ResponseEntity<ResponseObject>  findById(@PathVariable Long id)
//    {
//       Optional<Customer> optionalCustomer = customerRepository.findById(id);
//
//       if (optionalCustomer.isPresent())
//       {
//           return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Ok",
//                   "NOT_FOUTH",optionalCustomer.get()));
//       }
//       return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Ok",
//               "Success",customerRepository));
//    }
@GetMapping("/searchCustomerByName/{keyword}")
    ResponseEntity<ResponseObject> searchCustomerByName(@PathVariable String keyword)
{
    List<Customer> result = new ArrayList<>();
    for (Customer customer: customerRepository.findAllByIsDelete(false))
    {
        if(customer.getName().trim().toLowerCase().contains(keyword.trim().toLowerCase()));
        {
            result.add(customer);
        }
    }
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",result));

}
// @DeleteMapping("/{id}")
// void delete (@PathVariable Long id)
// {
//     customerRepository.deleteById(id);
// }

 @GetMapping("/{id}")     // * tk
 ResponseEntity<ResponseObject> findById (@PathVariable Long id)
 {

     Optional<Customer> optionalCustomer=  customerRepository.findById(id);
     if(optionalCustomer.isPresent())
     {
         return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                 "Success",optionalCustomer.get()));
     }

     return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
             "Success",""));
}

    @GetMapping("/search")
    ResponseEntity<ResponseObject> searchByKeyword(@RequestParam String keyword)
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                customerService.findByKeyword(keyword)));
    }


}
