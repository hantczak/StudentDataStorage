package com.example.demo.dao;

import com.example.demo.dao.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public boolean updateStudentData(long studentId, Student student) {
        if (studentsMap.containsKey(studentId)){
            studentsMap.put(student.getId(), student);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteStudent(long studentId) {
        if(studentsMap.containsKey(studentId)){
            studentsMap.remove(studentId);
            return true;
        }else {
            return false;
        }

    }

    public Map<Long, Student> getMap() {
        return this.studentsMap;
    }
}
