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

@Tag("integration")
public class DeleteStudentTests extends StudentDataStorageApplicationTests {

    @Autowired
    protected StudentRepository studentRepositoryProvider;

    @Test
    @DisplayName("Should delete student by Id")
    void shouldDeleteStudentByID() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.build();

        //when
        studentRepositoryProvider.addStudent(student);
        studentRepositoryProvider.deleteStudent(1L);

        //then
        Assertions.assertTrue(studentRepositoryProvider.getStudent(1L).isEmpty());
    }
}
