package hantczak.studentDataStorage.student.domain;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> getAllStudents();
    List<Student> getAllStudentsSortedWithPagination(StudentSortType studentSortType,int offset, int limit);
    Optional<Student> getStudent(long studentId);
    Student addStudent(Student student);
    boolean updateStudentData(long studentId,Student student);
    boolean deleteStudent(long studentId);
}