package com.example.demo.grade.domain;

import com.example.demo.average.domain.StudentAverageFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class GradeFacadeConfiguration {

    @Bean
    GradeFacade gradeFacade(
            GradeRepository gradeRepository,

    ) {
        GradeService service = new GradeService(gradeRepository, Collections.singleton())
        return new GradeFacade(
                gradeService,
                gradeSortService,
                studentAverageFacade);
    }
}
