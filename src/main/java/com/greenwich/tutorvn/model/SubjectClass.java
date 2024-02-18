package com.greenwich.tutorvn.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // khai bao bang du lieu
public class SubjectClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // id sinh tu dong tang trong database
    private Long id;
    private String category;
    private String name;
    private int grade;

    public SubjectClass(String category, String name, int grade) {
        this.category = category;
        this.name = name;
        this.grade = grade;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public SubjectClass() {
    }
}
