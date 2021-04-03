package hantczak.studentDataStorage.student.domain;

import hantczak.studentDataStorage.student.infrastructure.database.StudentLocalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFacadeConfiguration {

    @Bean
    public StudentFacade studentFacade() {
        StudentRepository studentLocalRepository = new StudentLocalRepository();
        StudentValidator studentValidator = new StudentValidator();
        StudentSortService studentSortService = new StudentSortService(studentLocalRepository);
        StudentService studentService = new StudentService(studentLocalRepository, studentValidator);
        return new StudentFacade(studentService, studentSortService);
    }

   public StudentFacade studentFacade(StudentRepository studentLocalRepository) {
        StudentValidator studentValidator = new StudentValidator();
        StudentSortService studentSortService = new StudentSortService(studentLocalRepository);
        StudentService studentService = new StudentService(studentLocalRepository, studentValidator);
        return new StudentFacade(studentService, studentSortService);
    }
}
