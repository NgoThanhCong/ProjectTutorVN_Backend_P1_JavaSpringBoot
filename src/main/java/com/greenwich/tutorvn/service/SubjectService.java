package com.greenwich.tutorvn.service;


import com.greenwich.tutorvn.model.SubjectClass;
import com.greenwich.tutorvn.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Configurable
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public Optional<SubjectClass> findById(long id){
        return subjectRepository.findById(id);
    }
}
