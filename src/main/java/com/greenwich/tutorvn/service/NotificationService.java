package com.greenwich.tutorvn.service;

import com.greenwich.tutorvn.model.Notification;
import com.greenwich.tutorvn.model.Order;
import com.greenwich.tutorvn.model.SubjectClass;
import com.greenwich.tutorvn.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    SubjectService subjectService;
    public Notification insert(Notification notification){
        notification.setCreatedDate(new Date());
        notification.setId(sequenceGeneratorService.generateSequence(notification.SEQUENCE_NAME)); 
        

        return notificationRepository.insert(notification);
    }

    public Notification insert(Order order){

        Notification notification = new Notification();

        Optional<SubjectClass> optionalSubjectClass = subjectService.findById(order.getSubject_Id());

        if(!optionalSubjectClass.isPresent()){

            return null;
        }

        notification.setCreatedDate(new Date());
        notification.setId(sequenceGeneratorService.generateSequence(notification.SEQUENCE_NAME));

        notification.setTitle("Thông báo nhận lớp");
        notification.setType(1);
        notification.setPriority(1);
        notification.setSenderID(0l);
        notification.setReceivedID(order.getTutor_ID());
        notification.setContent("Bạn nhận được yêu cầu dạy lớp " + optionalSubjectClass.get().getName()
                +"vào thời gian:" + order.getLearningTime()
                + ". Địa chỉ:" + order.getAddress() + "SĐT liên hệ:"
                + order.getPhoneNumber());

        return notificationRepository.insert(notification);

    }

    public List<Notification> getAll()
    {

        return notificationRepository.findAll();
    }


    public List<Notification> findByIdReceivedID(long id){
        return notificationRepository.findAllByReceivedID(id);
    }


    public Notification findById(long id){
        return notificationRepository.findById(id).get();
    }

    public void delete(long id){

        Optional<Notification> optional = notificationRepository.findById(id);
        if(optional.isPresent()){
            Notification notification = optional.get();
            notification.setDelete(true);
            notificationRepository.save(notification);
        }
    }
}
