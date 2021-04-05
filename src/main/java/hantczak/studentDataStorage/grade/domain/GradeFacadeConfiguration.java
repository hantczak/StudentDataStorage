package hantczak.studentDataStorage.grade.domain;

import hantczak.studentDataStorage.grade.infrastructure.database.GradeLocalRepository;
import hantczak.studentDataStorage.grade.infrastructure.database.GradePostgreSQLRepository;
import hantczak.studentDataStorage.grade.infrastructure.database.GradePostgreSQLRepositoryInterface;
import hantczak.studentDataStorage.student.domain.StudentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    public GradeFacade gradeFacade(StudentFacade studentFacade, GradePostgreSQLRepositoryInterface database) {
        GradeRepository gradeRepository = new GradePostgreSQLRepository(database);
        GradeValidator gradeValidator = new GradeValidator(studentFacade);
        GradeService gradeService = new GradeService(gradeRepository, gradeValidator);
        GradeSortService gradeSortService = new GradeSortService(gradeRepository);
        studentFacade.addListener(gradeService);
        return new GradeFacade(gradeService, gradeSortService);
    }

    public GradeFacade buildOnInMemoryRepo(StudentFacade studentFacade) {
        GradeRepository gradeRepository = new GradeLocalRepository();
        GradeValidator gradeValidator = new GradeValidator(studentFacade);
        GradeService gradeService = new GradeService(gradeRepository, gradeValidator);
        GradeSortService gradeSortService = new GradeSortService(gradeRepository);
        studentFacade.addListener(gradeService);
        return new GradeFacade(gradeService, gradeSortService);
    }

    public GradeFacade gradeFacade(StudentFacade studentFacade, GradeRepository gradeRepository) {
        GradeValidator gradeValidator = new GradeValidator(studentFacade);
        GradeService gradeService = new GradeService(gradeRepository, gradeValidator);
        GradeSortService gradeSortService = new GradeSortService(gradeRepository);
        studentFacade.addListener(gradeService);
        return new GradeFacade(gradeService, gradeSortService);
    }
}
