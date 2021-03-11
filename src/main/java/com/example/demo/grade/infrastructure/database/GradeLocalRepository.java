package com.example.demo.grade.infrastructure.database;

import com.example.demo.grade.domain.GradeRepository;
import com.example.demo.grade.domain.Grade;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GradeLocalRepository implements GradeRepository {
    private Map<Long, List<Grade>> studentIdToGrade = new HashMap<>();

    @Override
    public List<Grade> getAllGrades() {
        List<Grade> allGradesList = new ArrayList<>();
        studentIdToGrade.values().stream()
                .forEach(list -> allGradesList.addAll(list));
        return allGradesList;
    }

    @Override
    public List<Grade> getStudentGrades(long studentId) {
        List<Grade> studentGradeList;
        studentGradeList = studentIdToGrade.getOrDefault(studentId, Collections.emptyList());
        return studentGradeList;
    }

    @Override
    public void addGrade(Grade grade) {
        if (studentIdToGrade.containsKey(grade.getStudentId())) {
            studentIdToGrade.get(grade.getStudentId()).add(grade);
        } else {
            List<Grade> newStudentGradeList = new ArrayList<>();
            newStudentGradeList.add(grade);
            studentIdToGrade.put(grade.getStudentId(), newStudentGradeList);
        }
    }

    @Override
    public boolean updateGrade(Grade updatedGrade, int oldGradeId) {
        if (studentIdToGrade.containsKey(updatedGrade.getStudentId())) {

            Optional<Grade> gradeToBeUpdated = studentIdToGrade.get(updatedGrade.getStudentId()).stream()
                    .filter(grade -> grade.getGradeId() == oldGradeId)
                    .findFirst();
            gradeToBeUpdated.ifPresent(grade -> {
                deleteGrade(updatedGrade.getStudentId(),oldGradeId);
                addGrade(updatedGrade);
            });
            if (gradeToBeUpdated.isPresent()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteGrade(long studentId, int gradeToBeDeletedId) {
        List<Grade> gradeList = studentIdToGrade.get(studentId);
        if (gradeList!=null) {
            gradeList.removeIf(grade -> grade.getGradeId() == gradeToBeDeletedId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteStudentGrades(long studentId) {
        studentIdToGrade.remove(studentId);
    }
}
