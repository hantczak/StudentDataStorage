package com.example.demo.student;

import com.example.demo.database.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        Student student = new Student(
                1L,
                "Mariam",
                "mariam.jamal@gmail.com",
                LocalDate.of(2000,Month.JANUARY,1),
                21
        );
        studentRepository.addStudent(student);
    }

    public List<Student> getAllStudents() {

        return studentRepository.getAllStudents();
    }

    public List<Student> getStudent(long id){
        List<Student> student = new ArrayList<>();
        Optional<Student> firstFoundByIndex;
        firstFoundByIndex = Optional.ofNullable(studentRepository.getMap().getOrDefault(id,null));
        if(firstFoundByIndex.isPresent()){
            student.add(firstFoundByIndex.get());
            return student;
        }else {
            return student;
        }
    }
    public void addStudent(Student student){
        studentRepository.addStudent(student);
    }
    public void updateStudentData(long studentId,Student student){

        studentRepository.updateStudentData(studentId,student);
    }
    public void deleteStudent(long studentId){

        studentRepository.deleteStudent(studentId);
    }
}
