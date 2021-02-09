package com.skynet.modifying_annotation.repository;

import com.skynet.modifying_annotation.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public interface StudentRepository extends CrudRepository<Student, Serializable> {

    @Modifying
    @Transactional
    @Query(value = "insert into Student (id,student_name,roll_number, university) "
            + "VALUES(:id,:studentName,:rollNumber,:university)", nativeQuery = true)
    public void insertStudentUsingQueryAnnotation(@Param("id") int id, @Param("studentName") String studentName,
                                                  @Param("rollNumber") String rollNumber, @Param("university") String university);

    @Modifying
    @Transactional
    @Query("update Student s SET s.studentName = :studentName WHERE s.id = :id")
    public void updateStudentUsingQueryAnnotation(@Param("studentName") String studentName, @Param("id") int id);

    @Modifying
    @Transactional
    @Query("delete from Student s where s.id = :id")
    public void deleteStudentUsingQueryAnnotation(@Param("id") int id);

    @Query("select s from Student s where s.id = :id")
    public Student findById(@Param("id") int id);
}
