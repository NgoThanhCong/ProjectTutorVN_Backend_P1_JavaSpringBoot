package com.greenwich.tutorvn.controller;
import com.greenwich.tutorvn.model.*;
import com.greenwich.tutorvn.repository.OrderRepository;
import com.greenwich.tutorvn.request.RequestOrder;
import com.greenwich.tutorvn.service.NotificationService;
import com.greenwich.tutorvn.service.OrderService;
import com.greenwich.tutorvn.service.SequenceGeneratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController  // khai bao anotation restcontroller de tao ra 1 class Rest API
@RequestMapping(path  = "/api/course")  // *tao ra URL cos dang /api/
@CrossOrigin(origins = "http://localhost:3000,http://localhost:3002", maxAge = 3600)
public class OrderCotroller {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @Autowired
    NotificationService notificationService;


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @GetMapping("")  // *gui yeu cau de lay du lieu
    ResponseEntity<ResponseObject> getListOrder(@RequestParam int pageNum, @RequestParam int pageSize)
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                "Success",orderService.getOrderpage(pageNum, pageSize)) );
    }

    @GetMapping("/paging")                // *phân trang
    ResponseEntity<ResponseObject> getOrderByPage(int pageNum, int pageSize)
    {
        List<Order> listOrder = new ArrayList<>();
        listOrder = orderService.getOrderpage(pageNum, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                "Success",listOrder) );
    }


    @GetMapping("/getByTutor")
    ResponseEntity<ResponseObject> getListOrderByTutors()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                "Success",orderRepository.findAll( )) );
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insert (@RequestBody Order order)
    {

        order.setDelete(false);
        order.setActive(true);
        order.setStatus(OrderStatus.ORDER_NEW);
        order.setNumberRequest(order.getListTutorRequired().size()
        );
        System.out.println("insertorder");

        order.setId(sequenceGeneratorService.generateSequence(order.SEQUENCE_NAME));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                "Success",orderRepository.insert(order)));   // *.save(tutor) luu yeu cau va return lai gia tri
    }
//    @PutMapping("/update")
//    ResponseEntity<ResponseObject> update(@Valid @RequestBody Tutor tutor)
//    {
//        Optional<Tutor> optionalTutor = tutorRepository.findById(tutor.getId());
//        if(optionalTutor.isPresent())  // *kiem tra xem co ton tai ket qua optinalTutor hay  k
//        {
//            Tutor result = tutorRepository.save(tutor);  // *update ban ghi tutor truyen vao va tra ket qua ngay sau do
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",result));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",null));
//    }
    @PostMapping("/update")
    ResponseEntity<ResponseObject> update (@RequestParam String studentName,
                                           @RequestParam String phoneNumber,
                                           @RequestParam String address, @RequestParam long id)
    {    Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent())
        {

            Order oldValue = optionalOrder.get();
            oldValue.setStudentName(studentName);
            oldValue.setAddress(address);
            oldValue.setPhoneNumber(phoneNumber);

            Order result = orderRepository.save(oldValue);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",result));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",null));


//        order.setDelete(false);
//        order.setActive(true);
//        order.setStatus(OrderStatus.ORDER_NEW);
//        order.setNumberRequest(order.getListTutorRequired().size()
//        );
//        System.out.println("insertorder");
//
//        order.setId(sequenceGeneratorService.generateSequence(order.SEQUENCE_NAME));
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
//                "Success",orderRepository.insert(order)));   // *.save(tutor) luu yeu cau va return lai gia tri
    }

    @PostMapping("/assignTutor")   // *chỉ định một gia sư dạy, không có trong list các gia sư đã yêu cầu
    ResponseEntity<ResponseObject> assignTutor (@RequestBody RequestOrder requestOrder)
    {
        Optional<Order> optionalOrder = orderRepository.findById(requestOrder.getIdOrder());
        if(optionalOrder.isPresent())
        {
            Order order = optionalOrder.get();
            order.setTutor_ID(requestOrder.getIdTutor());

            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                    "Success",orderService.assignTutor(requestOrder) ));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject(200,
                "Not implement","" ));
    }

    @PostMapping("/requestTutor") // các yêu cầu nhận lớp của gia sư
    ResponseEntity<ResponseObject> requestOrder (@RequestBody RequestOrder requestOrder)
    {
        Optional<Order> optionalOrder = orderRepository.findById(requestOrder.getIdOrder());
        if(optionalOrder.isPresent())
        {

            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                    "Success",orderService.requestTutor(requestOrder) ));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject(200,
                "Not implement","" ));
    }



    @PostMapping("/cancel") // * một gia sư hủy yeu cầu nhận lớp, list yeu cau sẽ update
    ResponseEntity<ResponseObject> cancleOrder (@RequestBody RequestOrder requestOrder)
    {
        Optional<Order> optionalOrder = orderRepository.findById(requestOrder.getIdOrder());
        if(optionalOrder.isPresent())
        {
            Order order = optionalOrder.get();
            List<Long> listIdTutor = order.getListTutorRequired();

            try{
                for(Long id: listIdTutor)
                {
                    if(id == requestOrder.getIdTutor())
                    {
                        listIdTutor.remove(id);
                    }
                }
            }catch (Exception exception)
            {
            }
            order.setListTutorRequired(listIdTutor);
            //setTutor_ID(idTutor);
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                    "Success",orderRepository.save(order) ));
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject(200,
                "Not implement","" ));
    }


    @DeleteMapping("/{id}")  // *xoa order bang id
    void delete (@PathVariable Long id)
    {
        orderRepository.deleteById(id);
    }


    @GetMapping("/{id}")     // * tim kiem order bang id
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        // *tra ve kieu du lieu Optional ===Tim hieu ve kieu optional
        // *Bien duong dan
        Optional<Order> optionalOrder=  orderRepository.findById(id);
        if(optionalOrder.isPresent())
        {
            // !Neu tim thay Tutor co id = id truyen vao tu ham thi tra ve
            return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                    "Success",optionalOrder.get())); // Lay ve doi tuong neu no ton tai
        }
        // !neu ko tim thay tra ve NULL
        return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                "Success",""))
                ; // Lay ve doi tuong neu no ton tai;
    }
//    @PostMapping("/insertorder")  // *gui len server mot du lieu de create/update 1 tai nguyen nao do
//    ResponseEntity<ResponseObject> insert (@RequestBody Order order)  // *@RequestBody dinh nghia 1 doi tuong truyen vao
//    {
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Ok",
//                "Success",orderRepository.save(order)));   // *.save(tutor) luu yeu cau va return lai gia tri
//    }



}

