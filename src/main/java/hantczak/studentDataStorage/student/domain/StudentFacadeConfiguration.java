package hantczak.studentDataStorage.student.domain;

import hantczak.studentDataStorage.student.infrastructure.database.StudentRepositoryInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFacadeConfiguration {

    @Bean
    public StudentFacade studentFacade(StudentRepository studentRepository) {
        StudentValidator studentValidator = new StudentValidator();
        StudentService studentService = new StudentService(studentRepository, studentValidator);
        return new StudentFacade(studentService);
    }

    public StudentFacade buildOnInMemoryRepo() {
        StudentRepository studentLocalRepository = new StudentRepositoryInMemory();
        StudentValidator studentValidator = new StudentValidator();
        StudentService studentService = new StudentService(studentLocalRepository, studentValidator);
        return new StudentFacade(studentService);
    }
}
