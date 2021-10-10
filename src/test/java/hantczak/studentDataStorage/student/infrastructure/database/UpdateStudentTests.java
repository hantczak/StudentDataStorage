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
public class UpdateStudentTests extends StudentDataStorageApplicationTests {
    @Autowired
    protected StudentRepository studentRepositoryProvider;

    @Test
    @DisplayName("Should update student by Id")
    void shouldUpdateStudentById() {
        //given
        StudentBuilder studentBuilder = StudentBuilder.create();
        Student student = studentBuilder.setId(1L).build();
        studentBuilder.setName("cba");
        studentBuilder.setEmail("cba@gmail.com");
        Student newStudent = studentBuilder
                .setName("cba")
                .setEmail("cba@gmail.com")
                .build();

        //when
        studentRepositoryProvider.addStudent(student);
        studentRepositoryProvider.updateStudentData(1L, newStudent);

        //then
        Assertions.assertEquals(newStudent, studentRepositoryProvider.getStudent(1L).get());
    }
}