package hantczak.studentDataStorage.student.infrastructure.api;

import java.util.List;
import java.util.Objects;

public class StudentResponse {
    private List<StudentDto> students;
    private int studentCount;

    public StudentResponse(List<StudentDto> students) {
        this.students = students;
        this.studentCount = students.size();
    }

    public StudentResponse() {
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentResponse that = (StudentResponse) o;
        return studentCount == that.studentCount && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, studentCount);
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "students=" + students +
                ", studentCount=" + studentCount +
                '}';
    }
}
