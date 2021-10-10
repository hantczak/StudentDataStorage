package hantczak.studentDataStorage.utils;

import hantczak.studentDataStorage.grade.domain.Grade;
import hantczak.studentDataStorage.grade.domain.GradeScale;

import java.time.LocalDate;

public class GradeBuilder {
    private Long id = null;
    private GradeScale gradeScale = GradeScale.GOOD;
    private long studentId = 1L;
    private LocalDate insertionDate = LocalDate.of(2020, 01, 01);
    private int gradeWeight = 2;

    public GradeBuilder() {
    }

    public static GradeBuilder create() {
        return new GradeBuilder();
    }

    public GradeBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public GradeBuilder setGradeScale(GradeScale gradeScale) {
        this.gradeScale = gradeScale;
        return this;
    }

    public GradeBuilder setStudentId(long studentId) {
        this.studentId = studentId;
        return this;

    }

    public GradeBuilder setInsertionDate(LocalDate insertionDate) {
        this.insertionDate = insertionDate;
        return this;
    }

    public GradeBuilder setGradeWeight(int gradeWeight) {
        this.gradeWeight = gradeWeight;
        return this;
    }

    public Grade build() {
        Grade grade = new Grade();
        if (this.id != null) {
            grade.setGradeId(id);
        }
        grade.setGradeScale(gradeScale);
        grade.setStudentId(studentId);
        grade.setInsertionDate(insertionDate);
        grade.setGradeWeight(gradeWeight);
        return grade;
    }
}
