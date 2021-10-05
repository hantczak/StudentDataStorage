package hantczak.studentDataStorage.grade.infrastructure.api;

import java.util.List;
import java.util.Objects;

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

    public GradeResponse() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeResponse that = (GradeResponse) o;
        return gradeCount == that.gradeCount && Objects.equals(gradeList, that.gradeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeList, gradeCount);
    }
}
