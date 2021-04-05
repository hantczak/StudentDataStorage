package hantczak.studentDataStorage.average.domain;

import hantczak.studentDataStorage.average.infrastructure.database.StudentAveragePostgreSQLRepository;
import hantczak.studentDataStorage.average.infrastructure.database.StudentAveragePostgreSQLRepositoryInterface;
import hantczak.studentDataStorage.average.infrastructure.database.StudentAverageRepositoryInMemory;
import hantczak.studentDataStorage.grade.domain.GradeFacade;
import hantczak.studentDataStorage.student.domain.StudentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentAverageFacadeConfiguration {

    @Bean
    public StudentAverageFacade studentAverageFacade(GradeFacade gradeFacade, StudentFacade studentFacade, StudentAveragePostgreSQLRepositoryInterface databaseAccess) {
        StudentAverageRepository studentAverageRepository = new StudentAveragePostgreSQLRepository(databaseAccess);
        StudentAverageService studentAverageService = new StudentAverageService(studentAverageRepository, gradeFacade);
        gradeFacade.addListener(studentAverageService);
        studentFacade.addListener(studentAverageService);
        return new StudentAverageFacade(studentAverageService);
    }

    public StudentAverageFacade studentAverageFacade(GradeFacade gradeFacade, StudentFacade studentFacade) {
        StudentAverageRepository studentAverageRepository = new StudentAverageRepositoryInMemory();
        StudentAverageService studentAverageService = new StudentAverageService(studentAverageRepository, gradeFacade);
        studentFacade.addListener(studentAverageService);
        gradeFacade.addListener(studentAverageService);
        return new StudentAverageFacade(studentAverageService);
    }
}
