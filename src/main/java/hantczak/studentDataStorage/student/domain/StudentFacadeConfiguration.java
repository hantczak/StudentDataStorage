package hantczak.studentDataStorage.student.domain;

import hantczak.studentDataStorage.student.infrastructure.database.StudentLocalRepository;
import hantczak.studentDataStorage.student.infrastructure.database.StudentPostgreSQLDBAccessInterface;
import hantczak.studentDataStorage.student.infrastructure.database.StudentPostgreSQLRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFacadeConfiguration {

    @Bean
    public StudentFacade studentFacade(StudentPostgreSQLDBAccessInterface repository) {
        StudentRepository studentPostgreSQLRepository = new StudentPostgreSQLRepository(repository);
        StudentValidator studentValidator = new StudentValidator();
        StudentSortService studentSortService = new StudentSortService(studentPostgreSQLRepository);
        StudentService studentService = new StudentService(studentPostgreSQLRepository, studentValidator);
        return new StudentFacade(studentService, studentSortService);
    }

    public StudentFacade buildOnInMemoryRepo() {
        StudentRepository studentLocalRepository = new StudentLocalRepository();
        StudentValidator studentValidator = new StudentValidator();
        StudentSortService studentSortService = new StudentSortService(studentLocalRepository);
        StudentService studentService = new StudentService(studentLocalRepository, studentValidator);
        return new StudentFacade(studentService, studentSortService);
    }

   public StudentFacade studentFacade(StudentRepository studentRepository) {
        StudentValidator studentValidator = new StudentValidator();
        StudentSortService studentSortService = new StudentSortService(studentRepository);
        StudentService studentService = new StudentService(studentRepository, studentValidator);
        return new StudentFacade(studentService, studentSortService);
    }
}
