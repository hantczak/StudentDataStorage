package com.example.demo.dao;

import com.example.demo.dao.entity.Grade;

import java.util.List;

public interface GradeRepository {
    public List<Grade> getAllGrades();
    public List<Grade> getStudentGrades(long studentId);
    public void addGrade(Grade grade);
    public boolean updateGrade(Grade updatedGrade,int oldGradeValue, int oldGradeWeight);
    public boolean deleteGrade(Grade grade);
    public void deleteStudentGrades(long studentId);
}
