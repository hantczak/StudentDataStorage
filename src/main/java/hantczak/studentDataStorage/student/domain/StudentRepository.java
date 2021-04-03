package hantczak.studentDataStorage.student.domain;

import java.util.List;

public interface StudentRepository {
    List<Student> getAllStudents();
    Student getStudent(long studentId);
    void addStudent(Student student);
    boolean updateStudentData(long studentId,Student student);
    boolean deleteStudent(long studentId);
}
