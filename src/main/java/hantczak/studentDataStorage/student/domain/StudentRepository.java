package hantczak.studentDataStorage.student.domain;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> getAllStudents();
    Optional<Student> getStudent(long studentId);
    void addStudent(Student student);
    boolean updateStudentData(long studentId,Student student);
    boolean deleteStudent(long studentId);
}