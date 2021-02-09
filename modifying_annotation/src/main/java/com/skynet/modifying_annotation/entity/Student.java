package com.skynet.modifying_annotation.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "roll_number")
    private String rollNumber;

    @Column(name = "university")
    String university;

}
