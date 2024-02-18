package com.greenwich.tutorvn.repository;

import com.greenwich.tutorvn.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository  extends MongoRepository<Notification, Long>
{
    List<Notification> findAllByReceivedID(long receiverID);
    List<Notification> findAllByDelete(boolean delete);
}
