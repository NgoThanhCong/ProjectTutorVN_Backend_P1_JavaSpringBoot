package com.greenwich.tutorvn.repository;

import com.greenwich.tutorvn.model.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectClass, Long> {
    Optional<SubjectClass> findByName(String name);
}
