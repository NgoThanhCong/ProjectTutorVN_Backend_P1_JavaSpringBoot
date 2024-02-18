package com.greenwich.tutorvn.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order {
    // Order luu tru thong tin cua 1 don hang -= Co nghia la khi khach hang la Phu huynh goi dien den va yeu cau lop gia su thi chung ta tao 1 Order
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "course_sequence";

    private Long subject_Id;
    private Long customer_ID;
    private Long tutor_ID; // ID cua ong nhan lop = Bien nay update khi assign tutor vao day lop
    private  List<Long> listTutorRequired = new ArrayList<>(); // Nhung tutor y/c nhan lop

    private int numberRequest ;
    private int grade; // Lop day yeu cau
    private String studentName; // Ten cua hoc sinh
    private float timeInLesson; // Thoi gian day 1 buoi
    private boolean gender; // gioi tinh cua hoc sinh

    private int status; // 0 new 1- done 2  cancel;

    private String statusString ;
    private boolean genderRequired;// yeu cau giang vien nam hay nu
    private String phoneNumber; // so dien thoai khach hang
    private int learningMode; // hinh thuc hoc
    private String learningModeString;

    private String address; // Dia chi cua hoc sinh
    private float fee; // Hoc phi chi tra

    private boolean isActive; // *Xac nhan viec hien thi hay ko len danh sach tutor; // public
    private boolean isDelete; // !xoa khoi danh sach lop hoc trong truong hop du lieu la Rac // xoa han khoi Admin nhung Database van luu tru
    private List<String> learningTime;
    private List<LessonDate> lessonDates;
    private String startDate;
    private Date createdTime;
//    public Order(
//            Long subject_Id, Long customer_ID,
//            int grade, float timeInLesson,
//            boolean gender, boolean genderRequired, String phoneNumber,
//            int learningMode, String address, float fee,
//            boolean isActive, boolean isDelete, List<String> learningTime,
//            Date startDate, Date createdTime) {
//        this.subject_Id = subject_Id;
//        this.customer_ID = customer_ID;
//        this.grade = grade;
//        this.timeInLesson = timeInLesson;
//        this.gender = gender;
//        this.genderRequired = genderRequired;
//        this.phoneNumber = phoneNumber;
//        this.learningMode = learningMode;
//        this.address = address;
//        this.fee = fee;
//        this.isActive = isActive;
//        this.isDelete = isDelete;
//        this.learningTime = learningTime;
//        this.startDate = startDate;
//        this.createdTime = createdTime;
//        listTutorRequired = new ArrayList<>();
//    }

    public Long getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(Long customer_ID) {
        this.customer_ID = customer_ID;
    }

    public Long getTutor_ID() {
        return tutor_ID;
    }

    public void setTutor_ID(Long tutor_ID) {
        this.tutor_ID = tutor_ID;
    }

    public List<Long> getListTutorRequired() {
        return listTutorRequired;
    }

    public void setListTutorRequired(List<Long> listTutorRequired)
    {
        this.listTutorRequired = listTutorRequired;
    }

    public List<String> getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(List<String> learningTime) {
        this.learningTime = learningTime;
    }

    public int getNumberRequest() {
        return listTutorRequired.size();
    }

    public void setNumberRequest(int numberRequest) {
        this.numberRequest = numberRequest;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString() {
        if(status==OrderStatus.ORDER_NEW){
            return "NEW";

        }else if(status == OrderStatus.ORDER_DONE){
            return "DONE";

        }else if(status == OrderStatus.ORDER_CANCEL){
            return "CANCEL";

        }
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getLearningModeString() {
        if(learningMode == 1 ){
            return "OFFLINE";
        }else {
            return "ONLINE";
        }
       // return learningModeString;
    }

    public void setLearningModeString(String learningModeString) {
        this.learningModeString = learningModeString;
    }

    public List<LessonDate> getLessonDates() {
        return lessonDates;
    }

    public void setLessonDates(List<LessonDate> lessonDates) {
        this.lessonDates = lessonDates;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubject_Id() {
        return subject_Id;
    }

    public void setSubject_Id(Long subject_Id) {
        this.subject_Id = subject_Id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    public float getTimeInLesson() {
        return timeInLesson;
    }

    public void setTimeInLesson(float timeInLesson) {
        this.timeInLesson = timeInLesson;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isGenderRequired() {
        return genderRequired;
    }

    public void setGenderRequired(boolean genderRequired) {
        this.genderRequired = genderRequired;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLearningMode() {
        return learningMode;
    }

    public void setLearningMode(int learningMode) {
        this.learningMode = learningMode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
