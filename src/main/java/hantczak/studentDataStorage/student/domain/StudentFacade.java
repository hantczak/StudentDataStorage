package hantczak.studentDataStorage.student.domain;

import java.util.List;
import java.util.Optional;

public class StudentFacade {
    private final StudentService studentService;

    public StudentFacade(StudentService studentService) {
        this.studentService = studentService;
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public List<Student> getSortedStudents(String studentSortType, int offset, int limit) {
        return studentService.getAllStudentsSortedWithPagination(studentSortType, offset, limit);
    }

    public Optional<Student> getStudent(long id) {
        Optional<Student> firstFoundByIndex;
        firstFoundByIndex = studentService.getStudent(id);
        return firstFoundByIndex;
    }

    public Student addStudent(Student student) {
        return studentService.addStudent(student);
    }

    public boolean updateStudentData(long studentId, Student student) {
        return studentService.updateStudentData(studentId, student);
    }

    public boolean deleteStudentAndHisGrades(long studentId) {
        return studentService.deleteStudentAndHisGrades(studentId);
    }

    public void addListener(StudentDeletedListener studentDeletedListener) {
        studentService.addListener(studentDeletedListener);
    }
}

