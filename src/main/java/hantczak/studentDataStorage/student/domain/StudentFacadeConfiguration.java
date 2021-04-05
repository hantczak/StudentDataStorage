package hantczak.studentDataStorage.student.domain;

import hantczak.studentDataStorage.student.infrastructure.database.StudentRepositoryInMemory;
import hantczak.studentDataStorage.student.infrastructure.database.StudentPostgreSQLRepositoryInterface;
import hantczak.studentDataStorage.student.infrastructure.database.StudentPostgreSQLRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentFacadeConfiguration {

    @Bean
    public StudentFacade studentFacade(StudentPostgreSQLRepositoryInterface databaseAccess) {
        StudentRepository studentPostgreSQLRepository = new StudentPostgreSQLRepository(databaseAccess);
        StudentValidator studentValidator = new StudentValidator();
        StudentSortService studentSortService = new StudentSortService(studentPostgreSQLRepository);
        StudentService studentService = new StudentService(studentPostgreSQLRepository, studentValidator);
        return new StudentFacade(studentService, studentSortService);
    }

    public StudentFacade buildOnInMemoryRepo() {
        StudentRepository studentLocalRepository = new StudentRepositoryInMemory();
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
