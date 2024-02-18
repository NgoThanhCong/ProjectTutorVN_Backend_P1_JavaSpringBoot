package com.greenwich.tutorvn.model;

import jakarta.persistence.*;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Enumerated(EnumType.STRING)  // du lieu la kieu ENUM STRING == Con
    @Column(length = 20)
    private ERole name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public Role(Integer id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }
}
