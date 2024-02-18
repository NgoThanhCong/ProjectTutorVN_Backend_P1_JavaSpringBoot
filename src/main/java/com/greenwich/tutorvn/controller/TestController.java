//package com.greenwich.tutorvn.controller;
//
//import com.greenwich.tutorvn.model.Test;
//import com.greenwich.tutorvn.service.TestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//
//@RestController  // khai bao anotation restcontroller de tao ra 1 class Rest API
//@RequestMapping(path  = "/api/v1/test")  //tao ra URL cos dang /api/
//public class TestController {
//
//    @Autowired
//    TestService testService;
//
//    @GetMapping("/a")
//    List<String> getListTutor()
//    {
//        return Arrays.asList("Nguyen van A", "Pham cong minh","nguyen van gt", "nguyen tien dat");
//    }
//    @GetMapping("/b")
//    List<String> getListaa()
//    {
//        return Arrays.asList("Adasd");
//    }
//    @GetMapping("/c")
//    List<String> getLisbb()
//    {
//        return Arrays.asList("Nguyen van A", "Pham cong minh","nguyen van gt", "nguyen tien dat");
//    }
//
//
//    @GetMapping("/getAllRecord")
//    List<Test> getAll(@RequestParam String startDate, @RequestParam String endDate) throws ParseException {
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       // formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date start = formatter.parse(startDate);
//        Date end =  formatter.parse(endDate);
//        try {
//            return testService.getList(start, end);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
