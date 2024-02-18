package com.greenwich.tutorvn.controller;


import com.greenwich.tutorvn.model.Notification;
import com.greenwich.tutorvn.model.ResponseObject;
import com.greenwich.tutorvn.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "http://localhost:3000,http://localhost:3002", maxAge = 3600)

public class NotificationController {
    @Autowired
    NotificationService notificationService;


    @GetMapping("")
    public ResponseEntity<ResponseObject> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                notificationService.getAll()));
    }
    @PostMapping("/insert")

    public ResponseEntity<ResponseObject> insert(@RequestBody Notification notification){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                notificationService.insert(notification)));
    }



    @PostMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> findByID(@PathVariable long id){

        notificationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                  ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> insert(@PathVariable long id){


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                notificationService.findById(id)));
    }
}
