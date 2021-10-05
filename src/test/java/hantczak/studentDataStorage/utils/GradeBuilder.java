package hantczak.studentDataStorage.utils;

import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeScale;

import java.time.LocalDate;

public class GradeBuilder {
    private Long id = 1L;
    private GradeScale gradeScale = GradeScale.GOOD;
    private long studentId = 1L;
    private LocalDate insertionDate = LocalDate.of(2020,01,01);
    private int gradeWeight = 2;

    public GradeBuilder() {
    }

    public static GradeBuilder create(){
        return new GradeBuilder();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGradeScale(GradeScale gradeScale) {
        this.gradeScale = gradeScale;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setInsertionDate(LocalDate insertionDate) {
        this.insertionDate = insertionDate;
    }

    public void setGradeWeight(int gradeWeight) {
        this.gradeWeight = gradeWeight;
    }

    public Grade build(){
        return new Grade(id,gradeScale,studentId,insertionDate,gradeWeight);
    }
}
