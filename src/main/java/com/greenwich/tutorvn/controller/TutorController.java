package com.greenwich.tutorvn.controller;

import com.greenwich.tutorvn.model.Order;
import com.greenwich.tutorvn.model.ResponseObject;
import com.greenwich.tutorvn.model.ResponseObject;
import com.greenwich.tutorvn.model.Tutor;
import com.greenwich.tutorvn.repository.OrderRepository;
import com.greenwich.tutorvn.repository.TutorRepository;
import com.greenwich.tutorvn.service.OrderService;
import com.greenwich.tutorvn.service.TutorService;
import jakarta.validation.Valid;
import org.hibernate.annotations.SQLDeleteAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController  // *khai bao anotation restcontroller de tao ra 1 class Rest API
@RequestMapping(path  = "/api/v1/tutor")  // *tao ra URL cos dang /api/
@CrossOrigin(origins = "http://localhost:3000,http://localhost:3002", maxAge = 3600)
public class TutorController {
    /*
     *tang nay dung de tao ra cac router api duoi dang http url - restful API
     */
    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    TutorService tutorService;
  // link va  yeu cau voi repository

//    @GetMapping("/gettutors")  // gui yeu cau de lay du lieu
//    List<Tutor> getListTutor()
//    {
//        return tutorRepository.findAllByIsDelete(false);
//    }



    @GetMapping("/getTutorByPage")  // *gui yeu cau de lay du lieu
    ResponseEntity<ResponseObject> getOrderByPage(int pageNum, int pageSize)
    {
        List<Tutor> listTutor = new ArrayList<>();
        listTutor = tutorService.getTutorpage(pageNum, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,
                "Success",listTutor));
    }

    @GetMapping("/getAll")  // *gui yeu cau de lay du lieu
    ResponseEntity<ResponseObject> getListTutorAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                tutorRepository.findAllByIsDelete(false)));
    }

    @PostMapping("/insertTutor")  // *gui len server mot du lieu de create/update 1 tai nguyen nao do
    ResponseEntity<ResponseObject> insert ( @RequestBody Tutor tutor)  // *@RequestBody dinh nghia 1 doi tuong truyen vao
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                tutorRepository.save(tutor)));   // *.save(tutor) luu yeu cau va return lai gia tri
    }

    @DeleteMapping("/{id}")  // *gui len server mot du lieu de create/update 1 tai nguyen nao do
    void deleteHard (@PathVariable Long id)
    {
        tutorRepository.deleteById(id);
    }

    @GetMapping("/softDelete/{id}")
    void deleteSoft (@PathVariable Long id)
    {
        Optional<Tutor> optionalTutor = tutorRepository.findById(id); // *Co the tra ve hoac Not do vay phai de kieu la Optional<Tutor>

        if(optionalTutor.isPresent()) // *Kiem tra neu optional ton tai gia tri co nghia la tim duoc Tutor voi ID o tren
        {
            Tutor tutor = optionalTutor.get(); // *Lay ve doi tuong Tutor chinh xac
            tutor.setDelete(true);
            tutorRepository.save(tutor);
        }
    }

    @GetMapping("/{id}") //{id} tim kiem bang id
    ResponseEntity<ResponseObject> findById(@PathVariable Long id)  // *gui yeu cau tim id, @PathVarribale bien id bang duong dan
    {
        Optional<Tutor> optionalTutor = tutorRepository.findById(id);  // *optional kieu tra ve co hoac khong

        if (optionalTutor.isPresent())  // *isPresent la phuong thuc kiem tra co du lieu hay khong
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                    optionalTutor.get()));  // *.get lay ve gia tri
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",tutorRepository));
    }

    @GetMapping("/searchByName/{keyword}")
    ResponseEntity<ResponseObject> getListTutorAll(@PathVariable String keyword)
    {

        List<Tutor> result = new ArrayList<>();
        for(Tutor tutor: tutorRepository.findAllByIsDelete(false))
        {
            if(tutor.getName().trim().toLowerCase().contains(keyword.trim().toLowerCase()))
            {
                result.add(tutor);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",result));

    }

    @GetMapping("/gettutors")
    ResponseEntity<ResponseObject> getListTutor()
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                tutorRepository.findAllByIsDelete(false)));
    }


    @GetMapping("/search")
    ResponseEntity<ResponseObject> searchByKeyword(@RequestParam String keyword)
    {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",
                tutorService.findByKeyword(keyword)));
    }

    @PutMapping("/update")
    ResponseEntity<ResponseObject> update(@Valid @RequestBody Tutor tutor)
    {
        Optional<Tutor> optionalTutor = tutorRepository.findById(tutor.getId());
        if(optionalTutor.isPresent())  // *kiem tra xem co ton tai ket qua optinalTutor hay  k
        {
            Tutor result = tutorRepository.save(tutor);  // *update ban ghi tutor truyen vao va tra ket qua ngay sau do
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",result));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",null));
    }


    @PutMapping("/confirmTutor")
    ResponseEntity<ResponseObject> confirm (@Valid @RequestParam Long idTutor)
    {
        Optional<Tutor> optionalTutor = tutorRepository.findById(idTutor);
        if(optionalTutor.isPresent())  // kiem tra xem co ton tai ket qua optinalTutor hay  k
        {
            Tutor tutor = optionalTutor.get();
            tutor .setConfirmed(true);

            Tutor result = tutorRepository.save(tutor);  //update ban ghi tutor truyen vao va tra ket qua ngay sau do
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",result));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",null));
    }


    @PutMapping("/updateinfo")
    ResponseEntity<ResponseObject> updateInfo(@RequestParam Long id, @RequestParam String name, @RequestParam String phone, @RequestParam String email)
    {
        Optional<Tutor> optionalTutor = tutorRepository.findById(id);

        if(optionalTutor.isPresent())  // kiem tra xem co ton tai ket qua optinalTutor hay  k
        {
            Tutor foundTutor = optionalTutor.get();
            foundTutor.setName(name);
            foundTutor.setPhone(phone);
            foundTutor.setEmail(email);
            Tutor result = tutorRepository.save(foundTutor);  //update ban ghi tutor truyen vao va tra ve ket qua ngay sau do
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",result));   // tra ve ket qua
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"Success",null));
    }
}
