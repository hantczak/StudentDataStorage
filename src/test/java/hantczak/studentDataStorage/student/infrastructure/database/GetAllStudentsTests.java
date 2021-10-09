package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;
import hantczak.studentDataStorage.student.domain.StudentSortType;
import hantczak.studentDataStorage.utils.StudentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Tag("integration")
public class GetAllStudentsTests extends StudentDataStorageApplicationTests {

    @Autowired
    protected StudentRepository studentRepositoryProvider;

    @Test
    @DisplayName("Should return empty list for no registered students")
    void shouldReturnEmptyListForNoRegisteredStudents() {
        //given

        //when

        List<Student> expectedResponse = new ArrayList<>();

        //then
        List<Student> databaseResponse = studentRepositoryProvider.getAllStudents();
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered students ordered by name ascending")
    void shouldReturnStudentsOrderedByNameAscending() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();
        studentBuilder.setName("bca");
        studentBuilder.setId(2L);
        studentBuilder.setEmail("bca@gmail.com");
        Student student1 = studentBuilder.build();

        //when
        studentRepositoryProvider.addStudent(student);
        studentRepositoryProvider.addStudent(student1);

        List<Student> expectedResponse = List.of(student, student1);

        //then
        List<Student> databaseResponse = studentRepositoryProvider.getAllStudentsSortedWithPagination(StudentSortType.NAME_ASC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered students ordered by name descending")
    void shouldReturnStudentsOrderedByNameDescending() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();
        studentBuilder.setName("bca");
        studentBuilder.setId(2L);
        studentBuilder.setEmail("bca@gmail.com");
        Student student1 = studentBuilder.build();

        //when
        studentRepositoryProvider.addStudent(student);
        studentRepositoryProvider.addStudent(student1);

        List<Student> expectedResponse = List.of(student1, student);

        //then
        List<Student> databaseResponse = studentRepositoryProvider.getAllStudentsSortedWithPagination(StudentSortType.NAME_DSC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered students ordered by age ascending")
    void shouldReturnStudentsOrderedByAgeAscending() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();
        studentBuilder.setId(2L);
        studentBuilder.setEmail("bca@gmail.com");
        studentBuilder.setAge(14);
        Student student1 = studentBuilder.build();

        //when
        studentRepositoryProvider.addStudent(student);
        studentRepositoryProvider.addStudent(student1);

        List<Student> expectedResponse = List.of(student, student1);

        //then
        List<Student> databaseResponse = studentRepositoryProvider.getAllStudentsSortedWithPagination(StudentSortType.AGE_ASC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return registered students ordered by age descending")
    void shouldReturnStudentsOrderedByAgeDescending() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();
        studentBuilder.setId(2L);
        studentBuilder.setEmail("bca@gmail.com");
        studentBuilder.setAge(14);
        Student student1 = studentBuilder.build();

        //when
        studentRepositoryProvider.addStudent(student);
        studentRepositoryProvider.addStudent(student1);

        List<Student> expectedResponse = List.of(student1, student);

        //then
        List<Student> databaseResponse = studentRepositoryProvider.getAllStudentsSortedWithPagination(StudentSortType.AGE_DSC, 0, 5);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }

    @Test
    @DisplayName("Should return middle student for three students sorted alphabetically")
    void shouldReturnMiddleStudentForThreeStudentsSortedAlphabetically() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();
        studentBuilder.setId(2L);
        studentBuilder.setEmail("bbb@gmail.com");
        studentBuilder.setName("bbb");
        Student student1 = studentBuilder.build();
        studentBuilder.setId(3L);
        studentBuilder.setEmail("ccc@gmail.com");
        studentBuilder.setName("ccc");
        Student student2 = studentBuilder.build();

        //when
        studentRepositoryProvider.addStudent(student);
        studentRepositoryProvider.addStudent(student1);
        studentRepositoryProvider.addStudent(student2);

        List<Student> expectedResponse = List.of(student1);

        //then
        List<Student> databaseResponse = studentRepositoryProvider.getAllStudentsSortedWithPagination(StudentSortType.AGE_DSC, 1, 1);
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }
}