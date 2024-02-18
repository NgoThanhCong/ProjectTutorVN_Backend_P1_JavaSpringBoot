package com.greenwich.tutorvn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Notification {


    @Transient
    public static final String SEQUENCE_NAME = "notification_sequence";

    @Id
    private Long id;
    private Long receivedID; // ==0 all
    private Long senderID;
    private String title;
    private String content;
    private int priority;
    private  int type;
    private Date createdDate;

    private boolean delete;
}
