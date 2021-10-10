package hantczak.studentDataStorage.grade.domain;

import hantczak.studentDataStorage.grade.infrastructure.database.GradeRepositoryInMemory;
import hantczak.studentDataStorage.student.domain.StudentFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    public GradeFacade gradeFacade(StudentFacade studentFacade, GradeRepository gradeRepository) {
        GradeValidator gradeValidator = new GradeValidator(studentFacade);
        GradeService gradeService = new GradeService(gradeRepository, gradeValidator);
        studentFacade.addListener(gradeService);
        return new GradeFacade(gradeService);
    }

    public GradeFacade buildOnInMemoryRepo(StudentFacade studentFacade) {
        GradeRepository gradeRepository = new GradeRepositoryInMemory();
        GradeValidator gradeValidator = new GradeValidator(studentFacade);
        GradeService gradeService = new GradeService(gradeRepository, gradeValidator);
        studentFacade.addListener(gradeService);
        return new GradeFacade(gradeService);
    }
}

