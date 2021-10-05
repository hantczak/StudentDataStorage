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

        //when
        Student student = studentBuilder.build();
        studentRepositoryProvider.addStudent(student);

        studentBuilder.setName("cba");
        studentBuilder.setEmail("cba@gmail.com");
        Student newStudent = studentBuilder.build();

        studentRepositoryProvider.updateStudentData(1L, newStudent);

        //then
        Assertions.assertEquals(newStudent, studentRepositoryProvider.getStudent(1L).get());
    }
}
