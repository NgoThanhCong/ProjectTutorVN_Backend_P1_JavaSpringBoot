package com.greenwich.tutorvn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Date;
@Entity
public class Tutor {


    // *@NotNull : truong bat buoc phai co trong du lieu
    // *@NotEmpty: Co truong do nhung phai co du lieu va du lieu khong duoc de null
    // *@NotBlank : chi ap dung cho string va khong duoc de trong
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // *id sinh tu dong trong database
    private Long id;  //
    private String name; // *name cua gia su
    private String address; // *dia chi
    private String phone;
    private boolean gender;   // *gioi tinh
    private Date birthDay;

    private  Long userID;

    private String email;
    protected String experiences;   // *mo ta kinh nghiem
    private String currentJob;   // *cong viec hien tai
    private  boolean confirmed;
    private boolean isDelete;  // *xoa
    private String avatar; // *anh
    private String university;    //
    private String major;
    private boolean isGraduated;  // ?trang thai tot nghiep y/n

    public Tutor(String name, String address,
                 String phone, boolean gender, Date birthDay,
                 String email, String experiences, String currentJob,
                 boolean confirmed, boolean isDelete, String avatar, String university,
                 String major, boolean isGraduated) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.birthDay = birthDay;
        this.email = email;
        this.experiences = experiences;
        this.currentJob = currentJob;
        this.confirmed = confirmed;
        this.isDelete = isDelete;
        this.avatar = avatar;
        this.university = university;
        this.major = major;
        this.isGraduated = isGraduated;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Tutor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUniversity() {
        return university;
    }

    public void setUnivercity(String univercity) {
        this.university = univercity;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isGraduated() {
        return isGraduated;
    }

    public void setGraduated(boolean graduated) {
        isGraduated = graduated;
    }
}
