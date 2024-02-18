package com.greenwich.tutorvn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDate {

    private String date;
    private int slot;

    private String startTime;

    private String endTime;


}
