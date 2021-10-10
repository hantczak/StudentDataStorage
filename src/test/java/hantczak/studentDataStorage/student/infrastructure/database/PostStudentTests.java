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
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Tag("integration")
public class PostStudentTests extends StudentDataStorageApplicationTests {

    @Autowired
    protected StudentRepository studentRepositoryProvider;

    @Test
    @DisplayName("Should throw data integrity violation exception for duplicate email")
    void shouldThrowDataIntegrityViolationExceptionForDuplicateEmail() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();
        studentBuilder.setId(2L);
        Student student1 = studentBuilder.build();

        //when
        studentRepositoryProvider.addStudent(student);

        //then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> studentRepositoryProvider.addStudent(student1));
    }
}