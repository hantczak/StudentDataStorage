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
import java.util.Optional;

@Tag("integration")
public class GetStudentByIdTests extends StudentDataStorageApplicationTests {

    @Autowired
    protected StudentRepository studentRepositoryProvider;

    @Test
    @DisplayName("Should return no student if he does not exist")
    void shouldReturnNoStudentIfHeDoesNotExist() {
        //given

        //when

        //then
        Optional<Student> databaseResponse = studentRepositoryProvider.getStudent(1L);
        Assertions.assertTrue(databaseResponse.isEmpty());
    }

    @Test
    @DisplayName("Should return one student if exists")
    void shouldReturnOneStudentIfExists() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();

        //when
        studentRepositoryProvider.addStudent(student);
        Student expectedResponse = student;

        //then
        Optional<Student> databaseResponse = studentRepositoryProvider.getStudent(1L);
        Assertions.assertEquals(expectedResponse,databaseResponse.get());
    }
}
