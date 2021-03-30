package com.example.demo.grade.infrastructure.api;

import java.util.List;

public class GradeResponse {
    private List<GradeDto> gradeList;
    private int gradeCount;

    public GradeResponse(List<GradeDto> gradeList) {
        this.gradeList = gradeList;
        this.gradeCount = gradeList.size();
    }

    public List<GradeDto> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<GradeDto> gradeList) {
        this.gradeList = gradeList;
    }

    public int getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(int gradeCount) {
        this.gradeCount = gradeCount;
    }
}
