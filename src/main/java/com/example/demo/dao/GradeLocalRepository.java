package com.example.demo.dao;

import com.example.demo.dao.entity.Grade;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GradeLocalRepository implements GradeRepository {
    private Map<Long, List<Grade>> gradeRegister;

    public GradeLocalRepository() {
        this.gradeRegister = new HashMap<>();
    }

    @Override
    public List<Grade> getAllGrades() {
        List<Grade> allGradesList = new ArrayList<>();
        gradeRegister.values().stream()
                .forEach(list -> list.forEach(grade -> allGradesList.add(grade)));
        return allGradesList;
    }

    @Override
    public List<Grade> getStudentGrades(long studentId) {
        List<Grade> studentGradeList;
        studentGradeList = gradeRegister.getOrDefault(studentId, List.of());
        return studentGradeList;
    }

    @Override
    public void addGrade(Grade grade) {
        if (gradeRegister.containsKey(grade.getStudentId())) {
            this.gradeRegister.get(grade.getStudentId()).add(grade);
        } else {
            List<Grade> newStudentGradeList = new ArrayList<>();
            newStudentGradeList.add(grade);
            this.gradeRegister.put(grade.getStudentId(), newStudentGradeList);
        }
    }

    @Override
    public boolean updateGrade(Grade updatedGrade, int oldGradeValue) {
        if (gradeRegister.containsKey(updatedGrade.getStudentId())) {

            Optional<Grade> gradeToBeUpdated = gradeRegister.get(updatedGrade.getStudentId()).stream()
                    .filter(grade -> grade.getGradeValue() == oldGradeValue)
                    .findFirst();
            gradeToBeUpdated.ifPresent(grade -> {
                gradeRegister.get(grade.getStudentId()).remove(grade);
                addGrade(updatedGrade);
            });
            if (gradeToBeUpdated.isPresent()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteGrade(Grade gradeToBeDeleted) {
        if (gradeRegister.containsKey(gradeToBeDeleted.getStudentId())) {

            Optional<Grade> existingGrade = gradeRegister.get(gradeToBeDeleted.getStudentId()).stream()
                    .filter(grade -> grade.getGradeValue() == gradeToBeDeleted.getGradeValue())
                    .findFirst();
            existingGrade.ifPresent(grade -> gradeRegister.get(grade.getStudentId()).remove(grade));
            if (existingGrade.isPresent()) {
                return true;
            }
        }
        return false;
    }
}
