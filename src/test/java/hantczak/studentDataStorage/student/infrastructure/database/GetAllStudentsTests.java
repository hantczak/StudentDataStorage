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
        Student student1 = studentBuilder
                .setName("bca")
                .setId(2L)
                .setEmail("bca@gmail.com")
                .build();

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
        Student student1 = studentBuilder
                .setName("bca")
                .setId(2L)
                .setEmail("bca@gmail.com")
                .build();

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
        Student student1 = studentBuilder
                .setId(2L)
                .setEmail("bca@gmail.com")
                .setAge(14)
                .build();

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
        Student student1 = studentBuilder
                .setId(2L)
                .setEmail("bca@gmail.com")
                .setAge(14)
                .build();

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
        Student student1 = studentBuilder
                .setId(2L)
                .setEmail("bbb@gmail.com")
                .setName("bbb")
                .build();

        Student student2 = studentBuilder
                .setId(3L)
                .setEmail("ccc@gmail.com")
                .setName("ccc")
                .build();

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