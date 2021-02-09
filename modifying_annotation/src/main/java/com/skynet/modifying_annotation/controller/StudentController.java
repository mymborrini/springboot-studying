package com.skynet.modifying_annotation.controller;

import com.skynet.modifying_annotation.entity.Student;
import com.skynet.modifying_annotation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insertStudent(@RequestBody Student student) {
        String response = studentService.insertStudent(student);
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String updateStudent(@RequestBody Student student) {
        String response = studentService.updateStudent(student);
        return response;
    }

    @RequestMapping(value = "/updateNoModify", method = RequestMethod.PUT)
    @ResponseBody
    public String updateStudentNoModify(@RequestBody Student student) {
        String response = studentService.updateStudentWithNoModify(student);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudent(@PathVariable int id) {
        Student student = studentService.findById(id);
        return student;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteStudent(@RequestBody Student student) {
        String response = studentService.deleteStudent(student);
        return response;
    }
}
