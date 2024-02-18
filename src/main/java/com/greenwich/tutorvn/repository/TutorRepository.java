package com.greenwich.tutorvn.repository;

import com.greenwich.tutorvn.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TutorRepository extends JpaRepository <Tutor, Long> {
    // *Giup lam viec voi bang du lieu tuong ung
    // *O day la bang Tutor
    // *Long la ID cua tutor Table

    Optional<Tutor> findByName(String name);

    List<Tutor> findAllByConfirmed(boolean confirmed);

    List<Tutor> findAllByIsDelete(boolean isDelete);

    Optional<Tutor> findByPhone(String phone);
    Optional<Tutor> findByEmail(String email);

    List<Tutor> findAllByName(String keyword); // *Tim kiem theo ten
}


