package com.example.demo.services;

import com.example.demo.dao.StudentRepository;
import com.example.demo.dao.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {

        return studentRepository.getAllStudents();
    }

    public Optional<Student> getStudent(long id) {

        Optional<Student> firstFoundByIndex;
        firstFoundByIndex = Optional.ofNullable(studentRepository.getStudent(id));
        return firstFoundByIndex;
    }

    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    public boolean updateStudentData(long studentId, Student student) {
        return studentRepository.updateStudentData(studentId, student);
    }

    public boolean deleteStudent(long studentId) {
        return studentRepository.deleteStudent(studentId);
    }
}
