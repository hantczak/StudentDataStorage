package com.example.demo.database;

import com.example.demo.student.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentLocalRepository implements StudentRepository {

    private final Map<Long, Student> studentsMap = new HashMap<>();

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = studentsMap.values().stream()
                .collect(Collectors.toCollection(ArrayList::new));
        return studentList;
    }

    @Override
    public Student getStudent(long studentId) {

        return studentsMap.get(studentId);
    }

    @Override
    public void addStudent(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void updateStudentData(long studentId, Student student) {
        studentsMap.put(studentId, student);
    }

    @Override
    public void deleteStudent(long studentId) {
        studentsMap.remove(studentId);
    }
}
