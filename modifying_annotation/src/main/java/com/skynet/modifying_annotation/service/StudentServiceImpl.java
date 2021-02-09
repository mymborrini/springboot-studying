package com.skynet.modifying_annotation.service;

import com.skynet.modifying_annotation.entity.Student;
import com.skynet.modifying_annotation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public String insertStudent(Student student) {
        int id = student.getId();
        String studentName = student.getStudentName();
        String rollNumber = student.getRollNumber();
        String university = student.getUniversity();
        studentRepository.insertStudentUsingQueryAnnotation(id, studentName, rollNumber, university);
        return "Record inserted successfully using @Modifiying and @query Named Parameter";
    }

    @Override
    public String updateStudent(Student student) {
        studentRepository.updateStudentUsingQueryAnnotation(student.getStudentName(), student.getId());
        return "Record updated successfully using @Modifiying and @query Named Parameter";
    }

    @Override
    public String deleteStudent(Student student) {
        studentRepository.deleteStudentUsingQueryAnnotation(student.getId());
        return "Record deleted successfully using @Modifiying and @query Named Parameter";
    }

    @Override
    public Student findById(int id) {
        Student studentresponse = studentRepository.findById(id);
        return studentresponse;
    }

    @Transactional
    public String updateStudentWithNoModify(Student student) {
        studentRepository.save(student);
        return "Record updated successfully using save() method";
    }
}
