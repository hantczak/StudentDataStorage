package hantczak.studentDataStorage.student.infrastructure.database;

import hantczak.studentDataStorage.StudentDataStorageApplicationTests;
import hantczak.studentDataStorage.student.domain.Student;
import hantczak.studentDataStorage.student.domain.StudentRepository;
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
    @DisplayName("Should return registered students in order of addition")
    void shouldReturnStudentsInOrderOfAddition() {
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
        List<Student> databaseResponse = studentRepositoryProvider.getAllStudents();
        Assertions.assertEquals(expectedResponse, databaseResponse);
    }
}
