package com.greenwich.tutorvn.service;

import com.greenwich.tutorvn.model.Order;
import com.greenwich.tutorvn.model.Tutor;
import com.greenwich.tutorvn.repository.OrderRepository;
import com.greenwich.tutorvn.repository.TutorRepository;
import com.greenwich.tutorvn.request.RequestOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> getTutorpage(int pageNum, int pageSize)
    {
        Pageable pageOne = PageRequest.of(pageNum, pageSize);
        Page<Tutor> returnList = tutorRepository.findAll(pageOne);
        return returnList.stream().toList();
    }



    public Optional<Tutor> searchTutorByPhone(String phoneNumber){
        return tutorRepository.findByPhone(phoneNumber);
    }

    public  Optional<Tutor> searchTutorByName(String name){
        return tutorRepository.findByName(name);
    }

    public  Optional<Tutor> searchTutorByEmail(String email){
        return tutorRepository.findByEmail(email);
    }

    public List<Tutor> findByKeyword(String keyword){

        List<Tutor> tutorList = new ArrayList<>();
        Optional<Tutor> findByName = searchTutorByName(keyword);
        Optional<Tutor> findByEmail = searchTutorByEmail(keyword);
        Optional<Tutor> findByPhone = searchTutorByPhone(keyword);
        if(findByName.isPresent()){
            tutorList.add(findByName.get());
            return tutorList ;
        }
        if(findByEmail.isPresent()){
            tutorList.add(findByEmail.get());
            return tutorList ;
        }
        if(findByPhone.isPresent()){
            tutorList.add(findByPhone.get());
            return tutorList ;
        }
        return null;

    }
}
