package com.skynet.modifying_annotation.service;

import com.skynet.modifying_annotation.entity.Student;
import org.springframework.stereotype.Component;

@Component
public interface StudentService {

    public String insertStudent(Student student);

    public String updateStudent(Student student);

    public String deleteStudent(Student student);

    public Student findById(int id);

    String updateStudentWithNoModify(Student student);

}
