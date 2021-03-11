package com.example.demo.student.infrastructure.database;

import com.example.demo.student.domain.StudentRepository;
import com.example.demo.student.domain.Student;
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
        Student oldStudent = null;
        if (studentsMap.containsKey(studentId)) {
            oldStudent = studentsMap.put(student.getId(), student);
        }
        return oldStudent != null;
    }

    @Override
    public boolean deleteStudent(long studentId) {
        Student removedStudent = studentsMap.remove(studentId);
        return removedStudent != null;
    }
}
