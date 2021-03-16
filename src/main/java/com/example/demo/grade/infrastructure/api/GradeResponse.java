package com.example.demo.grade.infrastructure.api;

import com.example.demo.grade.domain.Grade;

import java.util.List;

public class GradeResponse {
    private List<Grade> gradeList;
    private int gradeCount;

    public GradeResponse(List<Grade> gradeList) {
        this.gradeList = gradeList;
        this.gradeCount = gradeList.size();
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public int getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(int gradeCount) {
        this.gradeCount = gradeCount;
    }
}
